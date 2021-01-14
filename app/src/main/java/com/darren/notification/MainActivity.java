package com.darren.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShow(View view) {
        Intent intent = new Intent(this, DefaultService.class);
        intent.putExtra(DefaultService.CMD_TAG, DefaultService.SHOW);
        startService(intent);
    }

    public void onHide(View view) {
        Intent intent = new Intent(this, DefaultService.class);
        intent.putExtra(DefaultService.CMD_TAG, DefaultService.HIDE);
        startService(intent);
    }

}