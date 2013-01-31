/**
 * 
 */
package com.ce.service.json;

/**
 * @author sanya
 * 
 */
public class JUser {
    private long   id;
    private String username;
    private String displayName;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the diaplayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param diaplayName
     *            the diaplayName to set
     */
    public void setDisplayName(String diaplayName) {
        this.displayName = diaplayName;
    }

}
