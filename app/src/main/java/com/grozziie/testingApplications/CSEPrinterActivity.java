package com.grozziie.testingApplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dantsu.escposprinter.connection.DeviceConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class CSEPrinterActivity extends AppCompatActivity {
TextInputEditText editTextEmail,enteruniprice,enterquan,finalamount,defaultprinty;
FirebaseFirestore firebaseFirestore;
LinearLayout firstdesign;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_s_e_printer);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("ESC Printer");
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        ////edittxt
        firebaseFirestore=FirebaseFirestore.getInstance();
        ///assign first
        firstdesign=findViewById(R.id.firstdesign);
        topAnimation = AnimationUtils.loadAnimation(CSEPrinterActivity.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(CSEPrinterActivity.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(CSEPrinterActivity.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(CSEPrinterActivity.this, R.anim.splash_end_animation);
        firstdesign.setAnimation(endAnimation);
        finalamounthint=findViewById(R.id.finalamounthint);


        editTextEmail=findViewById(R.id.editTextEmail);
        enteruniprice=findViewById(R.id.enteruniprice);
        enterquan=findViewById(R.id.enterquan);
        finalamount=findViewById(R.id.finalamount);
        defaultprinty=findViewById(R.id.defaultprinty);
        button_bluetooth_browse=findViewById(R.id.button_bluetooth_browse);
        ///default adddress
        firebaseFirestore.collection("Connected")
                .document("abc@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    defaultprinty.setText(task.getResult().getString("deviceaddress"));
                                }catch (Exception e) {
                                    defaultprinty.setText(task.getResult().getString("deviceaddress"));
                                }
                            }
                        }
                    }
                });
        enteruniprice.addTextChangedListener(unitprice);
        enterquan.addTextChangedListener(quantitywatcher);
        Button selectprinter=findViewById(R.id.selectprinter);
        selectprinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /// Toast.makeText(CSEPrinterActivity.this, "hhh", Toast.LENGTH_SHORT).show();
                final BluetoothConnection[] bluetoothDevicesList = (new BluetoothPrintersConnections()).getList();

                if (bluetoothDevicesList != null) {
                    final String[] items = new String[bluetoothDevicesList.length + 1];
                    items[0] = "Default printer";
                    int i = 0;
                    for (BluetoothConnection device : bluetoothDevicesList) {
                        items[++i] = device.getDevice().getName();
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(CSEPrinterActivity.this);
                    alertDialog.setTitle("Bluetooth printer selection");
                    alertDialog.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int index = i - 1;
                            if (index == -1) {
                                selectedDevice = null;
                            } else {
                                selectedDevice = bluetoothDevicesList[index];
                            }
                            Button button = (Button) findViewById(R.id.selectprinter);
                            button.setText(items[i]);
                            ///Toast.makeText(CSEPrinterActivity.this, ""+selectedDevice, Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alert = alertDialog.create();
                    alert.setCanceledOnTouchOutside(false);
                    alert.show();

                }
            }
        });


        button_bluetooth_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextEmail.getText().toString())||TextUtils.isEmpty(enteruniprice.getText().toString())
                ||TextUtils.isEmpty(enterquan.getText().toString())||TextUtils.isEmpty(finalamount.getText().toString())) {
                    Toasty.info(getApplicationContext(),"Enter all information",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(CSEPrinterActivity.this);
                    builder.setTitle("Confirmation")
                            .setMessage("Are you want to print it?")
                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            printBluetooth();
                        }
                    }).create();
                    builder.show();
                }

            }
        });

    }

    private void printBluetooth() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, CSEPrinterActivity.PERMISSION_BLUETOOTH);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, CSEPrinterActivity.PERMISSION_BLUETOOTH_ADMIN);
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, CSEPrinterActivity.PERMISSION_BLUETOOTH_CONNECT);
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, CSEPrinterActivity.PERMISSION_BLUETOOTH_SCAN);
        } else {
            new AsyncBluetoothEscPosPrint(
                    this,
                    new AsyncEscPosPrint.OnPrintFinished() {
                        @Override
                        public void onError(AsyncEscPosPrinter asyncEscPosPrinter, int codeException) {
                            Log.e("Async.OnPrintFinished", "AsyncEscPosPrint.OnPrintFinished : An error occurred !");
                        }

                        @Override
                        public void onSuccess(AsyncEscPosPrinter asyncEscPosPrinter) {
                            Log.i("Async.OnPrintFinished", "AsyncEscPosPrint.OnPrintFinished : Print is finished !");
                        }
                    }
            )
                    .execute(this.getAsyncEscPosPrinter(selectedDevice));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case CSEPrinterActivity.PERMISSION_BLUETOOTH:
                case CSEPrinterActivity.PERMISSION_BLUETOOTH_ADMIN:
                case CSEPrinterActivity.PERMISSION_BLUETOOTH_CONNECT:
                case CSEPrinterActivity.PERMISSION_BLUETOOTH_SCAN:
                    this.printBluetooth();
                    break;
            }
        }
    }

    private BluetoothConnection selectedDevice;
    public static final int PERMISSION_BLUETOOTH = 1;
    public static final int PERMISSION_BLUETOOTH_ADMIN = 2;
    public static final int PERMISSION_BLUETOOTH_CONNECT = 3;
    public static final int PERMISSION_BLUETOOTH_SCAN = 4;
    TextWatcher quantitywatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            check=s.toString();
            if (TextUtils.isEmpty(check)) {
                finalamounthint.setVisibility(View.GONE);
            }
            else {
                if (TextUtils.isEmpty(enteruniprice.getText().toString())) {
                }
                else {
                    finalamounthint.setVisibility(View.VISIBLE);
                    // finalamounthint.setAnimation(startAnimation);
                    double finalamount2=Double.parseDouble(check)*Double.parseDouble(enteruniprice.getText().toString());
                    finalamount.setText(""+finalamount2);
                }
            }
        }
    };
    TextInputLayout finalamounthint;
    Button button_bluetooth_browse;
    String check;
    TextWatcher unitprice=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            check=s.toString();
            if (TextUtils.isEmpty(check)) {
                finalamounthint.setVisibility(View.GONE);
            }
            else {
                if (TextUtils.isEmpty(enterquan.getText().toString())) {
                }
                else {
                    finalamounthint.setVisibility(View.VISIBLE);
                   // finalamounthint.setAnimation(startAnimation);
                    double finalamount2=Double.parseDouble(check)*Double.parseDouble(enterquan.getText().toString());
                    finalamount.setText(""+finalamount2);
                }
            }

        }
    };

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),JoinActivity.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),JoinActivity.class));
        return true;
    }

    /**
     * Asynchronous printing
     */
    Random random=new Random();
    int number=random.nextInt(56);
    @SuppressLint("SimpleDateFormat")
    public AsyncEscPosPrinter getAsyncEscPosPrinter(DeviceConnection printerConnection) {
        SimpleDateFormat format = new SimpleDateFormat("'on' yyyy-MM-dd 'at' HH:mm:ss");
        AsyncEscPosPrinter printer = new AsyncEscPosPrinter(printerConnection, 203, 48f, 32);
        return printer.addTextToPrint(
                "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.app_logo, DisplayMetrics.DENSITY_MEDIUM)) + "</img>\n" +
                        "[L]\n" +
                        "[C]<u><font size='big'>ORDER N°"+number+"</font></u>\n" +
                        "[L]\n" +
                        "[C]<u type='double'>" + format.format(new Date()) + "</u>\n" +
                        "[C]\n" +
                        "[C]================================\n" +
                        "[L]\n" +
                        "[L]<b>Product Name : "+editTextEmail.getText().toString()+" : </b>[R]\n" +
                        "[L]  Unit Price  : "+enteruniprice.getText().toString()+"\n" +
                        "[L]\n" +
                        "[L]<b>Quantity : "+enterquan.getText().toString()+"</b>[R]\n" +
                        "[L]\n" +
                        "[L]\n" +
                        "[C]--------------------------------\n" +
                        "[R]TOTAL PRICE :[R]"+finalamount.getText().toString()+"\n" +
                        "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
                        "[L]\n" +
                        "[C]<qrcode size='20'>http://www.developpeur-web.dantsu.com/</qrcode>\n"
        );
    }

}