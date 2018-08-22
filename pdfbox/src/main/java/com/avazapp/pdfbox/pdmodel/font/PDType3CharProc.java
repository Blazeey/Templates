package com.avazapp.pdfbox.pdmodel.font;

import com.avazapp.pdfbox.contentstream.PDContentStream;
import com.avazapp.pdfbox.cos.COSStream;
import com.avazapp.pdfbox.pdmodel.PDResources;
import com.avazapp.pdfbox.pdmodel.common.COSObjectable;
import com.avazapp.pdfbox.pdmodel.common.PDRectangle;
import com.avazapp.pdfbox.pdmodel.common.PDStream;
import com.avazapp.pdfbox.util.Matrix;

import java.io.IOException;
import java.io.InputStream;

/**
 * A Type 3 character procedure. This is a standalone PDF content stream.
 *
 * @author John Hewson
 */
public final class PDType3CharProc implements COSObjectable, PDContentStream
{
	private final PDType3Font font;
	private final COSStream charStream;

	public PDType3CharProc(PDType3Font font, COSStream charStream)
	{
		this.font = font;
		this.charStream = charStream;
	}

	@Override
	public COSStream getCOSObject()
	{
		return charStream;
	}

	public PDType3Font getFont()
	{
		return font;
	}

    public PDStream getContentStream()
    {
        return new PDStream(charStream);
    }

	@Override
    public InputStream getContents() throws IOException
    {
        return charStream.getUnfilteredStream();
    }

	@Override
	public PDResources getResources()
	{
		return font.getResources();
	}

	@Override
	public PDRectangle getBBox()
	{
		return font.getFontBBox();
	}
	
	@Override
	public Matrix getMatrix() {
		return font.getFontMatrix();
	}

	// todo: add methods for getting the character's width from the stream
}