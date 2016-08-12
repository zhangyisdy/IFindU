package lenovo.piedemo.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.InputStream;
import java.util.List;

import lenovo.piedemo.adapter.ListBaseAdapter;
import lenovo.piedemo.adapter.NewsAdapter;
import lenovo.piedemo.base.BaseListFragment;
import lenovo.piedemo.bean.ListEntity;
import lenovo.piedemo.bean.News;
import lenovo.piedemo.bean.NewsList;
import lenovo.piedemo.http.RemoteHttpApi;
import lenovo.piedemo.ui.DetailActivity;
import lenovo.piedemo.util.XmlUtils;
import lenovo.piedemo.widget.EmptyLayout;

/**
 * Created by zhangyi on 16-4-27.
 */
public class NewsFragment extends BaseListFragment<News>{

    @Override
    public NewsAdapter getListAdapter() {
        return new NewsAdapter();
    }

    @Override
    protected void sendRequestData(){
        RemoteHttpApi.getNewsList(mCatalog , mCurrentPage , mHandler);
    }

    @Override
    protected NewsList parseList(InputStream is) throws Exception{
        NewsList list = null;
        list = XmlUtils.toBean(NewsList.class ,is);
        return list;
    }

    @Override
    protected void executeOnLoadDataSuccess(List<News> data) {

        if (mCatalog == NewsList.CATALOG_WEEK
                || mCatalog == NewsList.CATALOG_MONTH) {
            if (mState == STATE_REFRESH)
                mAdapter.clear();
            mAdapter.addData(data);
            mState = STATE_NOMORE;
            mAdapter.setState(ListBaseAdapter.STATE_NO_MORE);
            return;
        }
        super.executeOnLoadDataSuccess(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = mAdapter.getItem(position);
        Intent intent = new Intent();
        intent.setClass(getContext(), DetailActivity.class);
        intent.putExtra("id",news.getId());
        startActivity(intent);
    }
}
