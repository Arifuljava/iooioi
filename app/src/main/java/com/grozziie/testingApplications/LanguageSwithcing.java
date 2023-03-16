package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageSwithcing extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    TextView  textdetect;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_swithcing);

         toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("Language Settings");
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);

        textdetect=findViewById(R.id.textdetect);
        spinnerTextSize = findViewById(R.id.spinner);
        spinnerTextSize.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.language);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_row, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);
    }
    String checking;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            checking=valueFromSpinner;
            if (checking.equals("Select a language")) {
                Toast.makeText(this, "Please pick a language", Toast.LENGTH_SHORT).show();
            }
            else if (checking.equals("বাংলা")||checking.equals("Bangla")) {
                textdetect.setText("");
                textdetect.setText(R.string.bangla);
                toolbar.setTitle(R.string.banglastring);
            }
            else if (checking.equals("English")||checking.equals("ইংরেজি")) {
                textdetect.setText("");
                textdetect.setText(R.string.english);
                toolbar.setTitle(R.string.englishname);
            }
            else if (checking.equals("中國人")) {
                textdetect.setText("");
                textdetect.setText(R.string.china1);
                toolbar.setTitle(R.string.chiu1);

            }
            else if (checking.equals("中国人")) {
                textdetect.setText("");
                textdetect.setText(R.string.china2);
                toolbar.setTitle(R.string.chiu2);
            }
            else if (checking.equals("Thai")||checking.equals("แบบไทย")) {
                textdetect.setText("");
                textdetect.setText(R.string.thai);
                toolbar.setTitle(R.string.thailang);
            }
            else if (checking.equals("Melayu")) {
                textdetect.setText("");
                textdetect.setText(R.string.malay);
                toolbar.setTitle(R.string.malayu);
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}