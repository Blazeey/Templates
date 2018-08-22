package com.avazapp.pdfbox.contentstream.operator.graphics;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;

import android.graphics.PointF;

/**
 * m Begins a new subpath.
 *
 * @author Ben Litchfield
 */
public final class MoveTo extends GraphicsOperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> operands) throws IOException
    {
        COSNumber x = (COSNumber)operands.get(0);
        COSNumber y = (COSNumber)operands.get(1);
        PointF pos = context.transformedPoint(x.floatValue(), y.floatValue());
        context.moveTo(pos.x, pos.y);
    }

    @Override
    public String getName()
    {
        return "m";
    }
}