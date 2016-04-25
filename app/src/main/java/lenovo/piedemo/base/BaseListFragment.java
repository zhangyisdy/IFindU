package lenovo.piedemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lenovo.piedemo.base.BaseFragment;

/**
 * Created by zhangyi on 16-3-8.
 */
public class BaseListFragment extends BaseFragment {

    public static String BUNDLE_KEY_CATALOG = "bundle_key_catalog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text = new TextView(getActivity());
        text.setText("111");
        //return super.onCreateView(inflater, container, savedInstanceState);
        return text;
    }

}
