package com.grozziie.testingApplications;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.codesgood.views.JustifiedTextView;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.jgabrielfreitas.core.BlurImageView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.nitish.typewriterview.TypeWriterView;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import retrofit2.http.Url;

public class JoinActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar mainToolbar;
    private String current_user_id;
    private BottomNavigationView mainBottomNav;
    private DrawerLayout mainDrawer;
    private ActionBarDrawerToggle mainToggle;
    private NavigationView mainNav;

    FrameLayout frameLayout;
    private TextView drawerName,appname;
    private CircleImageView drawerImage;
    FirebaseAuth firebaseAuth;
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseFirestoreSettings settings;
    private DatabaseReference mUserRef;




    KProgressHUD kProgressHUD;
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();


    private HashMap<String, String> user;
    private String name, email, photo, mobile,username;
    private Drawer result;
    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket;
    String uuid="00001101-0000-1000-8000-00805F9B34FB";
    ////////Dialouge setup
    Dialog mDialouge;
    Uri imageuri;
    int flag = 0;
    BluetoothSocket m5ocket;
    BluetoothManager mBluetoothManager;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice device;
    ImageView imageposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Grozziie");
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        ///phone's default language getings
        String  defaultlanguage=Locale.getDefault().getDisplayLanguage();
        ///Toast.makeText(this, ""+defaultlanguage, Toast.LENGTH_SHORT).show();
        ///get default internet IP address
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(JoinActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null&&networkInfo.isConnected()) {

            try {
                Toast.makeText(this, "Connect with this IP : "+getPublicIPAddress(), Toast.LENGTH_SHORT).show();
            }catch (Exception e) {
            }
        }
        else {
            Toast.makeText(this, "Not Connect", Toast.LENGTH_SHORT).show();
        }


       /// Toast.makeText(this, ""+deviceName, Toast.LENGTH_SHORT).show();
        //////////////bluetooth checking
       try {
           bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
           ////Toast.makeText(this, ""+bluetoothAdapter.isEnabled(), Toast.LENGTH_SHORT).show();
           if (!bluetoothAdapter.isEnabled()) {
               AlertDialog.Builder builder=new AlertDialog.Builder(JoinActivity.this);
               builder.setTitle("Bluetooth")
                       .setMessage("Your bluetooth is disable now.\nDo you want to enable it?")
                       .setPositiveButton("NOT NOW", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();


                           }
                       }).setNegativeButton("ENABLE NOW", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                       bluetoothAdapter.enable();
                       bluetoothAdapter.startDiscovery();



                   }
               }).create();
               builder.show();
           }
           else {


           }
       }catch (Exception e) {
       }

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        mainDrawer=findViewById(R.id.main_activity);
        mainNav = findViewById(R.id.main_nav);
        mainNav.setNavigationItemSelectedListener(this);

        mainToggle = new ActionBarDrawerToggle(this,mainDrawer,toolbar,R.string.open,R.string.close);
        mainDrawer.addDrawerListener(mainToggle);
        mainDrawer.addDrawerListener(mainToggle);
        mainToggle.setDrawerIndicatorEnabled(true);
        mainToggle.syncState();
        ///animation
        topAnimation = AnimationUtils.loadAnimation(JoinActivity.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(JoinActivity.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(JoinActivity.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(JoinActivity.this, R.anim.splash_end_animation);
        mainDrawer.setAnimation(topAnimation);
        mainNav.setAnimation(topAnimation);
        ///cardanimation
        ///enabling dialouge
        try {
            final Dialog mDialog = new Dialog(JoinActivity.this);


            //mDialog = new Dialog(HomeACTIVITY.this);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //LayoutInflater factory = LayoutInflater.from(this);

            mDialog.setContentView(R.layout.dialouge2);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            CardView dailyCheckCard_circle=(CardView)mDialog.findViewById(R.id.dailyCheckCard_circle);
            dailyCheckCard_circle.setAnimation(topAnimation);
            TypeWriterView aboutcompanydetails=(TypeWriterView)mDialog.findViewById(R.id.aboutcompanydetails);
            aboutcompanydetails.animateText(aboutcompanydetails.getText().toString());
            aboutcompanydetails.setCharacterDelay(42);
            aboutcompanydetails.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                @Override
                public void onAnimationEnd() {
                    //Do something
                    aboutcompanydetails.isAnimationRunning(); //returns true if animation is still running
                    aboutcompanydetails.stopAnimation(); //Stop the ongoing animation
                    aboutcompanydetails.isTextInitialised(); //returns false if animation is not started
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ///mDialog.dismiss();
                            Toasty.success(getApplicationContext(),"Welcome to GROZZIIE homepage",Toasty.LENGTH_SHORT,true).show();
                            return;
                        }
                    },3000);
                }
            });

            mDialog.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
        ///card 1
        animationone=findViewById(R.id.animationone);
        escprinter=findViewById(R.id.escprinter);
        animationone.setAnimation(startAnimation);
        escprinter.setAnimation(endAnimation);
        escprinter.animateText("ESC Printer");
        escprinter.setCharacterDelay(300);
        escprinter.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
            @Override
            public void onAnimationEnd() {
                //Do something
                escprinter.isAnimationRunning(); //returns true if animation is still running
                escprinter.stopAnimation(); //Stop the ongoing animation
                escprinter.isTextInitialised(); //returns false if animation is not started
            }
        });
        dailyCheckCard=findViewById(R.id.dailyCheckCard);
        dailyCheckCard.setAnimation(startAnimation);
        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                bottomSheetDialog11.setContentView(R.layout.about2);
                CardView dailyCheckCard=(CardView)bottomSheetDialog11.findViewById(R.id.dailyCheckCard);
                dailyCheckCard.setAnimation(startAnimation);
                dailyCheckCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),CSECHAT.class));
                    }
                });
                ///card 2
                CardView luckySpinCard=(CardView)bottomSheetDialog11.findViewById(R.id.luckySpinCard);
                luckySpinCard.setAnimation(endAnimation);
                luckySpinCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),CSEPrinterActivity.class));

                    }
                });





                bottomSheetDialog11.show();
            }
        });
        ////card 2
        image1=findViewById(R.id.image1);
        image1.setAnimation(endAnimation);
        cpclanimation=findViewById(R.id.cpclanimation);
        cpclanimation.setAnimation(endAnimation);
        cpclanimation.animateText("CPCL Printer");
        cpclanimation.setCharacterDelay(300);
        cpclanimation.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
            @Override
            public void onAnimationEnd() {
                //Do something
                cpclanimation.isAnimationRunning(); //returns true if animation is still running
                cpclanimation.stopAnimation(); //Stop the ongoing animation
                cpclanimation.isTextInitialised(); //returns false if animation is not started
            }
        });
        luckySpinCard=findViewById(R.id.luckySpinCard);
        luckySpinCard.setAnimation(endAnimation);
        luckySpinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                bottomSheetDialog11.setContentView(R.layout.aboutboth);
                CardView dailyCheckCard=(CardView)bottomSheetDialog11.findViewById(R.id.dailyCheckCard);
                dailyCheckCard.setAnimation(startAnimation);
                dailyCheckCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),CPCLCHAT.class));
                    }
                });
                ///card 2
                CardView luckySpinCard=(CardView)bottomSheetDialog11.findViewById(R.id.luckySpinCard);
                luckySpinCard.setAnimation(endAnimation);
                luckySpinCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
