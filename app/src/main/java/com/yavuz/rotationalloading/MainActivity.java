package com.yavuz.rotationalloading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonShow;
    RotationalLoading rotationalLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        rotationalLoading = new RotationalLoading(this, R.mipmap.ic_launcher); //create instance

        rotationalLoading.setNumberOfRotatesPerSecond(3); // set these before initializeDialog();
        rotationalLoading.setIsCancelable(true);

        rotationalLoading.initializeDialog();

        rotationalLoading.setHeight(300); // set these after initializeDialog();
        rotationalLoading.setWidth(300);
        rotationalLoading.setOnClickListener(new View.OnClickListener() { // set click listener after initalizeDialog()
            @Override
            public void onClick(View v) {
                rotationalLoading.hide();
            }
        });

        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotationalLoading.show();
            }
        });
    }
}
