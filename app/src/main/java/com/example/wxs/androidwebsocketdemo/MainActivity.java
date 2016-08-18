package com.example.wxs.androidwebsocketdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener{


    private Button connectBtn;
    private Button disconnectBtn;
    private TextView messageTv;
    private EditText sendMsgEdit;
    private Button sendMsgBtn;
    private Intent websocketServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        websocketServiceIntent = new Intent(this, WebSocketService.class);
        startService(websocketServiceIntent);
        findViews();
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connect_btn:
                WebSocketService.webSocketConnect();
                break;

            case R.id.disconnect_btn:
                WebSocketService.closeWebsocket(false);
                break;

            case R.id.send_msg_btn:
                WebSocketService.sendMsg(sendMsgEdit.getText().toString());
                break;
        }
    }


    private void findViews(){
        connectBtn = (Button)findViewById(R.id.connect_btn);
        disconnectBtn = (Button)findViewById(R.id.disconnect_btn);
        messageTv = (TextView)findViewById(R.id.message_tv);
        sendMsgEdit = (EditText)findViewById(R.id.send_msg_edit);
        sendMsgBtn = (Button)findViewById(R.id.send_msg_btn);
    }

    private void initViews(){
        connectBtn.setOnClickListener(this);
        disconnectBtn.setOnClickListener(this);
        sendMsgBtn.setOnClickListener(this);
    }


    @Override
    protected void getMessage(String msg) {
        messageTv.setText("");
        messageTv.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketService.closeWebsocket(true);
        stopService(websocketServiceIntent);
    }
}
