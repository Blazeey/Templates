package com.blazeey.templates.Transformers;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Transformer for swiping page in activity fragment
 * transformer is used in viewpager
 */
public class ViewPagerTransformer implements ViewPager.PageTransformer {

    private static final float getAlpha(final float position) {
        return getSlowQuadraticAlpha(position);
    }

    private static final float getLinearAlpha(final float position) {
        if (position <= 0) {
            return 1 + position;
        }
        return 1 - position;
    }

    private static final float getFastQuadraticAlpha(final float position) {
        final float linearAlpha = getLinearAlpha(position);
        return linearAlpha * linearAlpha;
    }

    private static final float getSlowQuadraticAlpha(final float position) {
        return 1 - position * position;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        final int pageWidth = page.getWidth();
        if (position < -0.999f) { // [-Infinity,-1)
            // This page is way off-screen to the left so hide it.
            page.setAlpha(0);
            page.setVisibility(View.GONE);
            page.setTranslationX(pageWidth);
        } else if (position <= 0.999f) { // (-1, 1)
            // The further the page is from being center page the more transparent it is.
            page.setAlpha(getAlpha(position));
            // Counteract the default slide transition
            page.setTranslationX(pageWidth * -position);
            // Make sure the page is visible
            page.setVisibility(View.VISIBLE);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right so hide it.
            page.setAlpha(0);
            page.setVisibility(View.GONE);
            page.setTranslationX(-pageWidth);
        }
    }
}
