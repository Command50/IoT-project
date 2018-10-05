package com.example.maksy.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailPageActivivty extends AppCompatActivity {
    TextView checkStatus;
    TextView turningTime;
    TextView exceptionTime;
    @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_page);
           initFields();
            String status = getIntent().getStringExtra("status");
            checkStatus.setText(status);
            String turning = getIntent().getStringExtra("turningtime");
            turningTime.setText(turning);
            String exception = getIntent().getStringExtra("exceptiontime");
            exceptionTime.setText(exception);
    }


    public void backToList (View view) {
        Intent intent = new Intent(DetailPageActivivty.this, ListSensorsActivity.class);
        startActivity(intent);
    }
    private void initFields() {
        checkStatus = findViewById(R.id.checkStatus);
        turningTime = findViewById(R.id.turningTime);
        exceptionTime = findViewById(R.id.exceptionTime);
    }
}
