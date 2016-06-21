package com.wang.chat.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import com.wang.chat.R;
import com.wang.chat.view.fragment.*;

/**
 * A screen set user's login id & icon
 * <p>
 */
public class ConfigActivity extends BaseActivity {
    static final String TAG = "ConfigActivity";

    static final int REQUEST_CODE_PICK = 0x001;

    private AccountFragment mAccountFrag;
    private Fragment mChooseFrag, mServerFrag, mScanFrag;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_config;
    }

    @Override
    public void init() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (mAccountFrag == null) mAccountFrag = new AccountFragment();
        transaction.replace(R.id.layout_config, mAccountFrag);
        transaction.commit();
    }

    public void onInteract(int resId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        String tag = null;
        switch (resId) {
            case R.id.avatar_image:
                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.putExtra("crop", "true");
                pickIntent.putExtra("aspectX", 1);
                pickIntent.putExtra("aspectY", 1);
                pickIntent.putExtra("outputX", 200);
                pickIntent.putExtra("outputY", 200);
                pickIntent.putExtra("return-data", true);
                startActivityForResult(pickIntent, REQUEST_CODE_PICK);
                break;
            case R.id.login_button:
                tag = "choose";
                if (mChooseFrag == null) {
                    mChooseFrag = new ChooseFragment();
                }
                transaction.replace(R.id.layout_config, mChooseFrag, "choose");
//                InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                if( methodManager.isActive() ){
//                    try {
//                        methodManager.hideSoftInputFromWindow(
//                                getCurrentFocus().getWindowToken(),
//                                InputMethodManager.HIDE_NOT_ALWAYS);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }

                break;
            case R.id.start_server_button:
                tag = "server";
                if (mServerFrag == null) {
                    mServerFrag = new ServerFragment();
                }
                transaction.replace(R.id.layout_config, mServerFrag, "server");
                break;
            case R.id.scan_button:
                tag = "scan";
                if (mScanFrag == null) {
                    mScanFrag = new ScanFragment();
                }
                transaction.replace(R.id.layout_config, mScanFrag, "scan");
                break;
        }
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK) {
                Bitmap bitmap = data.getParcelableExtra("data");
                Log.d(TAG, "action=" + "," + data.getAction() + ", image=" + bitmap.toString() + ",data=" + data.getDataString());
                mAccountFrag.setAvatar(bitmap);
            }
        } else {
            Log.w(TAG, "get avatar error");
        }
    }
}
