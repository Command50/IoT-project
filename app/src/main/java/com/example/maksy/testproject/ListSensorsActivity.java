package com.example.maksy.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListSensorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensors);
    }
    public void onDetailPage(View view) {
        Intent intent = new Intent(ListSensorsActivity.this, DetailPageActivivty.class);
        startActivity(intent);
    }
}
