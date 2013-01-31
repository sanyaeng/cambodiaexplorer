package com.ce.service.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.rupp.client.model.Item;

/**
 * @author sanya
 * 
 */
public class ItemHelper {

    public static Key ITEM_KEY = null;

    /**
     * Function to create the entity from request
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @return {@link Entity}
     */
    public static Entity getItem(HttpServletRequest request) {
        // ITEM_KEY = KeyFactory.createKey(request.getParameter("category"), request.getParameter("displayName"));
        // System.out.println(">>>>> item key: " + ITEM_KEY);
        Entity item = new Entity("item");
        System.out.println(">>>> " + request.getParameter("displayName"));
        item.setProperty("displayName", request.getParameter("displayName"));
        item.setProperty("displayAddress", request.getParameter("displayAddress"));
        item.setProperty("mobile", request.getParameter("mobile"));
        item.setProperty("category", request.getParameter("category"));
        return item;
    }

    /**
     * Function to get the specific item
     * 
     * @param key
     *            {@link Key}
     * @return Item {@link Item}
     */
    public static Item getItem(Long id) {
        Item item = new Item();
        Query query = new Query("item");
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
        for(Entity result : pq.asIterable()) {
            if (result.getKey().getId() == id) {
                Key categoryKey = (Key) result.getProperty("category");
                item.setCategory(categoryKey);
                item.setDisplayAddress((String) result.getProperty("displayAddress"));
                item.setDisplayName((String) result.getProperty("displayName"));
                item.setId(id);
                return item;
            }
        }
        return null;
    }

    // private ItemKey itemKeyParser(Key key) {
    //
    // }

    /**
     * Function to get all the Item
     * 
     * @return {@link List}
     */
    public static List<Item> getAllItem(Entity category, String location) {
        List<Item> items = new ArrayList<Item>();
        Query query = new Query("item");
        if (category != null) {
            query.setAncestor(category.getKey());
        }
        if (null != location) {
            query.addFilter("location", Query.FilterOperator.EQUAL, location);
        }
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
        for(Entity result : pq.asIterable()) {
            Item item = new Item();
            System.out.println("Found: " + (String) result.getProperty("category"));
            // item.setCategory((Key) result.getProperty("category"));
            item.setDisplayAddress((String) result.getProperty("displayAddress"));
            item.setDisplayName((String) result.getProperty("displayName"));
            System.out.println(">>>>> " + result.getKey().getId());
            item.setId(result.getKey().getId());
            items.add(item);
        }
        return items;
    }

    /**
     * Count all item in the database
     * 
     * @return int
     */
    public static int countItem(Entity category, String location) {
        return getAllItem(category, location).size();
    }

    /**
     * Get the category entity by its catKey
     * 
     * @param catKey
     * @return
     */
    public static Entity getCategory(String catKey) {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        Query catQuery = new Query("category");
        catQuery.addFilter("catKey", Query.FilterOperator.EQUAL, catKey);
        PreparedQuery catpq = dataStore.prepare(catQuery);
        return catpq.asSingleEntity();
    }
}
