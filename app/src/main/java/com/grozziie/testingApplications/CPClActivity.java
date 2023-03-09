package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class CPClActivity extends AppCompatActivity {
    String uuidurl="00001101-0000-1000-8000-00805F9B34FB";
    String bluetoothaddress="F7:85:ED:28:B4:F0";
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_cl);
        try {
            bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
            bluetoothDevice=bluetoothAdapter.getRemoteDevice(bluetoothaddress);
            bluetoothSocket=bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuidurl));
            bluetoothSocket.connect();;
            if (bluetoothAdapter==null) {
                Toast.makeText(getApplicationContext(), "Please connected with bluetooth", Toast.LENGTH_SHORT).show();
            }
            else {
                if (bluetoothSocket.isConnected()) {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();

                }
            }
            try {

                OutputStream outputStream=bluetoothSocket.getOutputStream();
                byte[] printformat = {0x1B, 0x21, 12};
                outputStream.write(printformat);

                String t_line1 = "! 0 100 100 185 1\r\n";
                String t_line2 = "PCX 80 30\r\n";
                String t_line3 = "PRINT\r\n";
                Drawable d = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_cancel);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                PrintPic printPic = PrintPic.getInstance();
                printPic.init(bitmap);
                byte[] bitmapdata = printPic.printDraw();

                outputStream.write(t_line1.getBytes());
                outputStream.write(t_line2.getBytes());
                outputStream.write(bitmapdata);
                outputStream.write(t_line3.getBytes());
                outputStream.flush();

            } catch (Exception e) {
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }




        }catch (Exception e) {
            Toast.makeText(this, "Enum "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_cancel);
        resizeImage(bm,250,360);
    }
    public  Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        Toast.makeText(CPClActivity.this, "jhkkhjk", Toast.LENGTH_SHORT).show();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 10,10, width,
                height, matrix, true);
        return resizedBitmap;
    }
    private static byte[] StartBmpToPrintCode(Bitmap bitmap, int t) {
        byte temp = 0;
        int j = 7;
        int start = 0;
        if (bitmap != null) {
            int mWidth = bitmap.getWidth();
            int mHeight = bitmap.getHeight();

            int[] mIntArray = new int[mWidth * mHeight];
            byte[] data = new byte[mWidth * mHeight];
            bitmap.getPixels(mIntArray, 0, mWidth, 0, 0, mWidth, mHeight);
            encodeYUV420SP(data, mIntArray, mWidth, mHeight, t);
            byte[] result = new byte[mWidth * mHeight / 8];
            for (int i = 0; i < mWidth * mHeight; i++) {
                temp = (byte) ((byte) (data[i] << j) + temp);
                j--;
                if (j < 0) {
                    j = 7;
                }
                if (i % 8 == 7) {
                    result[start++] = temp;
                    temp = 0;
                }
            }
            if (j != 7) {
                result[start++] = temp;
            }

            int aHeight = 24 - mHeight % 24;
            byte[] add = new byte[aHeight * 48];
            byte[] nresult = new byte[mWidth * mHeight / 8 + aHeight * 48];
            System.arraycopy(result, 0, nresult, 0, result.length);
            System.arraycopy(add, 0, nresult, result.length, add.length);

            byte[] byteContent = new byte[(mWidth / 8 + 4)
                    * (mHeight + aHeight)];// ´òÓ¡Êý×é
            byte[] bytehead = new byte[4];// Ã¿ÐÐ´òÓ¡Í·
            bytehead[0] = (byte) 0x1f;
            bytehead[1] = (byte) 0x10;
            bytehead[2] = (byte) (mWidth / 8);
            bytehead[3] = (byte) 0x00;
            for (int index = 0; index < mHeight + aHeight; index++) {
                System.arraycopy(bytehead, 0, byteContent, index * 52, 4);
                System.arraycopy(nresult, index * 48, byteContent,
                        index * 52 + 4, 48);

            }
            return byteContent;
        }
        return null;

    }
    private void printImage() {
        try {


        }catch (Exception e) {
        }
    }
    public static void encodeYUV420SP(byte[] yuv420sp, int[] rgba, int width,
                                      int height, int t) {
        final int frameSize = width * height;
        int[] U, V;
        U = new int[frameSize];
        V = new int[frameSize];
        final int uvwidth = width / 2;
        int r, g, b, y, u, v;
        int bits = 8;
        int index = 0;
        int f = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                r = (rgba[index] & 0xff000000) >> 24;
                g = (rgba[index] & 0xff0000) >> 16;
                b = (rgba[index] & 0xff00) >> 8;
                // rgb to yuv
                y = ((66 * r + 129 * g + 25 * b + 128) >> 8) + 16;
                u = ((-38 * r - 74 * g + 112 * b + 128) >> 8) + 128;
                v = ((112 * r - 94 * g - 18 * b + 128) >> 8) + 128;
                // clip y
                // yuv420sp[index++] = (byte) ((y < 0) ? 0 : ((y > 255) ? 255 :
                // y));
                byte temp = (byte) ((y < 0) ? 0 : ((y > 255) ? 255 : y));
                if (t == 0) {
                    yuv420sp[index++] = temp > 0 ? (byte) 1 : (byte) 0;
                } else {
                    yuv420sp[index++] = temp > 0 ? (byte) 0 : (byte) 1;
                }

                // {
                // if (f == 0) {
                // yuv420sp[index++] = 0;
                // f = 1;
                // } else {
                // yuv420sp[index++] = 1;
                // f = 0;
                // }

                // }

            }

        }
        f = 0;
    }
}