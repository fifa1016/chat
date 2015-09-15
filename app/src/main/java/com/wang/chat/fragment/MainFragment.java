package com.wang.chat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wang.chat.R;
import com.wang.chatlib.presenter.MainPresenter;
import com.wang.chat.view.FootView;
import com.wang.chat.view.HeadView;

/**
 * Created by shawn on 9/12/15.
 */
public class MainFragment extends BaseFragment<MainPresenter.MainUiCallbacks> {

    private MainPresenter.MainUiCallbacks mCallbacks;

    @Override
    public void setCallbacks(MainPresenter.MainUiCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Bind(R.id.head)
    HeadView mHeadView;

    @Bind(R.id.foot)
    FootView mFootView;

    @OnClick({R.id.btnChats, R.id.btnContacts, R.id.btnSettings})
    public void changeTab(ImageButton button){
        switch (button.getId()){
            case R.id.btnChats:
                break;
            case R.id.btnContacts:
                break;
            case R.id.btnSettings:
                break;
        }
    }


}
