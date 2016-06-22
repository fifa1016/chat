package com.wang.chat.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wang.chat.R;
import com.wang.chat.presenter.AccountPresenter;
import com.wang.chat.view.fragment.AccountFragment;
import com.wang.chat.view.fragment.BaseFragment;
import com.wang.chat.view.fragment.ChooseFragment;
import com.wang.chat.view.fragment.ServerFragment;

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
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

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
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        BaseFragment frag = new ChooseFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("choose");
        transaction.commit();
    }

    @Override
    public void showStartAsServer() {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        BaseFragment frag = new ServerFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("server");
        transaction.commit();
    }

    @Override
    public void showScanServer() {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        BaseFragment frag = new ChooseFragment();
        frag.setDisplay(this);
        transaction.replace(R.id.activity_container, frag);
        transaction.addToBackStack("scan");
        transaction.commit();
    }
}
