package com.wang.chatlib.presenter;

import com.wang.chatlib.Display;

/**
 * Created by shawn on 9/9/15.
 */
public class MainPresenter extends BaseUiPresenter<MainPresenter.MainUi,
        MainPresenter.MainUiCallbacks>{

    @Override
    MainUiCallbacks createUiCallbacks() {
        return new MainUiCallbacks() {
        };
    }

    public interface MainUiCallbacks {}

    public interface MainUi extends BaseUiPresenter.Ui<MainUiCallbacks>{}

}
