package lenovo.piedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangyi on 16-3-4.
 */
public class PieApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mContext = getApplicationContext();
    }
}
