package com.avazapp.pdfbox.contentstream.operator.graphics;

import com.avazapp.pdfbox.contentstream.PDFGraphicsStreamEngine;
import com.avazapp.pdfbox.contentstream.PDFStreamEngine;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;

/**
 * Base class for graphics operators.
 *
 * @author John Hewson
 */
public abstract class GraphicsOperatorProcessor extends OperatorProcessor
{
    /** The processing context. */
    protected PDFGraphicsStreamEngine context;

    @Override
    public void setContext(PDFStreamEngine context)
    {
        super.setContext(context);
        this.context = (PDFGraphicsStreamEngine)context;
    }
}
