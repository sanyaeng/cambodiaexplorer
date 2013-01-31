/**
 * 
 */
package com.ce.service.json;

/**
 * @author sanyaeng
 * 
 */
public class JBussinessCategory {

    private String catKey  = null;
    private String catDesc = null;
    private Long   id      = null;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the catKey
     */
    public String getCatKey() {
        return catKey;
    }

    /**
     * @param catKey
     *            the catKey to set
     */
    public void setCatKey(String catKey) {
        this.catKey = catKey;
    }

    /**
     * @return the catDesc
     */
    public String getCatDesc() {
        return catDesc;
    }

    /**
     * @param catDesc
     *            the catDesc to set
     */
    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

}
