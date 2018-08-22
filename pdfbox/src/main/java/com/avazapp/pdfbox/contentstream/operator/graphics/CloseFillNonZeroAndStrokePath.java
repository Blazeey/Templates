package com.avazapp.pdfbox.contentstream.operator.graphics;

import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.cos.COSBase;

import java.io.IOException;

/**
 * b Close, fill and stroke the path with non-zero winding rule.
 *
 * @author Ben Litchfield
 */
public final class CloseFillNonZeroAndStrokePath extends GraphicsOperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> operands) throws IOException
    {
        context.processOperator("h", operands);  // ClosePath
        context.processOperator("B", operands);  // FillNonZeroAndStroke
    }

    @Override
    public String getName()
    {
        return "b";
    }
}
