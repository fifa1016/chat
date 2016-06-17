package com.wang.chat.presenter;

import com.wang.chat.contract.EditAccountContract;

/**
 * Created by wang on 16-6-17.
 */
public class EditAccountPresenter implements EditAccountContract.Presenter {

    private final EditAccountContract.View view;

    //TODO add datasource
    public EditAccountPresenter(EditAccountContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
