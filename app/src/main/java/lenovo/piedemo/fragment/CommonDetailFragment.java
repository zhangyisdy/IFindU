package lenovo.piedemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import cz.msebera.android.httpclient.Header;
import lenovo.piedemo.R;
import lenovo.piedemo.base.BaseFragment;
import lenovo.piedemo.ui.DetailActivity;
import lenovo.piedemo.util.TDevice;
import lenovo.piedemo.util.UIHelper;
import lenovo.piedemo.widget.EmptyLayout;

/**
 * Created by zhangyi on 16-8-11.
 */
public abstract class CommonDetailFragment<T extends Serializable> extends BaseFragment {

    protected int mCommentCount;
    protected int mId;
    protected EmptyLayout mEmptyLayout;
    protected WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,
                false);
        mCommentCount = getActivity().getIntent().getIntExtra("comment_count", 0);
        mId = getActivity().getIntent().getIntExtra("id", 0);
        initView(view);
        initData();
        requestData(false);
        return view;
    }

    protected void requestData(boolean b) {
        if(TDevice.hasInternet()){
            sendRequestDataForNet(mId);
        }
    }

    protected void initData() {
    }

    protected void initView(View view) {
        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
        mWebView = (WebView) view.findViewById(R.id.webview);
        UIHelper.initWebView(mWebView);
    }



    protected void setCommentCount(int commentCount) {
//        ((DetailActivity) getActivity()).toolFragment
//                .setCommentCount(commentCount);
    }

    protected AsyncHttpResponseHandler mDetailHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Log.d("zhangyi", "detail11 is:"+responseBody.length);
            T detail = parseData(new ByteArrayInputStream(responseBody));
            Log.d("zhangyi", "detail is:"+detail);
            if(detail != null){
                mEmptyLayout.setVisibility(View.GONE);
                executeOnLoadDataSuccess(detail);
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    };

    private void executeOnLoadDataSuccess(T detail) {
        if(detail != null && mWebView != null){
            mWebView.loadDataWithBaseURL("",getWebViewBody(detail),"text/html", "UTF-8", "");
            mWebView.loadUrl("javascript:showMidSize()");
            Log.d("zhangyi","executeOnLoadDataSuccess");
        }
    }

    protected abstract String getWebViewBody(T detail);

    protected abstract void sendRequestDataForNet(int id);

    protected abstract T parseData(InputStream is);


}
