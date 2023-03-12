package com.grozziie.testingApplications;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.print.PrintHelper;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.kerols.pdfconverter.CallBacks;
import com.kerols.pdfconverter.ImageToPdf;
import com.kerols.pdfconverter.PdfImageSetting;
import com.kerols.pdfconverter.PdfPage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Set;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

import static android.content.ContentValues.TAG;

public class AssenTaskDounwActivity extends AppCompatActivity {
    Uri imageuri;
    int flag = 0;
    BluetoothSocket m5ocket;
    BluetoothManager mBluetoothManager;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice device;
    ImageView imageposit;
    PdfPage pdfPage;
    Button printimageA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assen_task_dounw);
        imageposit = findViewById(R.id.imageposit);
        printimageA=findViewById(R.id.printimageA);
        printimageA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printImage();
            }
        });
        ///initilize Pdf Image

        /*
         pdfPage = new PdfPage(AssenTaskDounwActivity.this);
        pdfPage.setPageSize(1100,1100);
        PdfImageSetting mPdfImageSetting = new PdfImageSetting();
        // Custom Image Size
        mPdfImageSetting.setImageSize(200,200);

        mPdfImageSetting.setMargin(20,20,20,20);


        mPdfImageSetting.setImageSize(200,200);

        mPdfImageSetting.setMargin(20,20,20,20);

        // Setting for the second image on the page

        PdfImageSetting mPdfImageSetting2 = new PdfImageSetting();

        mPdfImageSetting2.setImageSize(100,100);

        mPdfImageSetting2.setMargin(220,220,220,220);
        pdfPage.add(mPdfImageSetting);
        pdfPage.add(mPdfImageSetting2);
         */


