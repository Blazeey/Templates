package com.avazapp.pdfbox.contentstream.operator.state;

import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSArray;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;

/**
 * d: Set the line dash pattern.
 *
 * @author Ben Litchfield
 */
public class SetLineDashPattern extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments)
    {
        COSArray dashArray = (COSArray) arguments.get(0);
        int dashPhase = ((COSNumber) arguments.get(1)).intValue();
        context.setLineDashPattern(dashArray, dashPhase);
    }

    @Override
    public String getName()
    {
        return "d";
    }
}
