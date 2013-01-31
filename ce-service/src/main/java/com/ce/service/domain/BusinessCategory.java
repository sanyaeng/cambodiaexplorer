/**
 * 
 */
package com.ce.service.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 * @author sanyaeng
 * 
 */
@Entity
public class BusinessCategory extends AbstractLongEntity {// extends AEDStringEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2343650913953925776L;

    @Basic
    private String            catKey;

    @Basic
    private String            catDesc;

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
