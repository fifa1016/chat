package com.wang.chat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.wang.chat.R;
import com.wang.chatlib.util.NetworkUtil;
import com.wang.chatlib.util.QRCodeGenerator;

/**
 * Created by shawn on 8/26/15.
 */
public class ServerFragment extends BaseFragment {

    private TextView mTxtIp;
    private EditText mEditPort;
    private ImageView mQrCode;

    @Override
    public int getViewLayout() {
        return R.layout.fragment_server;
    }

    @Override
    public void initViews(View view) {
        String ip = NetworkUtil.getIPV4Address();
        mTxtIp = (TextView) view.findViewById(R.id.server_ip);
        mTxtIp.setText( ip );

        mEditPort = (EditText) view.findViewById(R.id.server_port);
        mEditPort.setText("59999");
        String port = mEditPort.getText().toString();

        mQrCode = (ImageView)view.findViewById(R.id.server_qrcode);
        String serverInfo = "http://" + ip +":" + port;

        QRCodeGenerator.encodeToBitmap(serverInfo, mQrCode);
    }

    @Override
    public void initData() {

    }

}
