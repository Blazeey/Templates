package com.avazapp.pdfbox.pdmodel.graphics;

import com.avazapp.pdfbox.cos.COSArray;
import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSDictionary;
import com.avazapp.pdfbox.cos.COSFloat;
import com.avazapp.pdfbox.cos.COSNumber;
import com.avazapp.pdfbox.pdmodel.common.COSObjectable;
import com.avazapp.pdfbox.pdmodel.font.PDFont;
import com.avazapp.pdfbox.pdmodel.font.PDFontFactory;

import java.io.IOException;

/**
 * This class represents a font setting used for the graphics state.  A font setting is a font and a
 * font size.  Maybe there is a better name for this?
 *
 * @author Ben Litchfield
 */
public class PDFontSetting implements COSObjectable
{
    private COSArray fontSetting = null;

    /**
     * Creates a blank font setting, font will be null, size will be 1.
     */
    public PDFontSetting()
    {
        fontSetting = new COSArray();
        fontSetting.add( null );
        fontSetting.add( new COSFloat( 1 ) );
    }

    /**
     * Constructs a font setting from an existing array.
     *
     * @param fs The new font setting value.
     */
    public PDFontSetting( COSArray fs )
    {
        fontSetting = fs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public COSBase getCOSObject()
    {
        return fontSetting;
    }

    /**
     * This will get the font for this font setting.
     *
     * @return The font for this setting of null if one was not found.
     *
     * @throws IOException If there is an error getting the font.
     */
    public PDFont getFont() throws IOException
    {
        PDFont retval = null;
        COSBase font = fontSetting.getObject(0);
        if( font instanceof COSDictionary )
        {
            retval = PDFontFactory.createFont( (COSDictionary)font );
        }
        return retval;
    }

    /**
     * This will set the font for this font setting.
     *
     * @param font The new font.
     */
    public void setFont( PDFont font )
    {
        fontSetting.set( 0, font );
    }

    /**
     * This will get the size of the font.
     *
     * @return The size of the font.
     */
    public float getFontSize()
    {
        COSNumber size = (COSNumber)fontSetting.get( 1 );
        return size.floatValue();
    }

    /**
     * This will set the size of the font.
     *
     * @param size The new size of the font.
     */
    public void setFontSize( float size )
    {
        fontSetting.set( 1, new COSFloat( size ) );
    }
}
