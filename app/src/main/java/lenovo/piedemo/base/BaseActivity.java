package lenovo.piedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import lenovo.piedemo.R;
import lenovo.piedemo.util.StringUtils;

/**
 * Created by zhangyi on 16-8-11.
 */
public class BaseActivity extends AppCompatActivity {

    protected ActionBar mActionBar;
    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getLayoutId() != 0){
            setContentView(getLayoutId());
        }

        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        if(hasActionBar()){
            initActionBar(mActionBar);
        }

        init(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected void initActionBar(ActionBar actionBar) {
        Log.d("zhangyi"," actionbar is:"+actionBar);
        if(actionBar == null) {
            return;
        }

        if(hasBackButton()){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        }else{
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int title_res = getActionBarTitleRes();
            if(title_res != 0){
                mActionBar.setTitle(title_res);
            }
        }
    }

    public void setActionBarTitle(int resId){
        if(resId != 0){
            setActionBarTitle(getString(resId));
        }
    }

    public void setActionBarTitle(String title){
        if(StringUtils.isEmpty(title)){
            title = getString(R.string.app_name);
        }
        mActionBar.setTitle(title);
    }

    protected boolean hasActionBar() {
        return getSupportActionBar() != null;
    }

    protected int getActionBarTitleRes() {
        return 0;
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected int getLayoutId(){
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
