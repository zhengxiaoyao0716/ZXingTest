package com.zhengxiaoyao0716.zxingtest.zxingutil;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

import android.os.Message;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by zhengxiaoyao0716 on 2015/11/26.
 */
public final class Decoder {

    private static final HashMap<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>(3);
    static
    {
        Vector<BarcodeFormat> barcodeFormats = new Vector<BarcodeFormat>(1);
        barcodeFormats.add(BarcodeFormat.QR_CODE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, barcodeFormats);
    }

    /**
     * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
     * reuse the same reader objects from one decode to the next.
     *
     * @param data   The YUV preview frame.
     */
    static void decode(final byte[] data, final ScanHandler scanHandler) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                PlanarYUVLuminanceSource source = CameraManager.INSTANCE.buildLuminanceSource(data);
                if (source != null) {
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    Result rawResult = null;
                    MultiFormatReader multiFormatReader = new MultiFormatReader();
                    multiFormatReader.setHints(hints);
                    try {
                        rawResult = multiFormatReader.decodeWithState(bitmap);
                    } catch (ReaderException re) {
                        // continue
                    } finally {
                        multiFormatReader.reset();
                    }
                    if (rawResult != null) {
                        if (scanHandler != null) {
                            Message message = Message.obtain(scanHandler, 1, rawResult);
                            message.sendToTarget();
                        }
                    }
                }
            }
        }.start();
    }
}