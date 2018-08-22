package com.blazeey.templates.Utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.SearchView;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewUtils {
    public static void changeTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * Setting the text size for the searchview. Got from stackoverflow
     * @param searchView the searchview for which the textsize is to be set
     * @param fontSize the font size
     */
    public static void setSearchviewTextSize(SearchView searchView, int fontSize) {
        try {
            AutoCompleteTextView autoCompleteTextViewSearch = (AutoCompleteTextView) searchView.findViewById(searchView.getContext().getResources().getIdentifier("app:id/search_src_text", null, null));
            if (autoCompleteTextViewSearch != null) {
                autoCompleteTextViewSearch.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            } else {
                LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
                LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
                LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
                AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
                autoComplete.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            }
        } catch (Exception e) {
            LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
            LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
            AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
            autoComplete.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
    }

    /**
     * setting the image and the text for the searchview
     */
    public SpannableString setImageSpan(Context context,String text,int drawable) {

        SpannableString spannableString;
        CenteredImageSpan imageSpan = new CenteredImageSpan(context, drawable);
        spannableString = new SpannableString(text);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private String getTruncateText(String text, Button bt) {
        Paint paint = bt.getPaint();
        int width = (int) (1.5 * bt.getWidth());
        String all[] = text.split(" ");
        if (paint.measureText(text) <= width)
            return text;
        else {
            String ret = "";
            for (int i = 0; i < all.length - 1; i++) {
                ret = ret + all[i] + " ";
                int wd = (int) paint.measureText(ret + " " + all[all.length - 2] + " ... " + all[all.length - 1]);
                if (wd >= width)
                    return ret + "... " + all[all.length - 1];
            }
        }
        return text;
    }

}