startActivity(new Intent(getApplicationContext(),FreshCPClActivity.class));
                    }
                });





                bottomSheetDialog11.show();
            }
        });
        ///card 3
        deviceanimation=findViewById(R.id.deviceanimation);
        deviceanimation.setAnimation(startAnimation);
        devices=findViewById(R.id.devices);
        devices.setAnimation(endAnimation);
        devices.animateText("Devices");
        devices.setCharacterDelay(300);
        devices.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
            @Override
            public void onAnimationEnd() {
                //Do something
                devices.isAnimationRunning(); //returns true if animation is still running
                devices.stopAnimation(); //Stop the ongoing animation
                devices.isTextInitialised(); //returns false if animation is not started
            }
        });
        dailyCheckCard_1=findViewById(R.id.dailyCheckCard_1);
        dailyCheckCard_1.setAnimation(bottomAnimation);
        dailyCheckCard_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                bottomSheetDialog11.setContentView(R.layout.devices);
                ////refreshing data..............................data refreshing

                CircularProgressButton btn_id=(CircularProgressButton)bottomSheetDialog11.findViewById(R.id.btn_id);
                btn_id.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_id.startAnimation();
                        ////////refreshdata

                        LottieAnimationView tv_no_cards=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards);
                        RecyclerView recyclerbooth=(RecyclerView)bottomSheetDialog11.findViewById(R.id.recyclerbooth);
                        LottieAnimationView tv_no_cards_empty=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards_empty);

                        ////devices are setup from database

                        firebaseFirestore=FirebaseFirestore.getInstance();
                        ///getting data counter
                        firebaseFirestore.collection("My_Devices")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        int count = 0;
                                        for (DocumentSnapshot document : task.getResult()) {
                                            count++;
                                        }
                                        if (count==0) {
                                            tv_no_cards.setVisibility(View.VISIBLE);
                                            recyclerbooth.setVisibility(View.GONE);
                                            Handler handler=new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    tv_no_cards.setVisibility(View.GONE);
                                                    recyclerbooth.setVisibility(View.GONE);
                                                    tv_no_cards_empty.setVisibility(View.VISIBLE);
                                                }
                                            },2000);
                                            Handler handler1=new Handler();
                                            handler1.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    tv_no_cards.setVisibility(View.GONE);
                                                    recyclerbooth.setVisibility(View.GONE);
                                                    tv_no_cards_empty.setVisibility(View.VISIBLE);
                                                    btn_id.revertAnimation();
                                                    btn_id.setText("Refresh");
                                                    Toasty.info(getApplicationContext(),"Refreshing Done",Toasty.LENGTH_SHORT,true).show();
                                                    return;
                                                }
                                            },3000);
                                        }
                                        else {
                                            tv_no_cards.setVisibility(View.GONE);
                                            recyclerbooth.setVisibility(View.VISIBLE);
                                            DocumentReference documentReference;
                                            RecyclerView recyclerView;
                                            DevicAdapter getDataAdapter1;
                                            List<DeviceModel> getList;



                                            getList = new ArrayList<>();
                                            getDataAdapter1 = new DevicAdapter(getList);
                                            firebaseFirestore = FirebaseFirestore.getInstance();
                                            documentReference = firebaseFirestore.collection("My_Devices")
                                                    .document();

                                            recyclerbooth.setHasFixedSize(true);
                                            recyclerbooth.setLayoutManager(new LinearLayoutManager(JoinActivity.this));
                                            recyclerbooth.setAdapter(getDataAdapter1);


                                            firebaseFirestore.collection("My_Devices")
                                                    .orderBy("mydevicetime", Query.Direction.DESCENDING)
                                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                            for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                                if (ds.getType() == DocumentChange.Type.ADDED) {
                                                                    DeviceModel get = ds.getDocument().toObject(DeviceModel.class);
                                                                    getList.add(get);
                                                                    getDataAdapter1.notifyDataSetChanged();
                                                                }

                                                            }
                                                        }
                                                    });
                                            ////data refreshing
                                            Handler handler1=new Handler();
                                            handler1.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    tv_no_cards.setVisibility(View.GONE);
                                                    recyclerbooth.setVisibility(View.VISIBLE);
                                                    tv_no_cards_empty.setVisibility(View.GONE);
                                                    btn_id.revertAnimation();
                                                    btn_id.setText("Refresh");
                                                    Toasty.info(getApplicationContext(),"Refreshing Done",Toasty.LENGTH_SHORT,true).show();
                                                    return;
                                                }
                                            },3000);


                                        }
                                    }
                                });


                    }
                });
                ImageView bluethooth=(ImageView)bottomSheetDialog11.findViewById(R.id.bluethooth);
                bluethooth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (mBluetoothAdapter == null) {
                            // Device does not support Bluetooth
                            Toast.makeText(JoinActivity.this, "This device is not support bluetooth.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!mBluetoothAdapter.isEnabled()) {
                                Toasty.info(getApplicationContext(),"Please turn on bluetooth",Toasty.LENGTH_SHORT,true).show();
                                return;
                            }
                            else if (mBluetoothAdapter.isEnabled()){
                                Toasty.info(getApplicationContext(),"Bluetooth is open.",Toasty.LENGTH_SHORT,true).show();
                                return;
                            }
                            else {

                            }
                        }
                    }
                });

                ImageView canceldialouge=(ImageView)bottomSheetDialog11.findViewById(R.id.canceldialouge);
                canceldialouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog11.dismiss();

                    }
                });
                LottieAnimationView tv_no_cards=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards);
                RecyclerView recyclerbooth=(RecyclerView)bottomSheetDialog11.findViewById(R.id.recyclerbooth);
                LottieAnimationView tv_no_cards_empty=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards_empty);

                ////devices are setup from database

                firebaseFirestore=FirebaseFirestore.getInstance();
                ///getting data counter
                firebaseFirestore.collection("My_Devices")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int count = 0;
                                for (DocumentSnapshot document : task.getResult()) {
                                    count++;
                                }
                               if (count==0) {
                                   tv_no_cards.setVisibility(View.VISIBLE);
                                   recyclerbooth.setVisibility(View.GONE);
                                   Handler handler=new Handler();
                                   handler.postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           tv_no_cards.setVisibility(View.GONE);
                                           recyclerbooth.setVisibility(View.GONE);
                                           tv_no_cards_empty.setVisibility(View.VISIBLE);
                                       }
                                   },2000);
                               }
                               else {
                                   tv_no_cards.setVisibility(View.GONE);
                                   recyclerbooth.setVisibility(View.VISIBLE);
                                   DocumentReference documentReference;
                                   RecyclerView recyclerView;
                                   DevicAdapter getDataAdapter1;
                                   List<DeviceModel> getList;



                                   getList = new ArrayList<>();
                                   getDataAdapter1 = new DevicAdapter(getList);
                                   firebaseFirestore = FirebaseFirestore.getInstance();
                                   documentReference = firebaseFirestore.collection("My_Devices")
                                           .document();

                                   recyclerbooth.setHasFixedSize(true);
                                   recyclerbooth.setLayoutManager(new LinearLayoutManager(JoinActivity.this));
                                   recyclerbooth.setAdapter(getDataAdapter1);


                                   firebaseFirestore.collection("My_Devices")
                                           .orderBy("mydevicetime", Query.Direction.DESCENDING)
                                           .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                               @Override
                                               public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                   for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                       if (ds.getType() == DocumentChange.Type.ADDED) {
                                                           DeviceModel get = ds.getDocument().toObject(DeviceModel.class);
                                                           getList.add(get);
                                                           getDataAdapter1.notifyDataSetChanged();
                                                       }

                                                   }
                                               }
                                           });


                               }
                            }
                        });

                //////





                bottomSheetDialog11.show();
            }
        });
        ///card 4

        luckySpinCard__3=findViewById(R.id.luckySpinCard__3);
        luckySpinCard__3.setAnimation(bottomAnimation);
        ettingsanimation=findViewById(R.id.ettingsanimation);
        ettingsanimation.setAnimation(startAnimation);
        settingstext=findViewById(R.id.settingstext);
        settingstext.setAnimation(startAnimation);

        settingstext.animateText("Settings");
        settingstext.setCharacterDelay(300);
        settingstext.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
            @Override
            public void onAnimationEnd() {
                //Do something
                settingstext.isAnimationRunning(); //returns true if animation is still running
                settingstext.stopAnimation(); //Stop the ongoing animation
                settingstext.isTextInitialised(); //returns false if animation is not started

            }
        });
        luckySpinCard__3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                bottomSheetDialog11.setContentView(R.layout.settings);

                CircularProgressButton btn_id=(CircularProgressButton)bottomSheetDialog11.findViewById(R.id.btn_id);
                btn_id.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_id.startAnimation();
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn_id.revertAnimation();
                            }
                        },3000);
                    }
                });
                //////setup spinner
                Button button_bluetooth_browse=(Button)bottomSheetDialog11.findViewById(R.id.button_bluetooth_browse);
                button_bluetooth_browse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebaseFirestore=FirebaseFirestore.getInstance();

                        final BluetoothConnection[] bluetoothDevicesList = (new BluetoothPrintersConnections()).getList();

                        if (bluetoothDevicesList != null) {
                            final String[] items = new String[bluetoothDevicesList.length + 1];
                            items[0] = "Default printer";
                            int i = 0;
                            for (BluetoothConnection device : bluetoothDevicesList) {
                                items[++i] = device.getDevice().getName();
                                BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
                                String deviceName = myDevice.getName();
                                Date c = Calendar.getInstance().getTime();
                               // System.out.println("Current time => " + c);

                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                                String formattedDate = df.format(c);
                                DeviceModel deviceModel=new DeviceModel(myDevice.getName(),""+myDevice.getAddress().toString(), uuid,formattedDate,deviceName,formattedDate);
                                firebaseFirestore.collection("My_Devices")
                                        .document(myDevice.getAddress())
                                        .set(deviceModel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                            }

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(JoinActivity.this);
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
                                    Button button = (Button) bottomSheetDialog11.findViewById(R.id.button_bluetooth_browse);
                                    button.setText(items[i]);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("deviceaddress", ""+button.getText().toString());
                                    firebaseFirestore=FirebaseFirestore.getInstance();
                                    firebaseFirestore.collection("Connected")
                                            .document("abc@gmail.com")
                                            .set(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            });

                            AlertDialog alert = alertDialog.create();
                            alert.setCanceledOnTouchOutside(false);
                            alert.show();

                        }
                    }
                });







                bottomSheetDialog11.show();
            }
        });

    }
    private BluetoothConnection selectedDevice;


    CardView dailyCheckCard,luckySpinCard,dailyCheckCard_1,luckySpinCard__3;
    TypeWriterView  escprinter,cpclanimation,devices,settingstext;
    LottieAnimationView  animationone,image1,deviceanimation,ettingsanimation;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
