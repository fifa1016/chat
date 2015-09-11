package com.wang.chat.display;

import android.app.Activity;
import android.content.Intent;
import com.wang.chat.Display;
import com.wang.chat.R;
import com.wang.chat.activity.AboutActivity;
import com.wang.chat.fragment.AboutFragment;

/**
 * Created by shawn on 9/9/15.
 */
public class AboutDisplay extends Display {


    public AboutDisplay(Activity activity) {
        super(activity);
    }

    public void showAboutFragment() {
        AboutFragment fragment = new AboutFragment();
        mActivity.getFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, fragment)
                .commit();
    }

    public void startAboutActivity() {
        mActivity.startActivity( new Intent(mActivity, AboutActivity.class));
    }


}
