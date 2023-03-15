package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nitish.typewriterview.TypeWriterView;

public class CPCLCHAT extends AppCompatActivity {
    TextView show_message;
    TextInputEditText editTextEmail;
    TypeWriterView show_message_1_replay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_c_l_c_h_a_t);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("CPCL Printer Chat");
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        show_message=findViewById(R.id.show_message);
        show_message_1_replay=findViewById(R.id.show_message_1_replay);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextEmail.addTextChangedListener(chattext);
        Button selectprinter=findViewById(R.id.selectprinter);
        selectprinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
                    Toast.makeText(CPCLCHAT.this, "Enter message", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (editTextEmail.getText().toString().toLowerCase().contains("is cpcl printer is good")) {
                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Yes\nTHT Space electronical company created CPCL based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("cpcl is good")) {

                        //Toast.makeText(CSECHAT.this, "ggg", Toast.LENGTH_SHORT).show();
                        ///
                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Yes\nTHT Space electronical company created CPCL based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("cpcl is not good")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("No\nTHT Space electronical company created CPCL based printer");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("cpcl better than zpl")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("YES\nTHT Space electronical company  created CPCL based printer");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("zpl vs cpcl")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("ZPL (Zebra Programming Language) and CPCL (Comprehensive Printer Command Language) are two different programming languages used for printing labels and receipts on Zebra printers.\n" +
                                        "\n" +
                                        "ZPL is a printer language developed by Zebra Technologies that is used for designing and printing labels for a wide range of applications, including shipping, inventory management, and asset tracking. It is a high-level language that supports a wide range of commands, such as text and barcode printing, graphics, and formatting.\n" +
                                        "\n" +
                                        "CPCL, on the other hand, is a command language developed by Zebra Technologies specifically for mobile printers. It is a low-level language that is used to send commands directly to the printer, such as specifying the font size and position of text, and printing barcodes and images. It is more limited than ZPL in terms of its capabilities, but it is easier to learn and use.\n" +
                                        "\n" +
                                        "In summary, ZPL is a more powerful and flexible language that is suitable for a wide range of label printing applications, while CPCL is a simpler language that is more suited to mobile printing and applications with less complex printing requirements.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("cpcl vs zpl")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("ZPL (Zebra Programming Language) and CPCL (Comprehensive Printer Command Language) are two different programming languages used for printing labels and receipts on Zebra printers.\n" +
                                        "\n" +
                                        "ZPL is a printer language developed by Zebra Technologies that is used for designing and printing labels for a wide range of applications, including shipping, inventory management, and asset tracking. It is a high-level language that supports a wide range of commands, such as text and barcode printing, graphics, and formatting.\n" +
                                        "\n" +
                                        "CPCL, on the other hand, is a command language developed by Zebra Technologies specifically for mobile printers. It is a low-level language that is used to send commands directly to the printer, such as specifying the font size and position of text, and printing barcodes and images. It is more limited than ZPL in terms of its capabilities, but it is easier to learn and use.\n" +
                                        "\n" +
                                        "In summary, ZPL is a more powerful and flexible language that is suitable for a wide range of label printing applications, while CPCL is a simpler language that is more suited to mobile printing and applications with less complex printing requirements.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("cpcl vs zpl?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("ZPL (Zebra Programming Language) and CPCL (Comprehensive Printer Command Language) are two different programming languages used for printing labels and receipts on Zebra printers.\n" +
                                        "\n" +
                                        "ZPL is a printer language developed by Zebra Technologies that is used for designing and printing labels for a wide range of applications, including shipping, inventory management, and asset tracking. It is a high-level language that supports a wide range of commands, such as text and barcode printing, graphics, and formatting.\n" +
                                        "\n" +
                                        "CPCL, on the other hand, is a command language developed by Zebra Technologies specifically for mobile printers. It is a low-level language that is used to send commands directly to the printer, such as specifying the font size and position of text, and printing barcodes and images. It is more limited than ZPL in terms of its capabilities, but it is easier to learn and use.\n" +
                                        "\n" +
                                        "In summary, ZPL is a more powerful and flexible language that is suitable for a wide range of label printing applications, while CPCL is a simpler language that is more suited to mobile printing and applications with less complex printing requirements.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("zpl vs cpcl?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("ZPL (Zebra Programming Language) and CPCL (Comprehensive Printer Command Language) are two different programming languages used for printing labels and receipts on Zebra printers.\n" +
                                        "\n" +
                                        "ZPL is a printer language developed by Zebra Technologies that is used for designing and printing labels for a wide range of applications, including shipping, inventory management, and asset tracking. It is a high-level language that supports a wide range of commands, such as text and barcode printing, graphics, and formatting.\n" +
                                        "\n" +
                                        "CPCL, on the other hand, is a command language developed by Zebra Technologies specifically for mobile printers. It is a low-level language that is used to send commands directly to the printer, such as specifying the font size and position of text, and printing barcodes and images. It is more limited than ZPL in terms of its capabilities, but it is easier to learn and use.\n" +
                                        "\n" +
                                        "In summary, ZPL is a more powerful and flexible language that is suitable for a wide range of label printing applications, while CPCL is a simpler language that is more suited to mobile printing and applications with less complex printing requirements.");
                                //  show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("what is cpcl printer?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero" +
                                        " Printer Command Language (CPCL) to utilize the built in text, graphics," +
                                        " bar code printing, and communication " +
                                        "capabilities of Zebra printers. To configure the CPCL settings: Select CPCL " +
                                        "from the options on the left.");
                                // show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);

                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("what is esc printer")) {

                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("is esc printer?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //  show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("is esc printer")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc printer?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero" +
                                        " Printer Command Language (CPCL) to utilize the built in text, graphics," +
                                                " bar code printing, and communication " +
                                                "capabilities of Zebra printers. To configure the CPCL settings: Select CPCL " +
                                                "from the options on the left.");
                                // show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc printer")) {

                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    else if (editTextEmail.getText().toString().toLowerCase().contains("?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("CPCL profile provides various commands in Camero\" +\n" +
                                        "                                        \" Printer Command Language (CPCL) to utilize the built in text, graphics,\" +\n" +
                                        "                                        \" bar code printing, and communication \" +\n" +
                                        "                                        \"capabilities of Zebra printers. To configure the CPCL settings: Select CPCL \" +\n" +
                                        "                                        \"from the options on the left.");
                                //show_message_1_replay.setText("Yes\nTHT Space electronical company created ESC based printer");
                                show_message_1_replay.setCharacterDelay(70);
                                show_message_1_replay.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        //Do something
                                        show_message_1_replay.isAnimationRunning(); //returns true if animation is still running
                                        show_message_1_replay.stopAnimation(); //Stop the ongoing animation
                                        show_message_1_replay.isTextInitialised(); //returns false if animation is not started
                                    }
                                });
                            }
                        },3000);
                    }
                    Toast.makeText(CPCLCHAT.this, "Message Send"+editTextEmail.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    String check;
    TextWatcher chattext=new TextWatcher() {
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
            }
            else {

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
}