package com.avazapp.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.util.Matrix;

/**
 * BT: Begin text.
 *
 * @author Ben Litchfield
 * @author Laurent Huault
 */
public class BeginText extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
        context.setTextMatrix( new Matrix());
        context.setTextLineMatrix( new Matrix() );
        context.beginText();
    }

    @Override
    public String getName()
    {
        return "BT";
    }
}
