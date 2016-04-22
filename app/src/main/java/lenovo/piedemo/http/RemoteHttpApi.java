package lenovo.piedemo.http;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by zhangyi on 16-4-21.
 */
public class RemoteHttpApi {

    public static void getTestHtml(String url , AsyncHttpResponseHandler handler){
        HttpUtils.get(url , handler);
    }

}
