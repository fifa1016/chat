package com.wang.chat.contract;

import com.wang.chat.view.BaseView;
import com.wang.chat.presenter.BasePresenter;

/**
 * Created by wang on 16-6-17.
 */
public interface AccountContract {
    interface View extends BaseView<Presenter> {
        void showAvatar();

        void showLoginId();
    }


    interface Presenter extends BasePresenter {
        void saveAvatar();

        void getAvatar();

        void saveLoginId();

        void getLoginId();
    }
}
