package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import es.dmoral.toasty.Toasty;

public class CSEPrinterActivity extends AppCompatActivity {
TextInputEditText editTextEmail,enteruniprice,enterquan,finalamount,defaultprinty;
FirebaseFirestore firebaseFirestore;

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

        editTextEmail=findViewById(R.id.editTextEmail);
        enteruniprice=findViewById(R.id.enteruniprice);
        enterquan=findViewById(R.id.enterquan);
        finalamount=findViewById(R.id.finalamount);
        defaultprinty=findViewById(R.id.defaultprinty);
        button_bluetooth_browse=findViewById(R.id.button_bluetooth_browse);
        button_bluetooth_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isDigitsOnly(editTextEmail.getText().toString())||TextUtils.isDigitsOnly(enteruniprice.getText().toString())
                ||TextUtils.isDigitsOnly(enterquan.getText().toString())||TextUtils.isDigitsOnly(finalamount.getText().toString())
            ||TextUtils.isDigitsOnly(defaultprinty.getText().toString())) {
                    Toasty.info(getApplicationContext(),"Enter all information",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                }

            }
        });

    }
    Button button_bluetooth_browse;

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