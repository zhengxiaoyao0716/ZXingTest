package com.zhengxiaoyao0716.zxingtest.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by zhengxiaoyao0716 on 2015/11/28.
 */
public class LoginHandler extends Handler{
    private Activity activity;
    public LoginHandler(Activity activity) { this.activity = activity; }
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        switch (msg.what)
        {
            case 0 :
                Toast.makeText(activity, "Internet error.", Toast.LENGTH_LONG).show();
                break;
            case 1 :
                Toast.makeText(activity, "Login succeed.", Toast.LENGTH_LONG).show();
                break;
            case -1 :
                Toast.makeText(activity, "Login failed..", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
