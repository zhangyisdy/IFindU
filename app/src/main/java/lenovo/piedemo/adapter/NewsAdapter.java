package lenovo.piedemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lenovo.piedemo.R;
import lenovo.piedemo.bean.News;

/**
 * Created by zhangyi on 16-7-27.
 */
public class NewsAdapter extends ListBaseAdapter {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null || convertView.getTag() == null){

            convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.list_cell_news,null);

            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.description = (TextView) convertView.findViewById(R.id.tv_description);
            vh.source = (TextView) convertView.findViewById(R.id.tv_source);
            vh.time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.comment_count = (TextView) convertView.findViewById(R.id.tv_comment_count);
            vh.tip = (ImageView) convertView.findViewById(R.id.iv_tip);
            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        News news = (News) mDatas.get(position);

        vh.title.setText(news.getTitle());

        String description = news.getBody();
        vh.description.setVisibility(View.GONE);
        if (description != null && !description.isEmpty()) {
            vh.description.setVisibility(View.VISIBLE);
            vh.description.setText(description.trim());
        }

        vh.source.setText(news.getAuthor());
        vh.time.setText(news.getPubDate());
        vh.comment_count.setText(news.getCommentCount() + "");

        return convertView;
    }

    static class ViewHolder{
        TextView title;
        TextView description;
        TextView source;
        TextView time;
        TextView comment_count;
        ImageView tip;
    }
}