int id=item.getItemId();
if (id==R.id.home) {
    //mainDrawer.closeDrawers();;
    mainDrawer.closeDrawer(Gravity.START,false);
    Toasty.success(getApplicationContext(),"You are home ",Toasty.LENGTH_SHORT,true).show();


}
else if (id==R.id.promotion)
{
    mainDrawer.closeDrawer(Gravity.START,false);
    String oo[]={"ESC Printer","CPCL Printer"};
    AlertDialog.Builder builder =new AlertDialog.Builder(JoinActivity.this);
    builder.setTitle("Printer Options")
            .setItems(oo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which==0) {
                        final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                        bottomSheetDialog11.setContentView(R.layout.about2);
                        CardView dailyCheckCard=(CardView)bottomSheetDialog11.findViewById(R.id.dailyCheckCard);
                        dailyCheckCard.setAnimation(startAnimation);
                        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        ///card 2
                        CardView luckySpinCard=(CardView)bottomSheetDialog11.findViewById(R.id.luckySpinCard);
                        luckySpinCard.setAnimation(endAnimation);
                        luckySpinCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });




bottomSheetDialog11.show();
                    }
                    else {
                        final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
                        bottomSheetDialog11.setContentView(R.layout.aboutboth);
                        CardView dailyCheckCard=(CardView)bottomSheetDialog11.findViewById(R.id.dailyCheckCard);
                        dailyCheckCard.setAnimation(startAnimation);
                        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        ///card 2
                        CardView luckySpinCard=(CardView)bottomSheetDialog11.findViewById(R.id.luckySpinCard);
                        luckySpinCard.setAnimation(endAnimation);
                        luckySpinCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });





                        bottomSheetDialog11.show();
                    }

                }
            }).create();
    builder.show();

}
else if(id==R.id.wishlist) {
    mainDrawer.closeDrawer(Gravity.START,false);
    final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
    bottomSheetDialog11.setContentView(R.layout.devices);
    ////refreshing data..............................data refreshing

    CircularProgressButton btn_id=(CircularProgressButton)bottomSheetDialog11.findViewById(R.id.btn_id);
    btn_id.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn_id.startAnimation();
            ////////refreshdata

            LottieAnimationView tv_no_cards=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards);
            RecyclerView recyclerbooth=(RecyclerView)bottomSheetDialog11.findViewById(R.id.recyclerbooth);
            LottieAnimationView tv_no_cards_empty=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards_empty);

            ////devices are setup from database

            firebaseFirestore=FirebaseFirestore.getInstance();
            ///getting data counter
            firebaseFirestore.collection("My_Devices")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            if (count==0) {
                                tv_no_cards.setVisibility(View.VISIBLE);
                                recyclerbooth.setVisibility(View.GONE);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_no_cards.setVisibility(View.GONE);
                                        recyclerbooth.setVisibility(View.GONE);
                                        tv_no_cards_empty.setVisibility(View.VISIBLE);
                                    }
                                },2000);
                                Handler handler1=new Handler();
                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_no_cards.setVisibility(View.GONE);
                                        recyclerbooth.setVisibility(View.GONE);
                                        tv_no_cards_empty.setVisibility(View.VISIBLE);
                                        btn_id.revertAnimation();
                                        btn_id.setText("Refresh");
                                        Toasty.info(getApplicationContext(),"Refreshing Done",Toasty.LENGTH_SHORT,true).show();
                                        return;
                                    }
                                },3000);
                            }
                            else {
                                tv_no_cards.setVisibility(View.GONE);
                                recyclerbooth.setVisibility(View.VISIBLE);
                                DocumentReference documentReference;
                                RecyclerView recyclerView;
                                DevicAdapter getDataAdapter1;
                                List<DeviceModel> getList;



                                getList = new ArrayList<>();
                                getDataAdapter1 = new DevicAdapter(getList);
                                firebaseFirestore = FirebaseFirestore.getInstance();
                                documentReference = firebaseFirestore.collection("My_Devices")
                                        .document();

                                recyclerbooth.setHasFixedSize(true);
                                recyclerbooth.setLayoutManager(new LinearLayoutManager(JoinActivity.this));
                                recyclerbooth.setAdapter(getDataAdapter1);


                                firebaseFirestore.collection("My_Devices")
                                        .orderBy("mydevicetime", Query.Direction.DESCENDING)
                                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                            @Override
                                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                    if (ds.getType() == DocumentChange.Type.ADDED) {
                                                        DeviceModel get = ds.getDocument().toObject(DeviceModel.class);
                                                        getList.add(get);
                                                        getDataAdapter1.notifyDataSetChanged();
                                                    }

                                                }
                                            }
                                        });
                                ////data refreshing
                                Handler handler1=new Handler();
                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_no_cards.setVisibility(View.GONE);
                                        recyclerbooth.setVisibility(View.VISIBLE);
                                        tv_no_cards_empty.setVisibility(View.GONE);
                                        btn_id.revertAnimation();
                                        btn_id.setText("Refresh");
                                        Toasty.info(getApplicationContext(),"Refreshing Done",Toasty.LENGTH_SHORT,true).show();
                                        return;
                                    }
                                },3000);


                            }
                        }
                    });


        }
    });
    ImageView bluethooth=(ImageView)bottomSheetDialog11.findViewById(R.id.bluethooth);
    bluethooth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                // Device does not support Bluetooth
                Toast.makeText(JoinActivity.this, "This device is not support bluetooth.", Toast.LENGTH_SHORT).show();
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    Toasty.info(getApplicationContext(),"Please turn on bluetooth",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else if (mBluetoothAdapter.isEnabled()){
                    Toasty.info(getApplicationContext(),"Bluetooth is open.",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {

                }
            }
        }
    });

    ImageView canceldialouge=(ImageView)bottomSheetDialog11.findViewById(R.id.canceldialouge);
    canceldialouge.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomSheetDialog11.dismiss();

        }
    });
    LottieAnimationView tv_no_cards=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards);
    RecyclerView recyclerbooth=(RecyclerView)bottomSheetDialog11.findViewById(R.id.recyclerbooth);
    LottieAnimationView tv_no_cards_empty=(LottieAnimationView)bottomSheetDialog11.findViewById(R.id.tv_no_cards_empty);

    ////devices are setup from database

    firebaseFirestore=FirebaseFirestore.getInstance();
    ///getting data counter
    firebaseFirestore.collection("My_Devices")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    if (count==0) {
                        tv_no_cards.setVisibility(View.VISIBLE);
                        recyclerbooth.setVisibility(View.GONE);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tv_no_cards.setVisibility(View.GONE);
                                recyclerbooth.setVisibility(View.GONE);
                                tv_no_cards_empty.setVisibility(View.VISIBLE);
                            }
                        },2000);
                    }
                    else {
                        tv_no_cards.setVisibility(View.GONE);
                        recyclerbooth.setVisibility(View.VISIBLE);
                        DocumentReference documentReference;
                        RecyclerView recyclerView;
                        DevicAdapter getDataAdapter1;
                        List<DeviceModel> getList;



                        getList = new ArrayList<>();
                        getDataAdapter1 = new DevicAdapter(getList);
                        firebaseFirestore = FirebaseFirestore.getInstance();
                        documentReference = firebaseFirestore.collection("My_Devices")
                                .document();

                        recyclerbooth.setHasFixedSize(true);
                        recyclerbooth.setLayoutManager(new LinearLayoutManager(JoinActivity.this));
                        recyclerbooth.setAdapter(getDataAdapter1);


                        firebaseFirestore.collection("My_Devices")
                                .orderBy("mydevicetime", Query.Direction.DESCENDING)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                            if (ds.getType() == DocumentChange.Type.ADDED) {
                                                DeviceModel get = ds.getDocument().toObject(DeviceModel.class);
                                                getList.add(get);
                                                getDataAdapter1.notifyDataSetChanged();
                                            }

                                        }
                                    }
                                });


                    }
                }
            });

    //////





    bottomSheetDialog11.show();

}
else if(id==R.id.setings) {
    mainDrawer.closeDrawer(Gravity.START,false);
    final BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(JoinActivity.this);
    bottomSheetDialog11.setContentView(R.layout.settings);
    JustifiedTextView app_slogan=(JustifiedTextView)bottomSheetDialog11.findViewById(R.id.app_slogan);
    //app_slogan.setText("Please select a paired device for your primary connected printer.Here is some printer list that you are connected previously.");

    CircularProgressButton btn_id=(CircularProgressButton)bottomSheetDialog11.findViewById(R.id.btn_id);
    btn_id.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn_id.startAnimation();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_id.revertAnimation();
                }
            },3000);
        }
    });






    bottomSheetDialog11.show();
}

        return false;

    }
    public  static  String getDefaultIpAddress() throws IOException {
        // Create a URL object with the API endpoint

        String ipAddress;
       try {
           URL url = new URL("https://api.ipify.org?format=json");

           // Open a connection to the URL and read the response
           BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
           StringBuilder response = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
               response.append(line);
           }
           reader.close();

           // Parse the JSON response to get the IP address
           JSONObject jsonObject = new JSONObject(response.toString());
            ipAddress = jsonObject.getString("ip");

           return ipAddress;
       }catch (Exception e) {
           e.printStackTrace();
       }
        return null;
    }
    public static String getPublicIPAddress(){
        String value = null;
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> result = es.submit(new Callable<String>() {
            public String call() throws Exception {
                try {
                    URL url = new URL("http://whatismyip.akamai.com/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader r = new BufferedReader(new InputStreamReader(in));
                        StringBuilder total = new StringBuilder();
                        String line;
                        while ((line = r.readLine()) != null) {
                            total.append(line).append('\n');
                        }
                        urlConnection.disconnect();
                        return total.toString();
                    }finally {
                        urlConnection.disconnect();
                    }
                }catch (IOException e){
                    Log.e("Public IP: ",e.getMessage());
                }
                return null;
            }
        });
        try {
            value = result.get();
        } catch (Exception e) {
            // failed
        }
        es.shutdown();
        return value;
    }
    public  static String setUpmachinetime() throws Exception {
      final  String IP_ADDRESS="192.168.0.100";
       final int PORT_Addres=8500;
        Socket socket = new Socket(IP_ADDRESS, PORT_Addres);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_WEEK);
        String date=day+" / "+month+" / "+year;
        int scond=calendar.get(Calendar.SECOND);
        int min=calendar.get(Calendar.MINUTE);
        int hour=calendar.get(Calendar.HOUR);
        String timecurrently=String.format("%02d:%02d:%02d",hour,min,scond);
        String finaltimeanddate=timecurrently+" "+date;
        OutputStream output = socket.getOutputStream();
        String command = "SET_TIME: "+finaltimeanddate;
        output.write(command.getBytes());
        InputStream input = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = input.read(buffer);
        String response = new String(buffer, 0, bytesRead);
        if (response.contains("OK")) {
            System.out.println("Time set successfully.");
        } else {
            System.err.println("Error setting time: " + response);
        }
        socket.close();
    return null;
    }
}