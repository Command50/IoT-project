package com.example.maksy.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DetailPageActivityTwo  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page_two);
    }

    public void backToList(View view) {
        Intent intent = new Intent(DetailPageActivityTwo.this, ListSensorsActivity.class);
        startActivity(intent);
    }
}