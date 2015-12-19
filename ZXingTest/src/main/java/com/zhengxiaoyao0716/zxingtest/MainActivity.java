package com.zhengxiaoyao0716.zxingtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zhengxiaoyao0716.zxingtest.net.LoginByScanResult;
import com.zhengxiaoyao0716.zxingtest.net.LoginHandler;
import com.zhengxiaoyao0716.zxingtest.zxingtest.R;
import com.zhengxiaoyao0716.zxingtest.zxingutil.ScanActivity;

public class MainActivity extends Activity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.nameInputET);

        Button scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode > 0)
        {
            String result = data.getStringExtra("result");
            LoginByScanResult.login(result, editText.getText().toString(), new LoginHandler(this));
        }
    }
}
