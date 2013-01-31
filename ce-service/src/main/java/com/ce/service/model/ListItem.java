/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ce.service.model;

import com.ce.service.web.Constants;

/**
 * 
 * @author os
 */
public class ListItem {
    private String id;
    private String title;

    public String getLink() {
        return Constants.PATH_ITEMS + id;
    }

    public ListItem() {
    }

    public ListItem(String id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
