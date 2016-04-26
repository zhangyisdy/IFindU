package lenovo.piedemo.bean;


import lenovo.piedemo.R;
import lenovo.piedemo.base.BaseFragment;
import lenovo.piedemo.base.BaseViewPagerFragment;
import lenovo.piedemo.viewpagerFragment.NewsViewPagerFragment;

/**
 * Created by zhangyi on 16-3-7.
 */
public enum MainTab {

    NEWS(0, R.string.main_tab_name_news,R.drawable.tab_icon_new,NewsViewPagerFragment.class),

    TWEET(1,R.string.main_tab_name_tweet,R.drawable.tab_icon_tweet,BaseFragment.class),

    QUICK(2,R.string.main_tab_name_quick,R.drawable.tab_icon_new,BaseFragment.class),

    EXPLORE(3,R.string.main_tab_name_explore,R.drawable.tab_icon_explore,BaseFragment.class),

    ME(4,R.string.main_tab_name_my,R.drawable.tab_icon_me,BaseFragment.class);


    private int index;
    private int desc;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int index , int desc , int resIcon , Class<?> clz){
        this.index = index;
        this.desc = desc;
        this.resIcon = resIcon;
        this.clz = clz;
    }


    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
