/**
 * 
 */
package com.ce.service.json;

import java.util.List;

import com.google.appengine.api.datastore.Key;

/**
 * @author sanya
 * 
 */
public class JBusinessDetails {
    private Long         id;

    private Long         businessId;

    private String       info;

    private List<String> paymentAccepted;

    private List<String> imageUrls;

    private List<String> openHours;

    private List<String> specialities;

    private List<String> services;

    public JBusinessDetails(Long id, Long businessId, String info, List<String> paymentAccepted, List<String> imageUrl,
            List<String> openHours, List<String> specialities, List<String> services) {
        this.id = id;
        this.businessId = businessId;
        this.info = info;
        this.paymentAccepted = paymentAccepted;
        this.imageUrls = imageUrl;
        this.openHours = openHours;
        this.specialities = specialities;
        this.services = services;
    }

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
