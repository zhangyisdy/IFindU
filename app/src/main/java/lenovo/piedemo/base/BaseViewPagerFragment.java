package lenovo.piedemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lenovo.piedemo.R;
import lenovo.piedemo.adapter.ViewPagerFragmentAdapter;
import lenovo.piedemo.widget.PagerSlidingTabStrip;

/**
 * Created by zhangyi on 16-3-8.
 */
public abstract class BaseViewPagerFragment extends BaseFragment {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpage_fragment , null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.pager_tabstrip);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mTabStrip , mViewPager);

        setTabLimit();
        setViewPagerAdapter(mViewPagerFragmentAdapter);

    }

    protected void setTabLimit() {
    }

    protected abstract void setViewPagerAdapter(ViewPagerFragmentAdapter adapter);
}
