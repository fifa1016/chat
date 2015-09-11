package com.wang.chatlib.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import com.wang.chatlib.R;
import com.wang.chatlib.util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Set user's name and image.
 */
public class AccountFragment extends BaseFragment {
    private static final String TAG = "AccountFragment";

    // UI references.
    private AutoCompleteTextView mLoginIdView;
    private ImageView mAvatarView;
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
        String nickname = getLoginId();
        if (nickname == null || nickname.equals("")) {
            mLoginIdView.setText(NetworkUtil.getDeviceName());
        } else {
            mLoginIdView.setText(nickname);
        }

        mAvatarView = (ImageView) view.findViewById(R.id.avatar_image);
        Bitmap avatarBitmap = getAvatar();
        if (avatarBitmap != null) {
            mAvatarView.setImageBitmap(avatarBitmap);
        }
        mAvatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(v.getId());
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
                saveLoginId(id);
                onButtonPressed(view.getId());
            }
        });

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

    private static final String FILE_NAME = "avatar_bitmap";

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

    public void setAvatar(String path) {
        Log.d(TAG, "setAvatar() path="+path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Log.d(TAG," bitmap==null:"+ (bitmap==null) );
        mAvatarView.setImageBitmap(bitmap);
        saveAvatar(bitmap);
    }
    public void setAvatar( Bitmap bitmap ){
        mAvatarView.setImageBitmap(bitmap);
        saveAvatar(bitmap);
    }

    private void saveAvatar(Bitmap bitmap) {
        File file = new File(getActivity().getFilesDir(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();

            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
