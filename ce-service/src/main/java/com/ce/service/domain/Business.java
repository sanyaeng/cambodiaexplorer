/**
 * 
 */
package com.ce.service.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;

import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 * @author sanyaeng
 * 
 */
@Entity
public class Business extends AbstractLongEntity {// extends AEDLongEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3216653116956378508L;

    @Basic
    private String            username;

    @Basic
    private Long              businessCategoryId;

    @Basic
    private String            longitute;

    @Basic
    private String            lattitue;

    @Basic
    private String            businessName;

    @Basic
    private String            businessDescription;

    @Basic
    private String            businessLongAddress;

    @Basic
    private String            businessShortAddress;

    // list of string, 1: website, 2: email, 3: main phone
    @Basic
    private List<String>      comoption;

    // Business logo
    @Basic
    private String            logoUrl;

    /**
     * @return the logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * @param logoUrl
     *            the logoUrl to set
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * @return the comoption
     */
    public List<String> getComoption() {
        return comoption;
    }

    /**
     * @param comoption
     *            the comoption to set
     */
    public void setComoption(List<String> comoption) {
        this.comoption = comoption;
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
     * @return the businessCategoryId
     */
    public Long getBusinessCategoryId() {
        return businessCategoryId;
    }

    /**
     * @param businessCategoryId
     *            the businessCategoryId to set
     */
    public void setBusinessCategoryId(Long businessCategoryId) {
        this.businessCategoryId = businessCategoryId;
    }

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName
     *            the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the businessDescription
     */
    public String getBusinessDescription() {
        return businessDescription;
    }

    /**
     * @param businessDescription
     *            the businessDescription to set
     */
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    /**
     * @return the businessLongAddress
     */
    public String getBusinessLongAddress() {
        return businessLongAddress;
    }

    /**
     * @param businessLongAddress
     *            the businessLongAddress to set
     */
    public void setBusinessLongAddress(String businessLongAddress) {
        this.businessLongAddress = businessLongAddress;
    }

    /**
     * @return the businessShortAddress
     */
    public String getBusinessShortAddress() {
        return businessShortAddress;
    }

    /**
     * @param businessShortAddress
     *            the businessShortAddress to set
     */
    public void setBusinessShortAddress(String businessShortAddress) {
        this.businessShortAddress = businessShortAddress;
    }

    /**
     * @return the longitute
     */
    public String getLongitute() {
        return longitute;
    }

    /**
     * @param longitute
     *            the longitute to set
     */
    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    /**
     * @return the lattitue
     */
    public String getLattitue() {
        return lattitue;
    }

    /**
     * @param lattitue
     *            the lattitue to set
     */
    public void setLattitue(String lattitue) {
        this.lattitue = lattitue;
    }

}
