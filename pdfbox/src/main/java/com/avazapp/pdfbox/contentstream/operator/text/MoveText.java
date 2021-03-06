package com.avazapp.pdfbox.contentstream.operator.text;

import android.util.Log;

import com.avazapp.pdfbox.contentstream.operator.MissingOperandException;
import com.avazapp.pdfbox.contentstream.operator.Operator;
import com.avazapp.pdfbox.contentstream.operator.OperatorProcessor;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSNumber;
import com.avazapp.pdfbox.util.Matrix;

import java.util.List;

/**
 * Td: Move text position.
 *
 * @author Laurent Huault
 */
public class MoveText extends OperatorProcessor
{
    @Override
    public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException
    {
        if (arguments.size() < 2)
        {
            throw new MissingOperandException(operator, arguments);
        }
        Matrix textLineMatrix = context.getTextLineMatrix();
        if (textLineMatrix == null)
        {
            Log.w("PdfBox-Android", "TextLineMatrix is null, " + getName() + " operator will be ignored");
            return;
        }

        COSNumber x = (COSNumber) arguments.get(0);
        COSNumber y = (COSNumber) arguments.get(1);
        Matrix matrix = new Matrix(1, 0, 0, 1, x.floatValue(), y.floatValue());
        textLineMatrix.concatenate(matrix);
        context.setTextMatrix(textLineMatrix.clone());
    }

    @Override
    public String getName()
    {
        return "Td";
    }
}
