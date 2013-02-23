/**
 * 
 */
package com.ce.service.json;

/**
 * @author sanya
 * 
 */
public class JBlob {
    // bloc key
    private String blobKey;

    private String accessUrl;

    public JBlob(String blobKey, String accessUrl) {
        this.blobKey = blobKey;
        this.accessUrl = accessUrl;
    }

    /**
     * @return the blobKey
     */
    public String getBlobKey() {
        return blobKey;
    }

    /**
     * @param blobKey
     *            the blobKey to set
     */
    public void setBlobKey(String blobKey) {
        this.blobKey = blobKey;
    }

    /**
     * Get the url for the blob image
     * 
     * @return the accessUrl
     */
    public String getAccessUrl() {
        return accessUrl;
    }

    /**
     * @param accessUrl
     *            the accessUrl to set
     */
    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

}
