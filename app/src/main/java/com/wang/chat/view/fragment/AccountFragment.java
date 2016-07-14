package com.wang.chat.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.wang.chat.R;
import com.wang.chat.contract.AccountContract;

import java.io.File;

/**
 * Set user's name and image.
 */
public class AccountFragment extends BaseFragment implements AccountContract.View {
    private static final String TAG = "AccountFragment";

    AccountContract.Presenter presenter;

    // UI references.
    private AutoCompleteTextView editId;
    private ImageView imgAvatar;
    private Button btnNext;


    public AccountFragment() {
        // Required empty public constructor
        Log.d(TAG, "AccountFragment: constructor");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public int getViewLayout() {
        return R.layout.fragment_account;
    }

    @Override
    public void initViews(View view) {
        // Set up the login form.
        editId = (AutoCompleteTextView) view.findViewById(R.id.login_id);

        imgAvatar = (ImageView) view.findViewById(R.id.avatar_image);

        imgAvatar.setOnClickListener(v -> {
            Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
            pickIntent.setType("image/*");
            startActivityForResult(pickIntent, REQUEST_CODE_PICK);
        });


        btnNext = (Button) view.findViewById(R.id.btn_account_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    editId.setError(getString(R.string.id_empty_error));
                    return;
                }
                presenter.saveLoginId(id);

                AccountFragment.this.getDisplay().showChoose();
            }
        });

    }

    @Override
    public void showLoginId(String id) {
        this.editId.setText(id);
    }


    @Override
    public void showAvatar(String path) {
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imgAvatar.setImageBitmap(bitmap);
        } else {
            Log.w(TAG, "showAvatar(): file not exists:" + path);
        }
    }


    private static final int REQUEST_CODE_PICK = 001;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: request code=" + requestCode + ",resultCode=" + resultCode);
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                if (data == null) {
                    Log.e(TAG, "onActivityResult: pick image, data null");
                    return;
                }
                Log.d(TAG, "onActivityResult: data.getData()=" + data.getDataString());
                presenter.saveAvatar(data.getData());

            }
        }
    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
