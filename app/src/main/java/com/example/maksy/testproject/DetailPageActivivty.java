package com.example.maksy.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DetailPageActivivty extends AppCompatActivity {
    @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_page);
    }
    public void backToList (View view) {
        Intent intent = new Intent(DetailPageActivivty.this, ListSensorsActivity.class);
        startActivity(intent);
    }
}
