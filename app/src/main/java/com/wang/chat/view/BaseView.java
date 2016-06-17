package com.wang.chat.view;

import com.wang.chat.presenter.BasePresenter;

/**
 * Created by wang on 16-6-16.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
