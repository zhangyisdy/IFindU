package lenovo.piedemo.bean;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Created by zhangyi on 16-4-25.
 */
public class ViewPagerInfo {

    public final String _tag;
    public final Class<?> _clss;
    public final Bundle _params;
    public final String _title;

    public ViewPagerInfo(String tag, Class<?> clss , Bundle params , String title){
        this._tag = tag;
        this._clss = clss;
        this._params = params;
        this._title = title;
    }

}
