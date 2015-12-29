package com.yavuz.rotationalloading;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yavuz.rotationalloading.R;

/**
 * Created by yavuz on 27.12.2015.
 */
public class RotationalLoading {

    private AppCompatActivity activity;

    private int drawableID;
    private boolean isCancelable = false;
    private int numberOfRotatesPerSecond = 1;

    private Dialog dialog;
    private ImageView imageView;

    public RotationalLoading(AppCompatActivity activity, int drawableID, boolean isCancelable) {
        this.activity = activity;
        this.drawableID = drawableID;
        this.isCancelable = isCancelable;
    }

    public RotationalLoading(AppCompatActivity activity, int drawableID) {
        this.activity = activity;
        this.drawableID = drawableID;
    }

    public void initializeDialog() {

        Dialog dialog = new Dialog(this.activity);
        dialog.setCancelable(this.isCancelable);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ViewGroup.LayoutParams viewGroupLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout relativeLayout = new RelativeLayout(dialog.getContext());
        relativeLayout.setLayoutParams(viewGroupLayoutParams);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.setBackgroundColor(ContextCompat.getColor(this.activity, android.R.color.transparent));

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayoutParams.height = this.activity.getResources().getDisplayMetrics().widthPixels / 3;
        relativeLayoutParams.width = this.activity.getResources().getDisplayMetrics().widthPixels / 3;

        imageView = new ImageView(dialog.getContext());
        imageView.setLayoutParams(relativeLayoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageDrawable(ContextCompat.getDrawable(this.activity, this.drawableID));
        relativeLayout.addView(imageView);

        dialog.setContentView(relativeLayout);

        final AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.setFillAfter(true);
        animationSet.setFillEnabled(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(1000 / this.numberOfRotatesPerSecond);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);

        animationSet.addAnimation(rotateAnimation);

        imageView.startAnimation(animationSet);

        this.dialog = dialog;
        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                imageView.startAnimation(animationSet);
            }
        });
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
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) this.imageView.getLayoutParams();
        lParams.height = heightPixels;
        this.imageView.setLayoutParams(lParams);
    }

    public void setWidth(int widthPixels) {
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) this.imageView.getLayoutParams();
        lParams.width = widthPixels;
        this.imageView.setLayoutParams(lParams);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.imageView.setOnClickListener(listener);
    }

    public void setNumberOfRotatesPerSecond(int numberOfRotatesPerSecond) {
        this.numberOfRotatesPerSecond = numberOfRotatesPerSecond;
    }

    public void setIsCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }
}
