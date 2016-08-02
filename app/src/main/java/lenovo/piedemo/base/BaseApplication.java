package lenovo.piedemo.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by zhangyi on 16-4-21.
 */
public class BaseApplication extends Application {

    private static String PREF_NAME = "creativelocker.pref";
    private static Context mContext;
    private static Resources mResources;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mResources = mContext.getResources();
    }


    public static synchronized Context getContext(){return mContext;}

    public static synchronized Resources getResouece(){return mResources;}

}
