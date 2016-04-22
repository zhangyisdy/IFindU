package lenovo.piedemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lenovo.piedemo.R;
import lenovo.piedemo.widget.PagerSlidingTabStrip;

/**
 * Created by zhangyi on 16-3-8.
 */
public class BaseViewPagerFragment extends BaseFragment {

    private PagerSlidingTabStrip mTabStrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpage_fragment , null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.pager_tabstrip);

    }
}
