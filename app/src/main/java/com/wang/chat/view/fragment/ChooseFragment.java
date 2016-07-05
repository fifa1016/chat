package com.wang.chat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wang.chat.R;
import com.wang.chat.contract.ChooseContract;

/**
 * Created by shawn on 8/26/15.
 */
public class ChooseFragment extends BaseFragment implements ChooseContract.View {
    private static final String TAG = "ChooseFragment";

    private Button mStartServerButton, mScanButton;

    @Override
    public int getViewLayout() {
        return R.layout.fragment_choose;
    }

    @Override
    public void initViews(View view) {
        mStartServerButton = (Button) view.findViewById(R.id.start_server_button);
        mStartServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFragment.this.getDisplay().showStartAsServer();
            }
        });
        mScanButton = (Button) view.findViewById(R.id.scan_button);
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFragment.this.getDisplay().showScanServer();
            }
        });

    }


    @Override
    public void setPresenter(ChooseContract.Presenter presenter) {

    }
}
