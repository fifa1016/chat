package com.wang.chatlib.presenter;

/**
 * Created by shawn on 9/9/15.
 */
public class AboutPresenter extends BaseUiPresenter<AboutPresenter.AboutUi,
        AboutPresenter.AboutUiCallbacks> {


    public interface AboutUi extends BaseUiPresenter.Ui<AboutUiCallbacks> {

    }

    public interface AboutUiCallbacks {

    }

    @Override
    protected AboutUiCallbacks createUiCallbacks() {
        return new AboutUiCallbacks(){

        };
    }
}
