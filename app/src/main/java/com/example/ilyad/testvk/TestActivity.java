package com.example.ilyad.testvk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawView drawView = new DrawView(this);
        setContentView(drawView);

    }

}
