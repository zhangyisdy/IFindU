package lenovo.piedemo.fragment;

import java.io.InputStream;

import lenovo.piedemo.base.BaseFragment;
import lenovo.piedemo.bean.News;
import lenovo.piedemo.bean.NewsDetail;
import lenovo.piedemo.http.RemoteHttpApi;
import lenovo.piedemo.util.StringUtils;
import lenovo.piedemo.util.ThemeSwitchUtils;
import lenovo.piedemo.util.UIHelper;
import lenovo.piedemo.util.XmlUtils;

/**
 * Created by zhangyi on 16-8-11.
 */
public class NewsDetailFragment extends CommonDetailFragment<News>{

    @Override
    protected String getWebViewBody(News detail) {

        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = StringUtils.friendly_time(detail.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorId(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(UIHelper.setHtmlCotentSupportImagePreview(detail.getBody()));


        // 更多关于***软件的信息
        String softwareName = detail.getSoftwareName();
        String softwareLink = detail.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div class='oschina_software' style='margin-top:8px;font-weight:bold'>更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (detail != null && detail.getRelatives() != null
                && detail.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : detail.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return  body.toString();
    }

    @Override
    protected void sendRequestDataForNet(int id) {
        RemoteHttpApi.getNewsDetail(id, mDetailHandler);
    }

    @Override
    protected News parseData(InputStream is) {
        return XmlUtils.toBean(NewsDetail.class , is).getNews();
    }
}
