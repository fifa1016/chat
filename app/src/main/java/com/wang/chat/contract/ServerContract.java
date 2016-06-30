package com.wang.chat.contract;

import com.wang.chat.presenter.BasePresenter;
import com.wang.chat.view.BaseView;

/**
 * Created by wang on 16-6-23.
 */
public interface ServerContract {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

    }
}
