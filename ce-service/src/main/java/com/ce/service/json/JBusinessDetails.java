/**
 * 
 */
package com.ce.service.json;

import java.util.List;

/**
 * @author sanya
 * 
 */
public class JBusinessDetails {
    private Long         id;
    private List<String> info;
    private String       paymentAccepted;
    private List<String> imageUrl;

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
     * @return the info
     */
    public List<String> getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(List<String> info) {
        this.info = info;
    }

    /**
     * @return the paymentAccepted
     */
    public String getPaymentAccepted() {
        return paymentAccepted;
    }

    /**
     * @param paymentAccepted
     *            the paymentAccepted to set
     */
    public void setPaymentAccepted(String paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    /**
     * @return the imageUrl
     */
    public List<String> getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     *            the imageUrl to set
     */
    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

}
