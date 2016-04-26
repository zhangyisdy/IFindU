package lenovo.piedemo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import lenovo.piedemo.R;
import lenovo.piedemo.bean.ViewPagerInfo;
import lenovo.piedemo.widget.PagerSlidingTabStrip;

/**
 * Created by zhangyi on 16-4-25.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mViewPager;
    private Context mContext;
    private ArrayList<ViewPagerInfo> mTabs = new ArrayList<ViewPagerInfo>();


    public ViewPagerFragmentAdapter(FragmentManager fm , PagerSlidingTabStrip tabStrip, ViewPager viewPager) {
        super(fm);
        mContext = viewPager.getContext();
        mTabStrip = tabStrip;
        mViewPager = viewPager;
        mViewPager.setAdapter(this);
        mTabStrip.setViewPager(mViewPager);
    }

    /**
     * add tab
     * @param tag
     * @param clss
     * @param bundle
     * @param title
     */
    public void addTab(String tag , Class<?> clss , Bundle bundle , String title){
        ViewPagerInfo info = new ViewPagerInfo(tag, clss , bundle , title);
        addFragment(info);
    }

    private void addFragment(ViewPagerInfo info){

        View v = LayoutInflater.from(mContext).inflate(R.layout.base_viewpage_fragment_tab_item , null ,false);
        TextView title = (TextView) v.findViewById(R.id.tab_title);
        title.setText(info._title);

        mTabStrip.addTab(v);
        mTabs.add(info);

        notifyDataSetChanged();

    }

    /**
     *  remove view tab
     * @param index
     */
    public void removeTabItem(int index){
        if(index < 0){
            index = 0;
        }

        if(mTabs.isEmpty()){
            return;
        }

        mTabs.remove(index);
        mTabStrip.removeTab(index);
        notifyDataSetChanged();
    }

    /**
     *  remove all tab
     */
    public void removeTabAll(){
        if(mTabs.isEmpty()){
            return;
        }

        mTabs.clear();
        mTabStrip.removeAllTab();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext , info._clss.getName() , info._params);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position)._title;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }


}
