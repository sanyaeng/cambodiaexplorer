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
public class CommunicationOption extends AbstractLongEntity {// extends AEDLongEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2756402464652335372L;

    @Basic
    private Long              businessId;

    @Basic
    private Long              communicationTypeId;

    @Basic
    private String            useValue;

    @Basic
    private String            displayValue;

    /**
     * @return the businessId
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId
     *            the businessId to set
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * @return the communicationTypeId
     */
    public Long getCommunicationTypeId() {
        return communicationTypeId;
    }

    /**
     * @param communicationTypeId
     *            the communicationTypeId to set
     */
    public void setCommunicationTypeId(Long communicationTypeId) {
        this.communicationTypeId = communicationTypeId;
    }

    /**
     * @return the useValue
     */
    public String getUseValue() {
        return useValue;
    }

    /**
     * @param useValue
     *            the useValue to set
     */
    public void setUseValue(String useValue) {
        this.useValue = useValue;
    }

    /**
     * @return the displayValue
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * @param displayValue
     *            the displayValue to set
     */
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
