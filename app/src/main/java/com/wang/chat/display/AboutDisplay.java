package com.wang.chat.display;

import android.app.Activity;
import com.wang.chat.R;
import com.wang.chat.fragment.AboutFragment;

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
                .replace(R.id.activity_container, fragment)
                .commit();
    }

}
