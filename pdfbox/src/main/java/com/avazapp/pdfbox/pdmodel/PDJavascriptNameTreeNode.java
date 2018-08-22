package com.avazapp.pdfbox.pdmodel;

import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSDictionary;
import com.avazapp.pdfbox.pdmodel.common.PDNameTreeNode;
import com.avazapp.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.avazapp.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

import java.io.IOException;

/**
 * This class holds all of the name trees that are available at the document level.
 *
 * @author Ben Litchfield
 */
public class PDJavascriptNameTreeNode extends PDNameTreeNode<PDActionJavaScript>
{
    /**
     * Constructor.
     */
    public PDJavascriptNameTreeNode()
    {
        super();
    }

    /**
     * Constructor.
     *
     * @param dic The COS dictionary.
     */
    public PDJavascriptNameTreeNode( COSDictionary dic )
    {
        super(dic);
    }

    @Override
    protected PDActionJavaScript convertCOSToPD( COSBase base ) throws IOException
    {
        if (!(base instanceof COSDictionary))
        {
            throw new IOException(
                "Error creating Javascript object, expected a COSDictionary and not " + base);
        }
        return (PDActionJavaScript)PDActionFactory.createAction((COSDictionary) base);
    }

    @Override
    protected PDNameTreeNode createChildNode( COSDictionary dic )
    {
        return new PDJavascriptNameTreeNode(dic);
    }
}
