package com.blazeey.templates.Utils.PdfUtils.pdfutils;

import java.util.List;

public class PageData {

    public List<Object> pictureList;
    public String header;
    public String footer;
    public int pageNumber;

    public PageData(List<Object> pictureList, String header, int pageNumber) {
        this.pictureList = pictureList;
        this.header = header;
        this.pageNumber = pageNumber;
    }

}
