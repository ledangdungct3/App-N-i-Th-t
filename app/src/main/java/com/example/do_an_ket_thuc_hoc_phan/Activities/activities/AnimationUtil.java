package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ramotion.foldingcell.animations.AnimationEndListener;

public class AnimationUtil {
    private static final int  ANIMATION_DURUTION = 1000;
    private static boolean isani;
    public static void translate(final ImageView imageView, final View start, View end,
                                 final Animation.AnimationListener animationListener){
        start.setDrawingCacheEnabled(true);
        Bitmap cache = start.getDrawingCache();
        if(cache == null){
            return;
        }
        Bitmap bitmap = Bitmap.createBitmap(cache);
        start.setDrawingCacheEnabled(false);
        imageView.setImageBitmap(bitmap);
        float startwidthcenter = start.getWidth()/2;
        float startheightcenter = start.getHeight()/2;
        float endtwidthcenter = end.getWidth()*0.75f;
        float endtheightcenter = end.getHeight()/2f;
        final int[] startpos = new int[2];
        start.getLocationOnScreen(startpos);
        final int[] endpos = new int[2];
        end.getLocationOnScreen(endpos);
        float fromX = startpos[0];
        float toX = endpos[0] + endtwidthcenter - startwidthcenter;
        float fromY = startpos[1] - startheightcenter;
        float toY = endpos[1] - endtheightcenter +  startheightcenter;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateInterpolator());
        int animationDurution = 200;
        ScaleAnimation startscaleAnimation = new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,startwidthcenter
        ,startheightcenter);
        startscaleAnimation.setDuration(animationDurution);

        TranslateAnimation translateAnimation = new TranslateAnimation(fromX,toX,fromY,toY);
        translateAnimation.setStartOffset(animationDurution);
        translateAnimation.setDuration(ANIMATION_DURUTION);

        ScaleAnimation transcaleAnimation = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,toX,toY);
        transcaleAnimation.setStartOffset(animationDurution);
        transcaleAnimation.setDuration(ANIMATION_DURUTION);
        animationSet.addAnimation(startscaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(transcaleAnimation);
        if(isani){
            imageView.clearAnimation();
            if(animationListener != null){
                animationListener.onAnimationEnd(null);
            }
        }
        imageView.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isani = true;
                imageView.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                if(animationListener != null){
                    animationListener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                if(animationListener != null){
                    animationListener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(animationListener != null){
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });


    }
}
