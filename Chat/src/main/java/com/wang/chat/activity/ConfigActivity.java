package com.wang.chat.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import com.wang.chat.R;
import com.wang.chat.fragment.*;

/**
 * A screen set user's login id & icon
 * <p/>
 */
public class ConfigActivity extends BaseActivity
        implements OnInteractListener {

    private Fragment mAccountFrag;
    private Fragment mChooseFrag, mServerFrag, mScanFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        mAccountFrag = new AccountFragment();
        transaction.replace(R.id.layout_config, mAccountFrag, "account");
        transaction.commit();
    }

    @Override
    public void onInteract( int resId ) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        String tag = null;
        switch (resId){
            case R.id.login_button:
                tag = "choose";
                if( mChooseFrag == null ){
                    mChooseFrag = new ChooseFragment();
                }
                transaction.replace(R.id.layout_config, mChooseFrag, "choose");
                break;
            case R.id.start_server_button:
                tag = "server";
                if( mServerFrag == null ){
                    mServerFrag = new ServerFragment();
                }
                transaction.replace(R.id.layout_config, mServerFrag, "server");
                break;
            case R.id.scan_button:
                tag = "scan";
                if( mScanFrag == null ){
                    mScanFrag = new ScanFragment();
                }
                transaction.replace(R.id.layout_config, mScanFrag, "scan");
                break;
        }
        transaction.addToBackStack(tag);
        transaction.commit();
    }


}
