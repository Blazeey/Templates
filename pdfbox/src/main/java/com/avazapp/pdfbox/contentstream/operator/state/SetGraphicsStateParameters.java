package com.avazapp.pdfbox.contentstream.operator.state;

import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSName;
import com.avazapp.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

import java.io.IOException;

/**
 * gs: Set parameters from graphics state parameter dictionary.
 *
 * @author Ben Litchfield
 */
public class SetGraphicsStateParameters extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws IOException
    {
        // set parameters from graphics state parameter dictionary
        COSName graphicsName = (COSName)arguments.get( 0 );
        PDExtendedGraphicsState gs = context.getResources().getExtGState( graphicsName );
        gs.copyIntoGraphicsState( context.getGraphicsState() );
    }

    @Override
    public String getName()
    {
        return "gs";
    }
}
