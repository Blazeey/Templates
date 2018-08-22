package com.avazapp.pdfbox.pdmodel.interactive.annotation;

import com.avazapp.pdfbox.cos.COSStream;
import com.avazapp.pdfbox.pdmodel.PDDocument;
import com.avazapp.pdfbox.pdmodel.graphics.form.PDFormXObject;

/**
 * An appearance stream is a form XObject, a self-contained content stream that shall be rendered
 * inside the annotation rectangle.
 *
 * @author Ben Litchfield
 * @author John Hewson
 */
public class PDAppearanceStream extends PDFormXObject
{
    /**
     * Creates a Form XObject for reading.
     * @param stream The XObject stream
     */
    public PDAppearanceStream(COSStream stream)
    {
        super(stream);
    }

    /**
     * Creates a Form Image XObject for writing, in the given document.
     * @param document The current document
     */
    public PDAppearanceStream(PDDocument document)
    {
        super(document);
    }
}