package com.zhengxiaoyao0716.zxingtest.zxingutil;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.zxing.client.android.camera.CameraConfigurationUtils;

import java.io.IOException;

/**
 * Created by zhengxiaoyao0716 on 2015/11/27.
 */
@SuppressWarnings("deprecation")    //为了兼容5.0以下设备
public class SurfaceCallback implements SurfaceHolder.Callback {
    private ScanActivity activity;
    private Camera camera;
    public void releaseCamera()
    {
        if (camera == null) return;
        camera.release();
        camera = null;
    }

    SurfaceCallback(ScanActivity activity)
    {
        this.activity = activity;
    }

    private long time;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        camera.setDisplayOrientation(90);

        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point screenResolution = new Point(display.getWidth(), display.getHeight());
        Point cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(camera.getParameters(), screenResolution);
        CameraManager.INSTANCE.setScreenResolution(screenResolution);
        CameraManager.INSTANCE.setCameraResolution(cameraResolution);

        final ScanHandler scanHandler = new ScanHandler(activity);
        camera.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                long time = System.currentTimeMillis();
                if (time  - SurfaceCallback.this.time < 1000) return;
                SurfaceCallback.this.time = time;
                Decoder.decode(data, scanHandler);
                camera.autoFocus(null);
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
            activity.setResult(0);
            activity.finish();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
