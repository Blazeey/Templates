package com.avazapp.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;

/**
 * ET: End text.
 *
 * @author Laurent Huault
 */
public class EndText extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
        context.setTextMatrix(null);
        context.setTextLineMatrix(null);
        context.endText();
    }

    @Override
    public String getName()
    {
        return "ET";
    }
}
