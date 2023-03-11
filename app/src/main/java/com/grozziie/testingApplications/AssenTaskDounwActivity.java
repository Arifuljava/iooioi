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
import android.provider.DocumentsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Set;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

import static android.content.ContentValues.TAG;

public class AssenTaskDounwActivity extends AppCompatActivity {
Uri imageuri;
int flag=0;
BluetoothSocket m5ocket;
BluetoothManager  mBluetoothManager;
BluetoothAdapter mBluetoothAdapter;
     BluetoothDevice device;
     ImageView imageposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assen_task_dounw);
        imageposit=findViewById(R.id.imageposit);

        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.imagepost);
       final byte[] bitmapGetByte=BitmapToRGBbyte(bitmap);
        String BlueMac="F7:85:ED:28:B4:F0";
        mBluetoothManager= (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter=mBluetoothManager.getAdapter();


        if (mBluetoothAdapter.isEnabled()) {

          try {
                device = mBluetoothAdapter.getRemoteDevice(BlueMac);
              m5ocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
              m5ocket.connect();
              if (m5ocket.isConnected()) {
                  Toast.makeText(this, "Connectd with "+m5ocket.getRemoteDevice(), Toast.LENGTH_SHORT).show();
              }
          }catch (Exception e) {
              Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
          }
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      ///  Toast.makeText(AssenTaskDounwActivity.this, "gfgfg", Toast.LENGTH_SHORT).show();
                      if (m5ocket.isConnected()) {
                          OutputStream os = null;
                          os = m5ocket.getOutputStream();
                          String t_line1 = "! 0 200 200 "+bitmap.getHeight()+" 1 \r\n";
                          String t_line2 = "pw "+384+"\r\n";
                          String t_line3 = "DENSITY 12\r\n";
                          String t_line4 = "SPEED 3\r\n";
                          String t_line5 = "CG "+384/8+" "+bitmap.getHeight()+" 0 0 ";
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
                          os.flush();
                          os.flush();
                          m5ocket.close();
                      }
                      else {
                          m5ocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                          m5ocket.connect();
                          OutputStream os = null;
                          os = m5ocket.getOutputStream();
                          String t_line1 = "! 0 200 200 "+bitmap.getHeight()+" 1 \r\n";
                          String t_line2 = "pw "+384+"\r\n";
                          String t_line3 = "DENSITY 12\r\n";
                          String t_line4 = "SPEED 3\r\n";
                          String t_line5 = "CG "+384/8+" "+bitmap.getHeight()+" 0 0 ";
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
                          os.flush();
                          os.flush();
                          m5ocket.close();
                      }


                  } catch (Exception e) {
                      Log.e(TAG, "e"+e.getMessage());
                      //Toast.makeText(AssenTaskDounwActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
          }).start();

            Thread thread =new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            thread.start();
            if (thread.getState()==Thread.State.NEW||thread.getState()==Thread.State.BLOCKED||
                    thread.getState()==Thread.State.TERMINATED) {
                Toast.makeText(this, ""+thread.getState(), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, ""+thread.getState(), Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this, "Please active bluetooth", Toast.LENGTH_SHORT).show();
        }

      /*
      try {
          BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
          Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

          for (BluetoothDevice device : pairedDevices) {
              String deviceName = device.getName();
              String deviceAddress = device.getAddress();
              // Do something with the device name and address
              Toast.makeText(this, ""+deviceAddress, Toast.LENGTH_SHORT).show();
          }
          String printerMacAddress = "C6:D1:B8:06:90:DB"; // Replace with your printer's MAC address
          BluetoothDevice printerDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(printerMacAddress);
          BluetoothSocket socket = printerDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
          socket.connect();
          if(bluetoothAdapter.equals(null)) {
          }
          else {
              if (socket.isConnected()) {

                  Toast.makeText(this, "cccc", Toast.LENGTH_SHORT).show();
                  PrintHelper printHelper = new PrintHelper(AssenTaskDounwActivity.this);
                  printHelper.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                  printHelper.setOrientation(PrintHelper.ORIENTATION_PORTRAIT);
                  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagepost);

                  printHelper.setColorMode(PrintHelper.COLOR_MODE_COLOR);
                  printHelper.printBitmap("My Image", bitmap);
                  OutputStream outputStream = socket.getOutputStream();
Write the image data to the output stream
                  outputStream.write(imageData);
                  outputStream.flush();
                  socket.close();


    }
           ///   else {
       /// Toast.makeText(this, "ddddd", Toast.LENGTH_SHORT).show();
    }
}

      }catch (Exception e) {
              e.printStackTrace();
              }
       */


    }



    private byte[]  BitmapToRGBbyte(Bitmap bitmap) {
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
            int red = Color.red(argbPixel);
            int green = Color.green(argbPixel);
            int blue = Color.blue(argbPixel);
            int alpha = Color.alpha(argbPixel);

            rgbValues[i * componentsPerPixel + 0] = (byte) red;
            rgbValues[i * componentsPerPixel + 1] = (byte) green;
            rgbValues[i * componentsPerPixel + 2] = (byte) blue;
            //            rgbValues[i * componentsPerPixel + 3] = (byte) alpha;
        }

        return rgbValues;
    }

    public void pickimage(View view) {
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);

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
            WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics=new DisplayMetrics();
            
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
BitSet bitSet;
    private void convertToRGBScale(Bitmap bitmapOrg, int width, int height) {
        int newHeight=height;
        int newWidth=width;
        int R=0,B=0,G=0;
        int pixles;
        int x=0,y=0;
   bitSet=new BitSet();
   try {
       for ( x=0; x<height;x++) {
           for ( y=0;y<width;y++) {
               pixles=bitmapOrg.getPixel(x,y);
              R=Color.red(pixles);
              G=Color.green(pixles);
              B=Color.blue(pixles);
              //setcolor
               R=G=B=(int)((0.299*R)+(0.587*G)+(0.114*B));
               int k=0;
               if (R<55) {
                   bitSet.set(k);
               }
               k++;


           }
       }

   }catch (Exception e) {

   }

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