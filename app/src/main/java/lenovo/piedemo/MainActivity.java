package lenovo.piedemo;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import lenovo.piedemo.bean.MainTab;
import lenovo.piedemo.fragment.NavigationDrawerFragment;
import lenovo.piedemo.widget.MyFragmentTabHost;

/**
 * Created by zhangyi on 16-3-4.
 */
public class MainActivity extends ActionBarActivity implements View.OnTouchListener, TabHost.OnTabChangeListener {

    private Context mContext;
    private MyFragmentTabHost mTabHost;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.mContext = this;

        initView();
    }

    private void initView() {
        mTitle = getTitle();

        // set navigation drawer fragment
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer , (DrawerLayout)findViewById(R.id.drawer_layout));

        // set up tab
        mTabHost = (MyFragmentTabHost)findViewById(R.id.tab_host);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        initTab();

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    private void initTab() {
        MainTab[] tabs = MainTab.values();
        for(int i=0 ; i<tabs.length ; i++){
            MainTab mainTab = tabs[i];
            TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getDesc()));
            View indicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);

            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());

            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
                mTabHost.setNoTabChangedTag(getString(mainTab.getDesc()));
            }

            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null , null);
            title.setText(getString(mainTab.getDesc()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tab,mainTab.getClz(),null);
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private void restoreActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browser_menu, menu);
        if(!mNavigationDrawerFragment.isDraweOpen()){
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                //UIHelper.showSimpleBack(this, SimpleBackPage.SEARCH);
                break;
            case android.R.id.home:
                if(mNavigationDrawerFragment.isDraweOpen()){
                    mNavigationDrawerFragment.closeDrawerMenu();
                }else{
                    mNavigationDrawerFragment.openDrawerMenu();
                }

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId) {
        int size = mTabHost.getTabWidget().getTabCount();
        for(int i = 0 ; i<size; i++){
            View view = mTabHost.getTabWidget().getChildAt(i);
            if(i == mTabHost.getCurrentTab()){
                view.setSelected(true);
            }else{
                view.setSelected(false);
            }
        }
        supportInvalidateOptionsMenu();
    }
}
