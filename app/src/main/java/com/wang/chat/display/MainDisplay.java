package com.wang.chat.display;

import android.app.Activity;
import com.wang.chat.R;
import com.wang.chat.fragment.MainFragment;

/**
 * Created by shawn on 9/11/15.
 */
public class MainDisplay extends BaseDisplay{
    public MainDisplay(Activity activity) {
        super(activity);
    }

    public void showMainFragment(){
        mActivity.getFragmentManager().beginTransaction()
                .replace(R.id.activity_container, new MainFragment() )
                .commit();
    }
}
