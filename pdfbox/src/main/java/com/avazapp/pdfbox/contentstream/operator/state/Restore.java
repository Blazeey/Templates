package com.avazapp.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;

/**
 * Q: Restore the graphics state.
 * 
 * @author Laurent Huault
 */
public class Restore extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
        if (context.getGraphicsStackSize() > 1)
        {
            context.restoreGraphicsState();
        }
        else
        {
            // this shouldn't happen but it does, see PDFBOX-161
        	throw new EmptyGraphicsStackException();
        }
    }

    @Override
    public String getName()
    {
        return "Q";
    }
}
