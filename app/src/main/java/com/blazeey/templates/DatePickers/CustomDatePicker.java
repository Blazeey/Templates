package com.blazeey.templates.DatePickers;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.blazeey.templates.R;

import java.util.Calendar;

import static com.blazeey.templates.Utils.Screen.FULL_SCREEN_FLAG;
import static com.blazeey.templates.Utils.Screen.getAppUsableScreenSize;

/**
 * Custom datepicker dialog for analytics fragment
 */

public class CustomDatePicker extends Dialog {

    private Calendar currentDate;
    private DatePicker datePicker;
    private Calendar selectedDate;
    private LinearLayout rootLayout;
    private Context context;

    CustomDatePicker(@NonNull Context context, Calendar currentDate) {
        super(context);
        this.context = context;
        this.currentDate = currentDate;
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getDecorView().setSystemUiVisibility(FULL_SCREEN_FLAG);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        setContentView(R.layout.custom_date_picker);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        makePreparations();
    }

    /**
     * Sets variables
     */

    private void makePreparations() {
        datePicker = findViewById(R.id.date_picker);
        rootLayout = findViewById(R.id.custom_date_picker_root);

        datePicker.init(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = returnDate();
                dismiss();
            }
        });
    }

    /**
     * Returns the today's date as Date object
     * @return data - today's date
     */
    private Calendar returnDate() {
        Calendar date = Calendar.getInstance();
        date.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return date;
    }

    /**
     * Sets the minimum date and maximum date of the date picker and displays it as a dialog
     * @param v view to which the date picker corresponds to.
     * @param minDate minimum date that can be selected
     * @param maxDate maximum date that can be selected
     * @param currentDate today's date
     */

    public void showPicker(final View v, Calendar minDate, Calendar maxDate, Calendar currentDate) {
        datePicker.setMinDate(0);
        datePicker.setMaxDate(0);
        datePicker.setMinDate(minDate.getTime().getTime());
        maxDate.set(Calendar.HOUR_OF_DAY, 23);
        maxDate.set(Calendar.MINUTE, 59);
        maxDate.set(Calendar.SECOND, 58);
        datePicker.setMaxDate(maxDate.getTime().getTime());
        datePicker.updateDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        show();
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Rect rectf = new Rect();
                v.getGlobalVisibleRect(rectf);
                int screenHeight = getAppUsableScreenSize(context).y;
                int dialogHeight = rootLayout.getHeight();
                int viewBottom = rectf.bottom;
                int viewTop = rectf.top;
                int viewLeft = rectf.left;
                int dialogLeft = viewLeft - 20;
                int dialogTop = viewBottom;
                if (dialogTop + dialogHeight > screenHeight) {
                    dialogTop = viewTop - dialogHeight;
                }
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.gravity = Gravity.START | Gravity.TOP;
                layoutParams.x = dialogLeft;
                layoutParams.y = dialogTop;
                getWindow().setAttributes(layoutParams);
            }
        });
    }

    public Calendar getSelectedDate() {
        return selectedDate;
    }
}
