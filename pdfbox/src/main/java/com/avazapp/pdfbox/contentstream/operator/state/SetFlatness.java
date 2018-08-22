package com.avazapp.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;

/**
 * i: Set the flatness tolerance.
 *
 * @author John Hewson
 */
public class SetFlatness extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> operands) throws IOException
    {
        COSNumber value = (COSNumber)operands.get(0);
        context.getGraphicsState().setFlatness(value.floatValue());
    }

    @Override
    public String getName()
    {
        return "i";
    }
}
