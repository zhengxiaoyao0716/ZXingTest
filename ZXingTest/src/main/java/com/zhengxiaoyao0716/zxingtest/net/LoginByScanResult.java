package com.zhengxiaoyao0716.zxingtest.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by zhengxiaoyao0716 on 2015/11/28.
 */
public class LoginByScanResult {
    public static void login(final String result, final String name, final Handler handler) {
        final String[] keys = result.split("_");
        if (!keys[0].equals("XinManJing")) return;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message message = Message.obtain(handler);

                try {
                    message.what = HttpUtilities.loginPost(Integer.valueOf(keys[1]), name) ? 1 : -1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }
}
