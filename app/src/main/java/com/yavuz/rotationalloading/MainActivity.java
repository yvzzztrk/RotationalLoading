package com.yavuz.rotationalloading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int SHOW_TIME = 5000;
    Button buttonShow;
    RotationalLoading rotationalLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        rotationalLoading = new RotationalLoading(this,
                R.drawable.doge,
                R.layout.rotational_loading,
                R.anim.rotation);

        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotationalLoading.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rotationalLoading.hide();
                    }
                }, SHOW_TIME);
            }
        });
    }
}
