package com.example.maksy.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListSensorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensors);
    }
    public void onClickDetailPage (View v) {
        switch (v.getId()) {
            case R.id.imageSensorOne:
                otherActivity();
                break;
            case R.id.sensorTwo:
                otherActivity1();
                break;
        }
    }
    private void otherActivity() {
        Intent intent = new Intent(ListSensorsActivity.this, DetailPageActivivty.class);
        intent.putExtra("status", "Connected");
        intent.putExtra("turningtime", "17:45");
        intent.putExtra("exceptiontime", "18:00");

        startActivity(intent);
    }
    private void otherActivity1() {
        Intent intent = new Intent(ListSensorsActivity.this, DetailPageActivivty.class);
        intent.putExtra("status", "Not connected");
        intent.putExtra("turningtime", "10:00");
        intent.putExtra("exceptiontime", "10:20");
        startActivity(intent);
    }



}
