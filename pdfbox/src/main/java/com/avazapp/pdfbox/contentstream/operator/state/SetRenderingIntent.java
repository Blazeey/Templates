package com.avazapp.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSName;
import com.avazapp.pdfbox.pdmodel.graphics.state.RenderingIntent;

/**
 * ri: Set the rendering intent.
 *
 * @author John Hewson
 */
public class SetRenderingIntent extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> operands) throws IOException
    {
        COSName value = (COSName)operands.get(0);
        context.getGraphicsState().setRenderingIntent(RenderingIntent.fromString(value.getName()));
    }

    @Override
    public String getName()
    {
        return "ri";
    }
}
