package com.blazeey.templates.Utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

public class AnimationUtils {

    /**
     * set animation for the slideshow for the red marked add word in the bottom left corner
     * @return Animation Drawable
     */
    public AnimationDrawable getAnimationDrawable(int helpImages[],Context context) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i : helpImages)
            animationDrawable.addFrame(context.getResources().getDrawable(i), 2000);

        animationDrawable.setOneShot(false);
        animationDrawable.start();
        return animationDrawable;
    }

}
