/***/
package com.ce.service.model;

import com.ce.service.web.Constants;

/**
 * @author sophea <a href='mailto:sm@goldengekko.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2011
 */
public class ItemDetails extends ListItem {

    private String author;
    private double price;

    public ItemDetails(String id, String title, String author, double price) {
        super(id, title);
        this.author = author;
        this.price = price;

    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return Constants.PATH_IMAGE + getId() + ".jpg";
    }

    public ListItem generateListItem() {
        return new ListItem(super.getId(), super.getTitle());
    }
}
