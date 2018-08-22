package com.avazapp.pdfbox.pdmodel;

import com.avazapp.pdfbox.cos.COSBase;
import com.avazapp.pdfbox.cos.COSDictionary;
import com.avazapp.pdfbox.pdmodel.common.PDNameTreeNode;
import com.avazapp.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;

import java.io.IOException;

/**
 * This class holds all of the name trees that are available at the document level.
 *
 * @author Ben Litchfield
 */
public class PDEmbeddedFilesNameTreeNode extends PDNameTreeNode<PDComplexFileSpecification>
{
    /**
     * Constructor.
     */
    public PDEmbeddedFilesNameTreeNode()
    {
        super();
    }

    /**
     * Constructor.
     *
     * @param dic The COS dictionary.
     */
    public PDEmbeddedFilesNameTreeNode( COSDictionary dic )
    {
        super(dic);
    }

    @Override
    protected PDComplexFileSpecification convertCOSToPD(COSBase base) throws IOException
    {
        return new PDComplexFileSpecification( (COSDictionary)base );
    }

    @Override
    protected PDNameTreeNode createChildNode( COSDictionary dic )
    {
        return new PDEmbeddedFilesNameTreeNode(dic);
    }
}
