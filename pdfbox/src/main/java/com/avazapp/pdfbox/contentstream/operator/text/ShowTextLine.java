package com.avazapp.pdfbox.contentstream.operator.text;

import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;

import java.io.IOException;

/**
 * ': Move to the next line and show text.
 *
 * @author Laurent Huault
 */
public class ShowTextLine extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
        context.processOperator("T*", null);
        context.processOperator("Tj", arguments);
    }

    @Override
    public String getName()
    {
        return "'";
    }
}
