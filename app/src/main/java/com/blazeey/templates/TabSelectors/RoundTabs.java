package com.blazeey.templates.TabSelectors;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.blazeey.templates.R;

public class RoundTabs {
    private static final int SELECTED_DOT = R.drawable.selected_dot;
    private static final int UNSELECTED_DOT = R.drawable.un_selected_dot;

    public static  void setTabs(TabLayout pagerTabs,Context context) {
        int resId;
        for (int i = 0; i < pagerTabs.getTabCount(); i++) {
            TabLayout.Tab tab = pagerTabs.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.dots_selected_custom);
                View customView = tab.getCustomView();
                if (customView != null) {
                    ImageView tab_image = customView.findViewById(R.id.dots_image);
                    if (i == 0)
                        resId = SELECTED_DOT;
                    else
                        resId = UNSELECTED_DOT;
                    tab_image.setImageDrawable(context.getResources().getDrawable(resId));
                }
            }
        }
    }

    public static TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            View customView = tab.getCustomView();
            if (customView != null) {
                ImageView tab_image = customView.findViewById(R.id.dots_image);
//                tab_image.setImageDrawable(getResources().getDrawable(SELECTED_DOT));
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            View customView = tab.getCustomView();
            if (customView != null) {
                ImageView tab_image = customView.findViewById(R.id.dots_image);
//                tab_image.setImageDrawable(getResources().getDrawable(UNSELECTED_DOT));
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
