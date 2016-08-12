package lenovo.piedemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import java.util.UUID;

import lenovo.piedemo.base.BaseApplication;
import lenovo.piedemo.config.AppConfig;
import lenovo.piedemo.http.HttpUtils;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

/**
 * Created by zhangyi on 16-4-21.
 */
public class AppContext extends BaseApplication {

    public final static int PAGE_SIZE = 20; // 默认分页大小

    private static AppContext mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        init();
    }
    public static AppContext getInstance() {
        return mInstance;
    }

    private void init(){
        /* init the http request */
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        HttpUtils.setHttpClient(client);
        HttpUtils.setCookie(HttpUtils.getCookie(this));
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public static boolean get(String key, boolean defValue) {
        //return getPreferences().getBoolean(key, defValue);
        return true;
    }

}
