package com.wang.chat.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.wang.chat.R;

/**
 * Created by shawn on 8/25/15.
 */
public class AboutActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView version = (TextView)findViewById(R.id.about_version);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version.setText(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInteract(int resourceId) {

    }
}