//        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagepost);
//        final byte[] bitmapGetByte = BitmapToRGBbyte(bitmap);
//        String BlueMac = "62:65:7A:5F:03:26";
//        mBluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
//        mBluetoothAdapter = mBluetoothManager.getAdapter();
//
//
//        if (mBluetoothAdapter.isEnabled()) {
//
//            try {
//                device = mBluetoothAdapter.getRemoteDevice(BlueMac);
//                m5ocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
//                m5ocket.connect();
//                if (m5ocket.isConnected()) {
//                    Toast.makeText(this, "Connectd with " + m5ocket.getRemoteDevice(), Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        ///  Toast.makeText(AssenTaskDounwActivity.this, "gfgfg", Toast.LENGTH_SHORT).show();
//                        if (m5ocket.isConnected()) {
//                            OutputStream os = null;
//                            os = m5ocket.getOutputStream();
//                            String t_line1 = "! 0 200 200 " + bitmap.getHeight() + " 1 \r\n";
//                            String t_line2 = "pw " + 384 + "\r\n";
//                            String t_line3 = "DENSITY 12\r\n";
//                            String t_line4 = "SPEED 3\r\n";
//                            String t_line5 = "CG " + 384 / 8 + " " + bitmap.getHeight() + " 0 0 ";
//                            String t_line6 = "PR 0\r\n";
//                            String t_line7 = "FORM\r\n";
//                            String t_line8 = "PRINT\r\n";
//                            String t_line9 = "\r\n";
//                            os.write(t_line1.getBytes());
//                            os.write(t_line2.getBytes());
//                            os.write(t_line3.getBytes());
//                            os.write(t_line4.getBytes());
//                            os.write(t_line5.getBytes());
//                            os.write(t_line4.getBytes());
//                            os.write(bitmapGetByte);
//                            os.write(t_line9.getBytes());
//                            os.write(t_line6.getBytes());
//                            os.write(t_line7.getBytes());
//                            os.write(t_line8.getBytes());
////                          os.flush();
////                          os.flush();
////                          m5ocket.close();
//                        } else {
//                            m5ocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
//                            m5ocket.connect();
//                            OutputStream os = null;
//                            os = m5ocket.getOutputStream();
//                            String t_line1 = "! 0 200 200 " + bitmap.getHeight() + " 1 \r\n";
//                            String t_line2 = "pw " + 384 + "\r\n";
//                            String t_line3 = "DENSITY 12\r\n";
//                            String t_line4 = "SPEED 3\r\n";
//                            String t_line5 = "CG " + 384 / 8 + " " + bitmap.getHeight() + " 0 0 ";
//                            String t_line6 = "PR 0\r\n";
//                            String t_line7 = "FORM\r\n";
//                            String t_line8 = "PRINT\r\n";
//                            String t_line9 = "\r\n";
//                            os.write(t_line1.getBytes());
//                            os.write(t_line2.getBytes());
//                            os.write(t_line3.getBytes());
//                            os.write(t_line4.getBytes());
//                            os.write(t_line5.getBytes());
//                            os.write(t_line4.getBytes());
//                            os.write(bitmapGetByte);
//                            os.write(t_line9.getBytes());
//                            os.write(t_line6.getBytes());
//                            os.write(t_line7.getBytes());
//                            os.write(t_line8.getBytes());
////                          os.flush();
////                          os.flush();
////                          m5ocket.close();
//                        }
//
//
//                    } catch (Exception e) {
//                        Log.e(TAG, "e" + e.getMessage());
//                        //Toast.makeText(AssenTaskDounwActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).start();
//
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
//            thread.start();
//            if (thread.getState() == Thread.State.NEW || thread.getState() == Thread.State.BLOCKED ||
//                    thread.getState() == Thread.State.TERMINATED) {
//                Toast.makeText(this, "" + thread.getState(), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "" + thread.getState(), Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            Toast.makeText(this, "Please active bluetooth", Toast.LENGTH_SHORT).show();
//        }


        ///   else {
        /// Toast.makeText(this, "ddddd", Toast.LENGTH_SHORT).show();


    }
    OutputStream os = null;
    private void printImage()
    {
        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.imagepost);
        final byte[] bitmapGetByte=BitmapToRGBbyte(bitmap);
        String BlueMac="62:65:7A:5F:03:26";
        mBluetoothManager= (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter=mBluetoothManager.getAdapter();//耿大爷获取蓝牙适配器
        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(BlueMac);
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m5ocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    m5ocket.connect();

                    os = m5ocket.getOutputStream();
                    int bitmapHeight=1080;
                    if(bitmap.getHeight()>bitmapHeight)
                    {
                        bitmapHeight=1080;
                    }
                    else
                    {
                        bitmapHeight=bitmap.getHeight();
                    }
                   /*
                    String t_line1 = "! 0 200 200 "+bitmapHeight+" 1 \r\n";//bitmap.getHeight()
                    String t_line2 = "pw "+384+"\r\n";
                    String t_line3 = "DENSITY 12\r\n";
                    String t_line4 = "SPEED 3\r\n";
                    String t_line5 = "CG "+384/8+" "+bitmapHeight+" 0 0 ";
                    String t_line6 ="PR 0\r\n";
                    String t_line7= "FORM\r\n";
                    String t_line8 = "PRINT\r\n";
                    String t_line9 = "\r\n";
                    os.write(t_line1.getBytes());
                    os.write(t_line2.getBytes());
                    os.write(t_line3.getBytes());
                    os .write(t_line4.getBytes());
                    os .write(t_line5.getBytes());
                    os .write(t_line4.getBytes());
                    os.write(bitmapGetByte);
                    os .write(t_line9.getBytes());
                    os .write(t_line6.getBytes());
                    os.write(t_line7.getBytes());
                    os.write(t_line8.getBytes());
                    */
                    String t_line1 = "! 0 200 200 "+bitmapHeight+" 1 \r\n";//bitmap.getHeight()
                    String t_line2 = "pw "+384+"\r\n";
                    String t_line3 = "DENSITY 12\r\n";
                    String t_line4 = "SPEED 3\r\n";
                    String t_line5 = "CG "+384/8+" "+bitmapHeight+" 0 0 ";
                    String t_line6 ="PR 0\r\n";
                    String t_line7= "FORM\r\n";
                    String t_line8 = "PRINT\r\n";
                    String t_line9 = "\r\n";
                    os.write(t_line1.getBytes());
                    os.write(t_line2.getBytes());
                    os.write(t_line3.getBytes());
                    os .write(t_line4.getBytes());
                    os .write(t_line5.getBytes());
                    os .write(t_line4.getBytes());
                    os.write(bitmapGetByte);
                    os .write(t_line9.getBytes());
                    os .write(t_line6.getBytes());
                    os.write(t_line7.getBytes());
                    os.write(t_line8.getBytes());
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           try {
                               os.flush();
                               m5ocket.close();
                           }catch (Exception e) {
                              e.printStackTrace();
                           }
                        }
                    },5000);


                } catch (IOException e) {
                    Log.e(TAG, "e");
                }
            }
        });
        thread.start();
    }


    private byte[]  BitmapToRGBbyte1(Bitmap bitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        Bitmap argbBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(argbBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int componentsPerPixel = 4;
        int totalPixels = width * height;
        int totalBytes = totalPixels * componentsPerPixel;

        byte[] rgbValues = new byte[totalBytes];
        @ColorInt int[] argbPixels = new int[totalPixels];
        bitmap.getPixels(argbPixels, 0, width, 0, 0, width, height);


        for (int i = 0; i < totalPixels; i++) {
            @ColorInt int argbPixel = argbPixels[i];
            int R = Color.red(argbPixel);
            int G = Color.green(argbPixel);
            int B = Color.blue(argbPixel);
            int alpha = Color.alpha(argbPixel);

            R=G=B=(int)((0.299*R)+(0.587*G)+(0.114*B));

            rgbValues[i * componentsPerPixel + 0] = (byte) R;
            rgbValues[i * componentsPerPixel + 1] = (byte) G;
            rgbValues[i * componentsPerPixel + 2] = (byte) B;

        }

        return rgbValues;
    }

    public void pickimage(View view) {
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(intent, 101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
    Uri bitmapUri;
    Bitmap mainimageBitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
          try {
              File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
              bitmapUri = data.getData();
      ///     converttToBitMap(bitmapUri);
              //convertUritoFile(bitmapUri);

           //resizeImage(mainBitma,mainBitma.getHeight(),mainBitma.getWidth());
              try {
                  Picasso.get().load(bitmapUri).into(imageposit);
              }catch (Exception e) {
                  Picasso.get().load(bitmapUri).into(imageposit);
              }
          }catch (Exception e2) {
              e2.printStackTrace();
          }
            //convertToPDF(bitmapUri);
            createImageToPDF(bitmapUri);
            //
            //  putdata_indatabase(bitmapUri);
        /*
            ImageToPdf imageToPdf = new ImageToPdf(pdfPage,AssenTaskDounwActivity.this);
            imageToPdf.DataToPDF(data,
                    new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                            "iMAGEtoPDF.pdf"), new CallBacks() {
                        @Override
                        public void onFinish(String path) {
                            Toast.makeText(getApplicationContext(),"onFinish",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            Toast.makeText(getApplicationContext(),"onError",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onError: ", throwable );
                        }

                        @Override
                        public void onProgress(int progress , int max) {
                            Log.e(TAG, "onProgress: " +  progress  + "  " +  max );

                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(getApplicationContext(),"onCancel",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onStart() {
                            Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_SHORT).show();

                        }
                    });
         */
        }

    }
    int PICK=12;
    boolean request=false;

    private void createImageToPDF(Uri bitmapUri) {

        if ((ContextCompat.checkSelfPermission(AssenTaskDounwActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(AssenTaskDounwActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)) {
            request=false;
            if ((ActivityCompat.shouldShowRequestPermissionRationale(AssenTaskDounwActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
                request=true;

            }
            else {
                ActivityCompat.requestPermissions(AssenTaskDounwActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PICK);
                request=true;
            }
            if ((ActivityCompat.shouldShowRequestPermissionRationale(AssenTaskDounwActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE))) {
                request=true;
            }
            else {
                ActivityCompat.requestPermissions(AssenTaskDounwActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PICK);
                request=true;
            }

        }
        else {

        }
        request=true;
        createdef(request);

    }

    private void createdef(boolean request) {
        if (request==true) {
           /*
            WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics=new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float height=displayMetrics.heightPixels;
            float width=displayMetrics.widthPixels;
            int convertHeirht=(int)height,comverWidth=(int)width;
            */
           try {
               Document document=new Document();
               File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Testing.pdf");
               FileOutputStream fileOutputStream=new FileOutputStream(file);
               PdfWriter.getInstance(document,fileOutputStream);
               document.open();
               Paragraph paragraph=new Paragraph("Hello It's me");
               document.add(paragraph);
               document.close();
               Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();


           }catch (Exception e) {
               Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
           }

            
        }
        else {
            Toast.makeText(this, "Must need memory permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertToPDF(Uri bitmapUri) {
        try {
            File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Bitmap imagedata=BitmapFactory.decodeStream(getContentResolver().openInputStream(bitmapUri));
            Document document=new Document();
            PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(path));
            document.open();
            Image image = Image.getInstance(String.valueOf(imagedata));
            document.add(image);
            document.close();
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

        }catch (Exception e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private File convertUritoFile(Uri bitmapUri) {
        try {
            String uuuid=UUID.randomUUID().toString();
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(bitmapUri);

            File file = new File(getCacheDir(), "temp_file"+uuuid);
            FileOutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();
            Toast.makeText(this, ""+file, Toast.LENGTH_SHORT).show();

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    Bitmap mainBitma;
    private Bitmap converttToBitMap(Uri bitmapUri) {
       try {
           //convert Uri to Bitmap
           ContentResolver contentResolver=getContentResolver();
           InputStream inputStream=contentResolver.openInputStream(bitmapUri);
            mainBitma=BitmapFactory.decodeStream(inputStream);

        ///Sevond Step
           //Resize taken image
           Bitmap BitmapOrg = mainBitma;
           int width_1 = BitmapOrg.getWidth();
           int height_1 = BitmapOrg.getHeight();
           int newWidth = mainBitma.getWidth();
           int newHeight = mainBitma.getHeight();
           final  float seale_1=(width_1)/newWidth;
           final  float seale_2=(height_1)/newHeight;
           ///convertToRGBScale(BitmapOrg,BitmapOrg.getWidth(),BitmapOrg.getHeight());


          /*
            Matrix matrix = new Matrix();
           matrix.postScale(seale_1, seale_2);
           Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 10,10, width_1,
                   height_1, matrix, true);
                     int bitwidth= Integer.parseInt(String.valueOf(seale_1));

           int bitheight= Integer.parseInt(String.valueOf(seale_2));
           Toast.makeText(this, "Done10", Toast.LENGTH_SHORT).show();
           Bitmap resizebit=Bitmap.createScaledBitmap(BitmapOrg,bitwidth,bitheight,false);
           */


           Toast.makeText(this, "Done10", Toast.LENGTH_SHORT).show();


        return mainBitma;



       }catch (Exception e) {
           e.printStackTrace();
return null;
       }

    }
//BitSet bitSet;
    private  byte[]  BitmapToRGBbyte(Bitmap bitmapOrg) {
        ArrayList<Byte> Gray_ArrayList;
        Gray_ArrayList =new ArrayList<Byte>();
        int height = 1080;
        if(bitmapOrg.getHeight()>height)
        {
            height=1080;
        }
        else
        {
            height=bitmapOrg.getHeight();
        }
        int width = 384;
        int R = 0, B = 0, G = 0;
        int pixles;
        int x = 0, y = 0;
        Byte[] Gray_Send;
        //bitSet = new BitSet();
        try {
            int k = 0;
            int Send_i = 0;
            int x_GetR;
            for (x = 0; x < height; x++) {
                k=0;
                for (y = 0; y < width; y++) {
                    pixles = bitmapOrg.getPixel(x, y);
                    R = Color.red(pixles);
                    G = Color.green(pixles);
                    B = Color.blue(pixles);
                    //setcolor
                    R = G = B = (int) ((0.299 * R) + (0.587 * G) + (0.114 * B));

                    if (R < 120) {
                        //bitSet.set(k);
                        x_GetR = 0;
                    } else {
                        x_GetR = 1;

                    }
                    ///texting cde
                    k++;
                    if (k == 1) {
                         Send_i=0;
                        Send_i = Send_i + x_GetR | 0x80;

                    } else if (k == 2) {

                        Send_i = Send_i + x_GetR | 0x40;

                    } else if (k == 3) {

                        Send_i = Send_i + x_GetR | 0x20;

                    } else if (k == 4) {

                        Send_i = Send_i + x_GetR | 0x10;

                    } else if (k == 5) {

                        Send_i = Send_i + x_GetR | 0x08;

                    } else if (k == 6) {

                        Send_i = Send_i + x_GetR | 0x04;

                    } else if (k == 7) {

                        Send_i = Send_i + x_GetR | 0x02;

                    } else if (k == 8) {

                        Send_i = Send_i + x_GetR | 0x01;

                        Gray_ArrayList.add((byte) Send_i);
                        Send_i = 0;
                        k = 0;
                       // byte b = (byte) Send_i; // replace with your byte value

                        //i control it


                    }


                    /////////////////////=====================================


                }
            }

            byte[] sss=new byte[Gray_ArrayList.size()];
              Gray_Send=new Byte[Gray_ArrayList.size()];
            Gray_ArrayList.toArray(Gray_Send);
            for(int xx=0;xx<Gray_Send.length;xx++){
                sss[xx]=Gray_Send[xx];
            }
            return  sss;
        } catch (Exception e) {

        }
        return null;
    }




    private void resizeImage(Bitmap mainBitma, int height, int width) {

        Bitmap BitmapOrg = mainBitma;
        int width_1 = BitmapOrg.getWidth();
        int height_1 = BitmapOrg.getHeight();
        int newWidth = width;
        int newHeight = height;
        final  double seale_1=(width_1)/newWidth;
        final  double seale_2=(height_1)/height;
        Matrix matrix=new Matrix();
        matrix.postScale((float)seale_1,(float) seale_2);
        Bitmap resizeBitmap=Bitmap.createBitmap(BitmapOrg,15,15,width_1,height_1,matrix,true);
        Toast.makeText(this, "SecondStepDone", Toast.LENGTH_SHORT).show();


    }


}