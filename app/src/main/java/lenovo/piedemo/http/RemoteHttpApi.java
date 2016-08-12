package lenovo.piedemo.http;

import android.util.Log;

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
        HttpUtils.get("action/api/news_list",params,handler);
    }

    public static void getTestHtml(String url , AsyncHttpResponseHandler handler){
        HttpUtils.get(url , handler);
    }

    /**
     * 获取新闻明细
     *
     * @param id      新闻的id
     * @param handler
     */
    public static void getNewsDetail(int id, AsyncHttpResponseHandler handler) {
        Log.d("zhangyi" , "get News detail");
        RequestParams params = new RequestParams("id", id);
        HttpUtils.get("action/api/news_detail", params, handler);
    }

}
