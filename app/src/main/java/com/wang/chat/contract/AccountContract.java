package com.wang.chat.contract;

import android.net.Uri;

import com.wang.chat.view.BaseView;
import com.wang.chat.presenter.BasePresenter;

import java.io.InputStream;

/**
 * Created by wang on 16-6-17.
 */
public interface AccountContract {
    interface View extends BaseView<Presenter> {
        void showAvatar(String path);

        void showLoginId(String id);
    }


    interface Presenter extends BasePresenter {
        void saveAvatar(Uri uri);

        void saveLoginId(String id);

        void loadProfile();
    }
}
