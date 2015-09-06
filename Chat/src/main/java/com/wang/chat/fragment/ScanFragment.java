package com.wang.chat.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wang.chat.R;
import com.wang.chat.qrcode.ResultListener;
import com.wang.chat.qrcode.ResultHandler;
import com.wang.chat.view.CameraPreview;

/**
 * Created by shawn on 8/27/15.
 */
public class ScanFragment extends BaseFragment implements ResultListener {
    private static final String TAG = ScanFragment.class.getSimpleName();

    private CameraPreview mPreview;
    private View mMaskView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPreview = (CameraPreview)view.findViewById(R.id.scan_preview);
        mMaskView = view.findViewById(R.id.scan_mask);
        mPreview.setFocusAreaSize(300);


        if( mResultHandler == null ){
            mResultHandler = new ResultHandler( this );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mPreview.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreview.resume();
        //mPreview.setDecodeHandler( mResultHandler.getDecodeHandler() );
    }

    private ResultHandler mResultHandler;

    @Override
    public void onResult(String result){

    }

    @Override
    public void onDecodeHandlerReady(Handler handler) {
        Log.d(TAG,"onDecodeHandlerReady handler==null?"+(handler==null) );
        mPreview.setDecodeHandler( handler );
    }

    @Override
    public void requestPreviewFrame( Handler handler) {
        mPreview.setDecodeHandler( handler );
    }
}
