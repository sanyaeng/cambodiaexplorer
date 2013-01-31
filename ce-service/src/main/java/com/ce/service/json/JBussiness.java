/**
 * 
 */
package com.ce.service.json;

import java.util.List;

import com.ce.service.model.OpenHours;

/**
 * @author sanya
 * 
 */
public class JBussiness {
    private Long                       businessId;
    private String                     businessName;
    private String                     businessAddress;
    private String                     businessLat;
    private String                     businessLon;
    private String                     businessShortDescription;
    private String                     businessLongDescription;
    private JBussinessCategory         category;
    private List<JCommunicationOption> comOption;
    private List<OpenHours>            openHours;

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
     * @return the openHours
     */
    public List<OpenHours> getOpenHours() {
        return openHours;
    }

    /**
     * @param openHours
     *            the openHours to set
     */
    public void setOpenHours(List<OpenHours> openHours) {
        this.openHours = openHours;
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
     * @return the businessAddress
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * @param businessAddress
     *            the businessAddress to set
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * @return the businessLat
     */
    public String getBusinessLat() {
        return businessLat;
    }

    /**
     * @param businessLat
     *            the businessLat to set
     */
    public void setBusinessLat(String businessLat) {
        this.businessLat = businessLat;
    }

    /**
     * @return the businessLon
     */
    public String getBusinessLon() {
        return businessLon;
    }

    /**
     * @param businessLon
     *            the businessLon to set
     */
    public void setBusinessLon(String businessLon) {
        this.businessLon = businessLon;
    }

    /**
     * @return the businessShortDescription
     */
    public String getBusinessShortDescription() {
        return businessShortDescription;
    }

    /**
     * @param businessShortDescription
     *            the businessShortDescription to set
     */
    public void setBusinessShortDescription(String businessShortDescription) {
        this.businessShortDescription = businessShortDescription;
    }

    /**
     * @return the businessLongDescription
     */
    public String getBusinessLongDescription() {
        return businessLongDescription;
    }

    /**
     * @param businessLongDescription
     *            the businessLongDescription to set
     */
    public void setBusinessLongDescription(String businessLongDescription) {
        this.businessLongDescription = businessLongDescription;
    }

    public JBussinessCategory getCategory() {
        return category;
    }

    public void setCategory(JBussinessCategory category) {
        this.category = category;
    }

    public List<JCommunicationOption> getComOption() {
        return comOption;
    }

    public void setComOption(List<JCommunicationOption> comOption) {
        this.comOption = comOption;
    }

}
