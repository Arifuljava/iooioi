package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatGptActivityy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_gpt_activityy);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://platform.openai.com:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatGptApi chatGptApi = retrofit.create(ChatGptApi.class);
    }
}