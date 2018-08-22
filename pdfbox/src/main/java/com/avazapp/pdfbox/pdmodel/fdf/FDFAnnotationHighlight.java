package com.avazapp.pdfbox.pdmodel.fdf;

import com.avazapp.pdfbox.cos.COSDictionary;
import com.avazapp.pdfbox.cos.COSName;

import org.w3c.dom.Element;

import java.io.IOException;

/**
 * This represents a Highlight FDF annotation.
 *
 * @author Ben Litchfield
 */
public class FDFAnnotationHighlight extends FDFAnnotationTextMarkup
{
    /**
     * COS Model value for SubType entry.
     */
    public static final String SUBTYPE ="Highlight";

    /**
     * Default constructor.
     */
    public FDFAnnotationHighlight()
    {
        super();
        annot.setName( COSName.SUBTYPE, SUBTYPE );
    }

    /**
     * Constructor.
     *
     * @param a An existing FDF Annotation.
     */
    public FDFAnnotationHighlight( COSDictionary a )
    {
        super( a );
    }

    /**
     * Constructor.
     *
     *  @param element An XFDF element.
     *
     *  @throws IOException If there is an error extracting information from the element.
     */
    public FDFAnnotationHighlight( Element element ) throws IOException
    {
        super( element );
        annot.setName( COSName.SUBTYPE, SUBTYPE );
    }
}
