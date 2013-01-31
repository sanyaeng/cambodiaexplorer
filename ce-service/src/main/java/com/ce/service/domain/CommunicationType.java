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
public class CommunicationType extends AbstractLongEntity {// extends AEDStringEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3462271045437752734L;

    @Basic
    private String            type;

    @Basic
    private String            description;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
