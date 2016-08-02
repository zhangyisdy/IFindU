package lenovo.piedemo.http;

import android.os.Build;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.client.params.ClientPNames;
import lenovo.piedemo.AppContext;

/**
 * Created by zhangyi on 16-4-21.
 */
public class HttpUtils {

    private static AsyncHttpClient mHttpClient;
    private final static String HOST = "www.oschina.net";
    private final static String API_URL = "http://www.oschina.net/%s";
    private static String appCookie;


    /**
     *  get user agent for http request
     * @return
     */
    private static String getUserAgent(){
        AppContext appContext = AppContext.getInstance();
        StringBuilder sb = new StringBuilder("OSCHAINA.NET");
        sb.append("/" + appContext.getPackageInfo().versionName + "_"
                + appContext.getPackageInfo().versionCode); // app version info
        sb.append("/Android");
        sb.append("/" + Build.VERSION.RELEASE);  // android os version
        sb.append("/" + Build.MODEL);    // phone model
        sb.append("/" + appContext.getAppId()); // get app uuid
        return sb.toString();
    }

    /**
     * http get function
     * @param url
     * @param handler
     */
    public static void get(String url , AsyncHttpResponseHandler handler){
        mHttpClient.get(getAbsolutelyUrl(url), handler);
    }

    /**
     * http get function
     * @param url
     * @param params
     * @param handler
     */
    public static void get(String url , RequestParams params , AsyncHttpResponseHandler handler){
        mHttpClient.get(getAbsolutelyUrl(url) , params , handler);
    }

    public static void setHttpClient(AsyncHttpClient client){
        mHttpClient = client;
        mHttpClient.addHeader("Accept-Language", Locale.getDefault().getLanguage());
        //mHttpClient.addHeader("HOST", HOST);
        //mHttpClient.addHeader("Connection", "Keep-Alive");
        mHttpClient.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

        /* set user agent */
        //mHttpClient.setUserAgent(getUserAgent());

    }

    public static void setCookie(String cookie){
        mHttpClient.addHeader("cookie",cookie);
    }

    public static String getCookie(AppContext appContext) {
        if (appCookie == null || appCookie == "") {
            appCookie = appContext.getProperty("cookie");
        }
        return appCookie;
    }

    /*
     *get absolutely url
     * */
    private static String getAbsolutelyUrl(String url){
        String destUrl = url;
        if (!url.startsWith("http:") && !url.startsWith("https:")) {
            destUrl = String.format(API_URL, url);
        }
        Log.d("BASE_CLIENT", "request:" + destUrl);
        return destUrl;
    }

}
