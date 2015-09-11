package com.wang.chatlib.presenter;

/**
 * Created by shawn on 9/9/15.
 */
public abstract class BaseUiPresenter<U extends BaseUiPresenter.Ui<UC>, UC>
        extends BasePresenter {

    public interface Ui<UC> {
        void setCallbacks(UC callbacks);
    }

    abstract UC createUiCallbacks();

    public synchronized final void attachUi(U ui){
        ui.setCallbacks( createUiCallbacks() );
    }

    public synchronized final void detachUi(U ui){
        ui.setCallbacks( null );
    }
}
