package com.avazapp.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.MissingOperandException;
import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;

/**
 * w: Set line width.
 *
 * @author Ben Litchfield
 */
public class SetLineWidth extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
    	if (arguments.size() < 1)
    	{
    		throw new MissingOperandException(operator, arguments);
    	}
        COSNumber width = (COSNumber) arguments.get(0);
        context.getGraphicsState().setLineWidth(width.floatValue());
    }

    @Override
    public String getName()
    {
        return "w";
    }
}
