package com.wang.chat.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wang.chat.R;
import com.wang.chat.presenter.AccountPresenter;
import com.wang.chat.view.fragment.AccountFragment;
import com.wang.chat.view.fragment.BaseFragment;
import com.wang.chat.view.fragment.ChooseFragment;
import com.wang.chat.view.fragment.ScanFragment;
import com.wang.chat.view.fragment.ServerFragment;

import java.security.Permission;

/**
 * Created by wang on 16-6-21.
 */
public class AndroidDisplay implements Display {

    private AppCompatActivity activity;

    public AndroidDisplay(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    @Override
    public void showAccount() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        AccountFragment frag = new AccountFragment();
        frag.setDisplay(this);
        AccountPresenter presenter = new AccountPresenter(frag);
        frag.setPresenter(presenter);

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
        if (Build.VERSION.SDK_INT >= 23
                &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Have no camera permission", Toast.LENGTH_SHORT)
                    .show();
            return;

        }

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        BaseFragment frag = new ScanFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("scan");
        transaction.commit();
    }
}
