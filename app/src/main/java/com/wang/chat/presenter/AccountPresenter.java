package com.wang.chat.presenter;

import com.wang.chat.contract.AccountContract;

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
    public void start() {

    }
}
