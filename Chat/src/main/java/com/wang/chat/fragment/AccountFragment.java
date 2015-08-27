package com.wang.chat.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import com.wang.chat.R;
import com.wang.chat.util.Util;

/**
 * Set user's name and image.
 */
public class AccountFragment extends BaseFragment {
    private static final String TAG = "AccountFragment";

    // UI references.
    private AutoCompleteTextView mLoginIdView;
    private ImageView mIconView;
    private View mProgressView;
    private Button mLoginButton;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up the login form.
        mLoginIdView = (AutoCompleteTextView) view.findViewById(R.id.login_id);
        mLoginIdView.setText(Util.getDeviceName());

        mIconView = (ImageView) view.findViewById(R.id.login_icon);
        mIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLoginButton = (Button) view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mLoginIdView.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    mLoginIdView.setError(getString(R.string.id_empty_error));
                    return;
                }
                onButtonPressed(view.getId());
            }
        });

        mProgressView = view.findViewById(R.id.login_progress);
    }

    // TODO
    private String getLoginId() {
        return "";
    }
}
