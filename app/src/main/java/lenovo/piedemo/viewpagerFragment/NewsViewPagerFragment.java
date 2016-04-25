package lenovo.piedemo.viewpagerFragment;

import android.os.Bundle;

import lenovo.piedemo.R;
import lenovo.piedemo.adapter.ViewPagerFragmentAdapter;
import lenovo.piedemo.base.BaseViewPagerFragment;
import lenovo.piedemo.bean.BlogList;
import lenovo.piedemo.bean.NewsList;
import lenovo.piedemo.base.BaseListFragment;
import lenovo.piedemo.fragment.BlogFragment;

/**
 * Created by zhangyi on 16-4-25.
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment {


//    @Override
//    protected void setTabLimit() {
//        mViewPager.setOffscreenPageLimit(3);
//    }

    @Override
    protected void setViewPagerAdapter(ViewPagerFragmentAdapter adapter) {
        String title[] = getResources().getStringArray(R.array.news_viewpage_arrays);
        adapter.addTab("news", BaseListFragment.class , getBundle(NewsList.CATALOG_ALL) , title[0]);
        adapter.addTab("news_week", BaseListFragment.class , getBundle(NewsList.CATALOG_WEEK) , title[1]);
        adapter.addTab("latest_blog", BaseListFragment.class , getBundle(BlogList.CATALOG_LATEST) , title[2]);
        adapter.addTab("recomend_blog", BaseListFragment.class , getBundle(BlogList.CATALOG_RECOMMEND) , title[3]);
    }

    /**
     * 基类会根据不同的 new type 显示不同的数据
     * @param newType
     * @return
     */
    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }

    /**
     * 基类会根据不同的catalog展示相应的数据
     *
     * @param catalog
     *            要显示的数据类别
     * @return
     */
    private Bundle getBundle(String catalog) {
        Bundle bundle = new Bundle();
        bundle.putString(BlogFragment.BUNDLE_BLOG_TYPE, catalog);
        return bundle;
    }
}
