package lenovo.piedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lenovo.piedemo.R;
import lenovo.piedemo.bean.Entity;


public class ListBaseAdapter<T extends Entity> extends BaseAdapter {

    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    public static final int STATE_NETWORK_ERROR = 5;
    public static final int STATE_OTHER = 6;

    protected int state = STATE_LESS_ONE_PAGE;

    protected ArrayList<T> mDatas = new ArrayList<T>();
    private LayoutInflater mInflater;

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return this.state;
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }
    
    /**
     * set the listview data
     * @param data
     */
    public void addData(List<T> data){
        if(mDatas != null && data != null && !data.isEmpty()) {
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public ArrayList<T> getData() {
        return mDatas == null ? (mDatas = new ArrayList<T>()) : mDatas;
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(state == STATE_LESS_ONE_PAGE){
            return mDatas.size();
        }else if(state == STATE_NO_DATA){
            return 1;
        }else{
            return mDatas.size()+1;
        }
    }

    @Override
    public T getItem(int position) {
        if(mDatas.size() > position){
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private LinearLayout mFooterView;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == getCount() - 1){
            if (getState() == STATE_LOAD_MORE || getState() == STATE_NO_MORE
                    || state == STATE_EMPTY_ITEM
                    || getState() == STATE_NETWORK_ERROR){
                mFooterView = (LinearLayout) View.inflate(parent.getContext() , R.layout.list_footer_layout , null);

                ProgressBar progress = (ProgressBar) mFooterView
                        .findViewById(R.id.loading);
                TextView text = (TextView) mFooterView.findViewById(R.id.text);
                switch (getState()) {
                    case STATE_LOAD_MORE:
                        break;
                    case STATE_NO_MORE:
                        mFooterView.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        break;
                    case STATE_EMPTY_ITEM:
                        progress.setVisibility(View.GONE);
                        mFooterView.setVisibility(View.VISIBLE);
                        break;
                    case STATE_NETWORK_ERROR:
                        mFooterView.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        break;
                    default:
                        progress.setVisibility(View.GONE);
                        mFooterView.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        break;
                }
                return mFooterView;
            }
        }
        if(position < 0 ){
            position = 0; // 若列表没有数据，是没有footview/headview的
        }
        return getRealView(position, convertView, parent);
    }

    protected View getRealView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
