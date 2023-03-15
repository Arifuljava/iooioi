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
                    if (editTextEmail.getText().toString().toLowerCase().contains("is esc printer is good")) {
                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Yes\nTHT Space electronical company created ESC based printer");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc is good")) {

                        //Toast.makeText(CSECHAT.this, "ggg", Toast.LENGTH_SHORT).show();
                        ///
                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Yes\nTHT Space electronical company created ESC based printer");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc is not good")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("No\nTHT Space electronical company created ESC based printer");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc better than zpl")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.animateText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("YES\nTHT Space electronical company not created ZPL based printer");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("zpl vs esc")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Though Zebra printers are typically used to print black and white labels, they can also print color labels.\n" +
                                        " Using the colors in your brand will help to identify products quickly \n" +
                                        "and easily, which is helpful for customers who need fast identification \n" +
                                        "of inventory items at a glance.");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc vs zpl")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Though Zebra printers are typically used to print black and white labels, they can also print color labels.\n" +
                                        " Using the colors in your brand will help to identify products quickly \n" +
                                        "and easily, which is helpful for customers who need fast identification \n" +
                                        "of inventory items at a glance.");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("esc vs zpl?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Though Zebra printers are typically used to print black and white labels, they can also print color labels.\n" +
                                        " Using the colors in your brand will help to identify products quickly \n" +
                                        "and easily, which is helpful for customers who need fast identification \n" +
                                        "of inventory items at a glance.");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("zpl vs esc?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("Though Zebra printers are typically used to print black and white labels, they can also print color labels.\n" +
                                        " Using the colors in your brand will help to identify products quickly \n" +
                                        "and easily, which is helpful for customers who need fast identification \n" +
                                        "of inventory items at a glance.");
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
                    else if (editTextEmail.getText().toString().toLowerCase().contains("what is esc printer?")) {


                        show_message.setText(editTextEmail.getText().toString());
                        show_message_1_replay.setText("Thinking");
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                                show_message_1_replay.animateText("ESC/P-R is a common language for selected Epson printers " +
                                        "that supports every media type, paper size and associated printing mode " +
                                        "available on those printers. It is suited especially for consumer electronics devices " +
                                        "and embedded equipments.");
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
                    Toast.makeText(CSECHAT.this, "Message Send"+editTextEmail.getText(), Toast.LENGTH_SHORT).show();
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