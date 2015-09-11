package com.wang.chat.presenter;

import com.wang.chat.Display;

/**
 * Created by shawn on 9/9/15.
 */
public class MainPresenter  {

    Display mDisplay;

    private final AboutPresenter mAboutPresenter;
    public AboutPresenter getAboutPresenter(){
        return this.mAboutPresenter;
    }

    public MainPresenter(){
        mAboutPresenter = new AboutPresenter();
    }

    public void attachDisplay(Display display ){
        setDisplay(display);
    }
    public void detachDisplay(){
        setDisplay(null);
    }

    protected void setDisplay(Display display){
        mDisplay = display;
        //mAboutPresenter.setDisplay(display);
    }
}
