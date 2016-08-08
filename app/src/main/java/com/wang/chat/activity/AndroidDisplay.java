package com.wang.chat.activity;

import android.Manifest;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.wang.chat.R;
import com.wang.chat.presenter.AccountPresenter;
import com.wang.chat.view.fragment.AccountFragment;
import com.wang.chat.view.fragment.BaseFragment;
import com.wang.chat.view.fragment.ChooseFragment;
import com.wang.chat.view.fragment.ScanFragment;
import com.wang.chat.view.fragment.ServerFragment;


/**
 * Created by wang on 16-6-21.
 */
public class AndroidDisplay implements Display {
    private static final String TAG = "AndroidDisplay";

    private AppCompatActivity activity;

    public AndroidDisplay(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    @Override
    public void showAccount() {
        Log.d(TAG, "showAccount: ");
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        AccountFragment frag = new AccountFragment();
        AccountPresenter presenter = new AccountPresenter(frag);
        frag.setPresenter(presenter);
        frag.setDisplay(this);

        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("account");
        transaction.commit();
    }

    @Override
    public void showChoose() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        BaseFragment frag = new ChooseFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("choose");
        transaction.commit();
    }

    @Override
    public void showStartAsServer() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        BaseFragment frag = new ServerFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("server");
        transaction.commit();
    }

    @Override
    public void showScanServer() {
        RxPermissions.getInstance(activity)
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        Log.d(TAG, "call: camera granted!");
                        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                        BaseFragment frag = new ScanFragment();
                        frag.setDisplay(AndroidDisplay.this);
                        transaction.replace(R.id.activity_container, frag);
                        transaction.addToBackStack("scan");
                        transaction.commit();
                    } else {
                        Log.d(TAG, "call: camera NOT granted");
                        Toast.makeText(activity, "Have no camera permission", Toast.LENGTH_SHORT)
                                .show();
                    }
                }//Action1
                );

    }
}
