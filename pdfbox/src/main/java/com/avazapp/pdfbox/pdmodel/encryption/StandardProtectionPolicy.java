package com.avazapp.pdfbox.pdmodel.encryption;

/**
 * The protection policy to add to a document for password-based protection.
 *
 * The following example shows how to protect a PDF document with password.
 * In this example, the document will be protected so that someone opening
 * the document with the user password <code>user_pwd</code> will not be
 * able to modify the document.
 *
 * <pre>
 * AccessPermission ap = new AccessPermission();
 * ap.setCanModify(false);
 * StandardProtectionPolicy policy = new StandardProtectionPolicy(owner_pwd, user_pwd, ap);
 * doc.protect(policy);
 * </pre>
 *
 * @author Benoit Guillon
 */
public final class StandardProtectionPolicy extends ProtectionPolicy
{
    private AccessPermission permissions;
    private String ownerPassword = "";
    private String userPassword = "";

    /**
     * Creates an new instance of the standard protection policy
     * in order to protect a PDF document with passwords.
     *
     * @param ownerPassword The owner's password.
     * @param userPassword The users's password.
     * @param permissions The access permissions given to the user.
     */
    public StandardProtectionPolicy(String ownerPassword, String userPassword,
                                    AccessPermission permissions)
    {
        this.ownerPassword = ownerPassword;
        this.userPassword = userPassword;
        this.permissions = permissions;
    }

    /**
     * Returns the access permissions
     * @return the access permissions
     */
    public AccessPermission getPermissions()
    {
        return permissions;
    }

    /**
     * Sets the access permissions
     * @param permissions the new access permissions
     */
    public void setPermissions(AccessPermission permissions)
    {
        this.permissions = permissions;
    }

    /**
     * Returns the owner password.
     * @return the owner password
     */
    public String getOwnerPassword()
    {
        return ownerPassword;
    }

    /**
     * Sets the owner password
     * @param ownerPassword the new owner password
     */
    public void setOwnerPassword(String ownerPassword)
    {
        this.ownerPassword = ownerPassword;
    }

    /**
     * Returns the user password.
     * @return the user password
     */
    public String getUserPassword()
    {
        return userPassword;
    }

    /**
     * Sets the user password.
     * @param userPassword the new user password
     */
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
}