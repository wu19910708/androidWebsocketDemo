package com.example.wxs.androidwebsocketdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by wxs on 16/8/17.
 *
 * 这个基类里面就是注册了一下service以及注销service
 * 里面有一个抽象方法,每次activity收到消息后都会调用这个抽象方法
 * 只要继承这个类的都能收到消息
 *
 *
 */
public abstract class BaseActivity extends Activity {


    private BroadcastReceiver imReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WebSocketService.WEBSOCKET_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String msg = bundle.getString("message");
                    if (!TextUtils.isEmpty(msg))
                        getMessage(msg);
                }

            }
        }
    };


    protected abstract void getMessage(String msg);

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(WebSocketService.WEBSOCKET_ACTION);
        registerReceiver(imReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(imReceiver);
    }
}
