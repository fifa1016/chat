package com.wang.chatlib.display;

import android.app.Activity;
import android.content.Intent;
import com.wang.chatlib.Display;
import com.wang.chatlib.R;
import com.wang.chatlib.activity.AboutActivity;
import com.wang.chatlib.fragment.AboutFragment;

/**
 * Created by shawn on 9/9/15.
 */
public class AboutDisplay extends BaseDisplay {


    public AboutDisplay(Activity activity) {
        super(activity);
    }

    public void showAboutFragment() {
        AboutFragment fragment = new AboutFragment();
        mActivity.getFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, fragment)
                .commit();
    }

}
