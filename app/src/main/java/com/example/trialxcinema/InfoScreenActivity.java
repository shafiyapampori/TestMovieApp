package com.example.trialxcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trialxcinema.DashboardActivity.ResultsItem;

public class InfoScreenActivity extends AppCompatActivity {
    ResultsItem data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        data = getIntent().getExtras().getParcelable("ResultItem");
    }
}