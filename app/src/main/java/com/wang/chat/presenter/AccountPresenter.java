package com.wang.chat.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import com.wang.chat.contract.AccountContract;
import com.wang.chat.data.ProfileManager;

import java.io.InputStream;

/**
 * Created by wang on 16-6-17.
 */
public class AccountPresenter implements AccountContract.Presenter {

    private final AccountContract.View view;

    //TODO add datasource
    public AccountPresenter(AccountContract.View view) {
        this.view = view;
    }

    @Override
    public void saveAvatar(Uri uri) {
        ProfileManager.getInstance().saveAvatar(uri);
        view.showAvatar(ProfileManager.getInstance().getAvatarPath());
    }

    @Override
    public void loadProfile() {
        view.showAvatar(ProfileManager.getInstance().getAvatarPath());
        view.showLoginId(ProfileManager.getInstance().getLoginId());
    }

    @Override
    public void saveLoginId(String id) {
        ProfileManager.getInstance().saveLoginId(id);
    }

    @Override
    public void start() {
        loadProfile();
    }
}
