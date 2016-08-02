package lenovo.piedemo.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import lenovo.piedemo.AppContext;
import lenovo.piedemo.R;
import lenovo.piedemo.adapter.ListBaseAdapter;
import lenovo.piedemo.bean.Entity;
import lenovo.piedemo.bean.ListEntity;
import lenovo.piedemo.widget.EmptyLayout;

/**
 * Created by zhangyi on 16-3-8.
 */
public abstract class BaseListFragment<T extends Entity> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    public static String BUNDLE_KEY_CATALOG = "bundle_key_catalog";

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ListView mListView;
    protected EmptyLayout mEmptyLayout;

    protected ListBaseAdapter<T> mAdapter;

    protected int mCurrentPage = 0;

    protected int mCatalog = 1;

    private ParserTask mParserTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pull_refresh_listview, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        mListView = (ListView) view.findViewById(R.id.listview);
        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.error_layout);

        initView();
    }

    private void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mEmptyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO onclick error layout
            }
        });

        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);

        if(mAdapter != null){
            mListView.setAdapter(mAdapter);
            mEmptyLayout.setVisibility(View.VISIBLE);
        }else{
            mAdapter = getListAdapter();
            mListView.setAdapter(mAdapter);

            mEmptyLayout.setVisibility(View.VISIBLE);
            //requestData(false);
            sendRequestData();
        }
    }

    private void requestData(boolean refresh) {
        if(refresh){  // 请求网络数据
            sendRequestData();
        }else{

        }
    }

    protected void sendRequestData() {
    }

    public abstract ListBaseAdapter<T> getListAdapter();

    protected ListEntity<T> parseList(InputStream is) throws Exception{
        return null;
    }

    protected void executeParseTask(byte[] responseBody){
        cancelParserTask();
        mParserTask = new ParserTask(responseBody);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    protected void executeOnLoadDataSuccess(List<T> data) {

        mEmptyLayout.setLayoutMode(View.GONE);

        for (int i = 0; i < data.size(); i++) {
            if (compareTo(mAdapter.getData(), data.get(i))) {
                data.remove(i);
                i--;
            }
        }

        int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        if ((mAdapter.getCount() + data.size()) == 0) {
            adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        } else if (data.size() == 0
                || (data.size() < getPageSize() && mCurrentPage == 0)) {
            adapterState = ListBaseAdapter.STATE_NO_MORE;
            mAdapter.notifyDataSetChanged();
        } else {
            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
        }
        //mAdapter.setState(adapterState);
        mAdapter.addData(data);
        //mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
        //mAdapter.notifyDataSetChanged();

    }

    // 下拉刷新
    @Override
    public void onRefresh() {
        if(mState == STATE_REFRESH){ //设置刷新状态
            return;
        }

        mListView.setSelection(0);
        setSwipeRefreshLoadingStatus();
        mCurrentPage = 0;
        mState = STATE_REFRESH;
        //sendRequestData();
    }

    /**
     * 设置SwipeRefresh 下拉刷新的状态，防止重复刷新
     */
    private void setSwipeRefreshLoadingStatus() {
        if(mSwipeRefreshLayout != null){
            mSwipeRefreshLayout.setRefreshing(true);
            mSwipeRefreshLayout.setEnabled(false);
        }
    }


    class ParserTask extends AsyncTask<Void,Void,String>{
        private final byte[] responseData;

        List<T> list;

        public ParserTask(byte[] data) {this.responseData = data;}

        @Override
        protected String doInBackground(Void... params) {
            try{

                ListEntity<T> data= parseList(new ByteArrayInputStream(responseData));
                list = data.getList();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            executeOnLoadDataSuccess(list);
        }
    }

    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            Log.d("zhangyi" , "AsyncHttpResponseHandler onSuccess");
            executeParseTask(responseBody);
//            if(isAdded()){
//                executeParseTask(responseBody);
//            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.d("zhangyi" , "AsyncHttpResponseHandler onFailure");
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    protected int getPageSize() {
        return AppContext.PAGE_SIZE;
    }

    protected boolean compareTo(List<? extends Entity> data, Entity enity) {
        int s = data.size();
        if (enity != null) {
            for (int i = 0; i < s; i++) {
                if (enity.getId() == data.get(i).getId()) {
                    return true;
                }
            }
        }
        return false;
    }

}
