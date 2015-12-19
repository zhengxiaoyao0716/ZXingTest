package com.zhengxiaoyao0716.zxingtest.zxingutil;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.google.zxing.Result;

/**
 * Created by zhengxiaoyao0716 on 2015/11/27.
 */
public class ScanHandler extends Handler {
    private ScanActivity activity;
    public ScanHandler(ScanActivity activity) { this.activity = activity; }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        Result result = (Result) msg.obj;
        Intent intent = activity.getIntent().putExtra("result", result.getText());
        activity.setResult(1, intent);
        activity.finish();
    }
}
