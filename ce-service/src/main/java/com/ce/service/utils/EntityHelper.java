/**
 * 
 */
package com.ce.service.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.rupp.client.model.Category;






/**
 * @author sanya
 *
 */
public class EntityHelper {
    /**
     * Get all categories in the datastore
     * @return List of Category
     */
    public static List<Category> getAllCategory() {
        List<Category> catList = new ArrayList<Category>();
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("category");

        PreparedQuery pq = dataStore.prepare(q);
        for(Entity result : pq.asIterable()) {
            Category category = new Category();
            category.setCatKey((String) result.getProperty("catKey"));
            category.setCatDesc((String) result.getProperty("catDesc"));
            catList.add(category);
        }
        
        return catList;
    }
}
