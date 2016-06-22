package com.wang.chat.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.wang.chatlib.util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Set user's name and image.
 */
public class AccountFragment extends BaseFragment implements AccountContract.View {
    private static final String TAG = "AccountFragment";

    AccountContract.Presenter presenter;

    // UI references.
    private AutoCompleteTextView loginIdView;
    private ImageView avatarView;
    private Button btnNext;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public int getViewLayout() {
        return R.layout.fragment_account;
    }

    @Override
    public void initViews(View view) {
        // Set up the login form.
        loginIdView = (AutoCompleteTextView) view.findViewById(R.id.login_id);
        String nickname = getLoginId();
        if (nickname == null || nickname.equals("")) {
            loginIdView.setText(NetworkUtil.getDeviceName());
        } else {
            loginIdView.setText(nickname);
        }

        avatarView = (ImageView) view.findViewById(R.id.avatar_image);
        Bitmap avatarBitmap = getAvatar();
        if (avatarBitmap != null) {
            avatarView.setImageBitmap(avatarBitmap);
        }
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
                pickIntent.setType("image/*");
                startActivityForResult(pickIntent, REQUEST_CODE_PICK);
            }
        });

        btnNext = (Button) view.findViewById(R.id.btn_account_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = loginIdView.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    loginIdView.setError(getString(R.string.id_empty_error));
                    return;
                }
                saveLoginId(id);

                AccountFragment.this.getDisplay().showChoose();
            }
        });

    }

    @Override
    public void initData() {

    }

    private static final String PREF_NICKNAME = "nickname";

    private String getLoginId() {
        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sp.getString(PREF_NICKNAME, "");
    }

    private void saveLoginId(String nickname) {
        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREF_NICKNAME, nickname);
        editor.commit();
    }

    @Override
    public void showLoginId() {

    }

    private static final String FILE_NAME = "avatar_bitmap";


    @Override
    public void showAvatar() {

    }

    private Bitmap getAvatar() {
        File file = new File(getActivity().getFilesDir(), FILE_NAME);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(FILE_NAME);
            return bitmap;
        } else {
            Log.w(TAG, "file not exits:" + FILE_NAME);
            return null;
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
                }
                Log.d(TAG, "onActivityResult: data.getData()=" + data.getDataString());
                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                    saveAvatar(inputStream);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (bitmap != null) {
                        avatarView.setImageBitmap(bitmap);
                    } else {
                        Log.d(TAG, "onActivityResult: bitmap null!");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private void saveAvatar(InputStream inputStream) {
        File file = new File(getActivity().getFilesDir(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
