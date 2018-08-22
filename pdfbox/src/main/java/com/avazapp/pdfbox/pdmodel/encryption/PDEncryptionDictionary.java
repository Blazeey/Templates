package com.avazapp.pdfbox.pdmodel.encryption;

import com.avazapp.pdfbox.cos.COSDictionary;

/**
 * @deprecated Use {@link PDEncryption } instead
 */
@Deprecated
public class PDEncryptionDictionary extends PDEncryption
{
    /**
     * @deprecated Use {@link PDEncryption } instead
     */
    public PDEncryptionDictionary()
    {
        super();
    }

    /**
     * @deprecated Use {@link PDEncryption#PDEncryption(COSDictionary) } instead
     * @param dictionary a COS encryption dictionary
     */
    public PDEncryptionDictionary(COSDictionary dictionary)
    {
        super(dictionary);
    }
}
