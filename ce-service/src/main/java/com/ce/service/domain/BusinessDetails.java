/**
 * 
 */
package com.ce.service.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;

import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 * @author sanya
 * 
 */
@Entity
public class BusinessDetails extends AbstractLongEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3643248305422520677L;

    @Basic
    private Long              businessId;

    @Basic
    private String            info;

    @Basic
    private List<String>      paymentAccepted;

    @Basic
    private List<String>      imageUrls;

    @Basic
    private List<String>      openHours;

    @Basic
    private List<String>      specialities;

    @Basic
    private List<String>      services;

    /**
     * @return the openHours
     */
    public List<String> getOpenHours() {
        return openHours;
    }

    /**
     * @param openHours
     *            the openHours to set
     */
    public void setOpenHours(List<String> openHours) {
        this.openHours = openHours;
    }

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
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the paymentAccepted
     */
    public List<String> getPaymentAccepted() {
        return paymentAccepted;
    }

    /**
     * @param paymentAccepted
     *            the paymentAccepted to set
     */
    public void setPaymentAccepted(List<String> paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    /**
     * @return the imageUrls
     */
    public List<String> getImageUrls() {
        return imageUrls;
    }

    /**
     * @param imageUrls
     *            the imageUrls to set
     */
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    /**
     * @return the specialities
     */
    public List<String> getSpecialities() {
        return specialities;
    }

    /**
     * @param specialities
     *            the specialities to set
     */
    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
    }

    /**
     * @return the services
     */
    public List<String> getServices() {
        return services;
    }

    /**
     * @param services
     *            the services to set
     */
    public void setServices(List<String> services) {
        this.services = services;
    }

}
