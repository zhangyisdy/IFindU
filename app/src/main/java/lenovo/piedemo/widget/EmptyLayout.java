package lenovo.piedemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import lenovo.piedemo.R;

/**
 * Created by zhangyi on 16-4-27.
 */
public class EmptyLayout extends LinearLayout {

    private Context mContext;

    public EmptyLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext , R.layout.empty_layout , null);
    }
}
