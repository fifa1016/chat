package com.wang.chat.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.wang.chat.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Log.d(TAG, "onTabSelected: tab id=" + tabId);
                if (tabId == R.id.tab_address) {

                } else if (tabId == R.id.tab_home) {

                } else if (tabId == R.id.tab_network) {

                } else if (tabId == R.id.tab_me) {

                }
            }
        });


    }

}
