package lenovo.piedemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lenovo.piedemo.adapter.ListBaseAdapter;
import lenovo.piedemo.base.BaseListFragment;

/**
 * Created by zhangyi on 16-3-8.
 */
public class NewsTabFragment extends BaseListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    private ListBaseAdapter getListAdapter() {
        return null;
    }
}
