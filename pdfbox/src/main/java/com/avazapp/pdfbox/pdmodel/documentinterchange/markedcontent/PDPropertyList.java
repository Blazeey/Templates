package com.avazapp.pdfbox.pdmodel.documentinterchange.markedcontent;

import com.avazapp.pdfbox.cos.COSDictionary;
import com.avazapp.pdfbox.cos.COSName;
import com.avazapp.pdfbox.pdmodel.common.COSObjectable;
import com.avazapp.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;

/**
 * A property list is a dictionary containing private information meaningful to the conforming
 * writer creating the marked content.
 */
public class PDPropertyList implements COSObjectable
{
    protected final COSDictionary dict;

    /**
     * Creates a property list from the given dictionary.
     * @param dict COS dictionary
     */
    public static PDPropertyList create(COSDictionary dict)
    {
        if (COSName.OCG.equals(dict.getItem(COSName.TYPE)))
        {
            return new PDOptionalContentGroup(dict);
        }
        else
        {
            // todo: more types
            return new PDPropertyList(dict);
        }
    }

    /**
     * Constructor for subclasses.
     */
    protected PDPropertyList()
    {
        this.dict = new COSDictionary();
    }

    /**
     * Constructor for subclasses.
     */
    protected PDPropertyList(COSDictionary dict)
    {
        this.dict = dict;
    }

    @Override
    public COSDictionary getCOSObject()
    {
        return dict;
    }
}
