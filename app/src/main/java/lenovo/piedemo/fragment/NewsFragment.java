package lenovo.piedemo.fragment;

import com.loopj.android.http.AsyncHttpResponseHandler;

import lenovo.piedemo.adapter.ListBaseAdapter;
import lenovo.piedemo.base.BaseListFragment;
import lenovo.piedemo.http.RemoteHttpApi;

/**
 * Created by zhangyi on 16-4-27.
 */
public class NewsFragment extends BaseListFragment{

    @Override
    public ListBaseAdapter getListAdapter() {
        return null;
    }

    @Override
    protected void sendRequestData(){
        RemoteHttpApi.getNewsList(mCatalog , mCurrentPage , mHandler);
    }
}
