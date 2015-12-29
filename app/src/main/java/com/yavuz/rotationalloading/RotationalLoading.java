package com.yavuz.rotationalloading;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yavuz.rotationalloading.R;

/**
 * Created by yavuz on 27.12.2015.
 */
public class RotationalLoading {

    private AppCompatActivity activity;

    private int drawableID;
    private int layoutID;
    private int animationID;
    private boolean isCancelable = false;

    private Dialog dialog;

    public RotationalLoading(AppCompatActivity activity, int drawableID, int layoutID, int animationID, boolean isCancelable) {
        this.activity = activity;
        this.drawableID = drawableID;
        this.layoutID = layoutID;
        this.animationID = animationID;
        this.isCancelable = isCancelable;
    }

    public RotationalLoading(AppCompatActivity activity, int drawableID, int layoutID, int animationID) {
        this.activity = activity;
        this.drawableID = drawableID;
        this.layoutID = layoutID;
        this.animationID = animationID;
        initializeDialog();
    }

    public void initializeDialog() {
        Dialog dialog = new Dialog(this.activity, R.style.RotationalLoadingTheme);
        dialog.setContentView(this.layoutID);
        dialog.setCancelable(this.isCancelable);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageDrawable(ContextCompat.getDrawable(this.activity, this.drawableID));
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) image.getLayoutParams();

        lParams.height = this.activity.getResources().getDisplayMetrics().widthPixels / 3;
        lParams.width = this.activity.getResources().getDisplayMetrics().widthPixels / 3;

        image.setLayoutParams(lParams);

        Animation rotation = AnimationUtils.loadAnimation(this.activity, this.animationID);
        rotation.setRepeatCount(Animation.INFINITE);
        image.startAnimation(rotation);

        this.dialog = dialog;
    }

    public void show() {
        this.dialog.show();
    }

    public void hide() {
        if (this.dialog.isShowing()) {
            this.dialog.hide();
        }
    }

    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    public void setHeight(int heightPixels) {
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        lParams.height = heightPixels;
        lParams.width = this.activity.getResources().getDisplayMetrics().widthPixels / 3;
        image.setLayoutParams(lParams);
    }

    public void setWidth(int widthPixels) {
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        lParams.width = widthPixels;
        image.setLayoutParams(lParams);
    }
}
