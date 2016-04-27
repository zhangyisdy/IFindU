package lenovo.piedemo.http;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import lenovo.piedemo.AppContext;
import lenovo.piedemo.bean.NewsList;
import lenovo.piedemo.config.AppConfig;

/**
 * Created by zhangyi on 16-4-21.
 */
public class RemoteHttpApi {

    public static void getNewsList(int catalog , int page , AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.put("catalog" , catalog);
        params.put("pageIndex" , page);
        params.put("pageSize" , AppContext.PAGE_SIZE);
        if(catalog == NewsList.CATALOG_WEEK){
            params.put("show","week");
        }else if(catalog == NewsList.CATALOG_MONTH){
            params.put("show","month");
        }
        HttpUtils.get("action/api/news_list" , params , handler);
    }

    public static void getTestHtml(String url , AsyncHttpResponseHandler handler){
        HttpUtils.get(url , handler);
    }

}
