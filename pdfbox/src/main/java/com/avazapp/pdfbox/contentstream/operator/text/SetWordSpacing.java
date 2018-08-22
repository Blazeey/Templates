package com.avazapp.pdfbox.contentstream.operator.text;

import java.util.List;

import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;

/**
 * Tw: Set word spacing.
 *
 * @author Laurent Huault
 */
public class SetWordSpacing extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments)
    {
        COSNumber wordSpacing = (COSNumber)arguments.get( 0 );
        context.getGraphicsState().getTextState().setWordSpacing( wordSpacing.floatValue() );
    }

    @Override
    public String getName()
    {
        return "Tw";
    }
}
