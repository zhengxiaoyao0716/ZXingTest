package com.zhengxiaoyao0716.zxingtest.net;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtilities {
    private static final String URL_PATH = "http://123.57.72.138:5001/qrcodelogin/";

    public static boolean loginPost(int key, String name)
            throws IOException
    {
        HttpURLConnection connection
                = (HttpURLConnection)new URL(URL_PATH + "login").openConnection();
        connection.setConnectTimeout(6000);
        connection.setReadTimeout(12000);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST");

        //设置请求体的类型是文本类型
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Charset", "UTF-8");

        //获得输出流，向服务器写入数据
        byte[] contentBytes = String.format("key=%d&name=%s", key, name).getBytes("UTF-8");
        connection.getOutputStream().write(contentBytes);

        if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK)
            return false;

        ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
        byte[] bytePer = new byte[1024];
        int len;
        while((len = connection.getInputStream().read(bytePer)) != -1)
        {
            outPutStream.write(bytePer, 0, len);
        }
        connection.disconnect();
        return new String(outPutStream.toByteArray()).equals("succeed");
    }
}