package com.blazeey.templates.Utils.PdfUtils.pdfutils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.avazapp.pdfbox.pdmodel.PDDocument;
import com.avazapp.pdfbox.pdmodel.PDPage;
import com.avazapp.pdfbox.pdmodel.PDPageContentStream;
import com.avazapp.pdfbox.pdmodel.common.PDRectangle;
import com.avazapp.pdfbox.pdmodel.graphics.image.LosslessFactory;
import com.avazapp.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.blazeey.templates.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PrintPdfAdapter {

    private Context context;
    private String pdfPath;
    private PDDocument document;
    private List<PageData> pageDataList;
    private int PAGE_WIDTH;
    private int PAGE_HEIGHT;
    private int gridWidth, gridHeight;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault());

    public PrintPdfAdapter(Context context, int width, int height, String path) {
        this.context = context;
        this.gridHeight = height;
        this.gridWidth = width;

        PAGE_WIDTH = (int) ((gridWidth * 11) / 9.5);
        PAGE_HEIGHT = (int) ((gridHeight * 8) / 6.0);
        document = new PDDocument();
        pdfPath = "";
    }

    public void onWrite(WriteListener listener) {
        int start = 0, end = pageDataList.size() - 1;

        File folder = new File("");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (!success) {
            listener.onFailed(new IOException());
        }
        try {
            for (int i = start; i <= end; i++) {
                View pageView = drawView(pageDataList.get(i));
                Log.d("PDFDoc", "Page start:" + i);
                createPage(pageView);
            }
            document.save(pdfPath);
            document.close();
            openPdf(pdfPath);
        } catch (IOException e) {
            listener.onFailed(e);
            e.printStackTrace();
        }
    }

    private View drawView(PageData pageData) {
        View view = null;

        view.measure(PAGE_WIDTH, PAGE_HEIGHT);
        view.layout(0, 0, PAGE_WIDTH, PAGE_HEIGHT);

        return view;
    }

    private void createPage(View view) throws IOException {

        PDRectangle pdfRect = new PDRectangle(view.getMeasuredWidth(), view.getMeasuredHeight());
        PDPage page = new PDPage(pdfRect);
        document.addPage(page);

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.draw(c);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject alphaXimage = LosslessFactory.createFromImage(document, bitmap);
        contentStream.drawImage(alphaXimage, 0, 0);

        contentStream.close();

    }

    private void openPdf(String path) {
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

    public interface WriteListener {
        void onPageComplete(int page, int total);

        void onFailed(Exception e);
    }
}
