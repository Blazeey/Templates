package com.blazeey.templates.Utils;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimUtils {
    float zIndex;

    public AnimUtils(float zIndex) {
        this.zIndex = zIndex;
    }

    public void zoomToCenterAnimation(final View view, final View parent, final float endSize, final String speed, final Animation.AnimationListener animationListener, boolean useViewTree) {
        zIndex = zIndex + 1;
        Log.v("Anim Utils", "ye");
        if (useViewTree)
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    zoomAnimate(view, parent, endSize, speed, animationListener);
                }
            });
        else
            zoomAnimate(view, parent, endSize, speed, animationListener);

    }

    private void zoomAnimate(final View view, final View parent, final float endSize, final String speed, final Animation.AnimationListener animationListener) {
        view.setElevation(zIndex);
        AnimationSet animationSet = new AnimationSet(true);
        float viewWidth = view.getWidth();
        float viewHeight = view.getHeight();
        int destwidth = (int) ((viewWidth / (1 / endSize)) * endSize);
        int destHeight = (int) ((viewHeight / (1 / endSize)) * endSize);

        int originalPos[] = new int[2];
        originalPos[0] = view.getLeft();
        originalPos[1] = view.getTop();

        Rect parentRect = new Rect();
        parent.getGlobalVisibleRect(parentRect);
        int xDest = (parentRect.width() / 2) - (destwidth / 2);
        int yDest = (parentRect.height() / 2) - (destHeight / 2);

        TranslateAnimation front = new TranslateAnimation(0, (xDest - originalPos[0]) / (endSize * endSize), 0, (yDest - originalPos[1]) / (endSize * endSize));
        front.setFillAfter(false);
        if (speed.equalsIgnoreCase("fast"))
            front.setDuration(750);
        else if (speed.equalsIgnoreCase("slow"))
            front.setDuration(1500);
        else//default
            front.setDuration(1000);
        animationSet.addAnimation(front);

        TranslateAnimation back = new TranslateAnimation(0, (originalPos[0] - xDest) / (endSize * endSize), 0, (originalPos[1] - yDest) / (endSize * endSize));
        back.setFillBefore(true);
        if (speed.equalsIgnoreCase("fast")) {
            back.setDuration(750);
            back.setStartOffset(1500);
        } else if (speed.equalsIgnoreCase("slow")) {
            back.setDuration(1500);
            back.setStartOffset(3000);
        } else {//default
            back.setDuration(1000);
            back.setStartOffset(2000);
        }
        animationSet.addAnimation(back);

        ScaleAnimation expand = new ScaleAnimation(1 / endSize, endSize, 1 / endSize, endSize);
        expand.setFillAfter(false);

        if (speed.equalsIgnoreCase("fast"))
            expand.setDuration(750);
        else if (speed.equalsIgnoreCase("slow"))
            expand.setDuration(1500);
        else//default
            expand.setDuration(1000);

        animationSet.addAnimation(expand);

        ScaleAnimation shrink = new ScaleAnimation(endSize, 1 / endSize, endSize, 1 / endSize);
        shrink.setFillBefore(false);
        if (speed.equalsIgnoreCase("fast")) {
            shrink.setDuration(750);
            shrink.setStartOffset(1500);
        } else if (speed.equalsIgnoreCase("slow")) {
            shrink.setDuration(1500);
            shrink.setStartOffset(3000);
        } else {//default
            shrink.setDuration(1000);
            shrink.setStartOffset(2000);
        }
        animationSet.addAnimation(shrink);

        view.startAnimation(animationSet);
        view.getAnimation().setAnimationListener(animationListener);
    }
}
