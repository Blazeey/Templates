package com.blazeey.templates.Utils.PdfUtils.pdfutils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blazeey.templates.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfCreator {
    private static final int PAGE_LIMIT = 8;
    private static final int PAGE_WIDTH = 768;
    private static final int PAGE_HEIGHT = 1366;
    private Context context;
    private PdfDocument pdfDocument;
    private int pageNumber;
    private SharedPreferences prefs;

    public PdfCreator(Context context) {
        this.context = context;
        this.pdfDocument = new PdfDocument();
        this.pageNumber = 0;
    }

    public void createPdf() {
        drawSentence();
    }

    private void drawSentence() {
            View view = drawView();
            createPage(view);
    }

    private View drawView() {
        View view = null;
        return view;
    }

    private void createPage(View view) {

        view.measure(PAGE_WIDTH, PAGE_HEIGHT);
        view.layout(0, 0, PAGE_WIDTH, PAGE_HEIGHT);

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber++).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        view.draw(page.getCanvas());
        pdfDocument.finishPage(page);
    }

    public void savePdf(String path) {
        File file = new File(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pdfDocument.writeTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private View drawItem() {

        View view = null;
        return view;
    }

    public void openPdf(String path) {
        File file = new File(path);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), "application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }
    }
}
