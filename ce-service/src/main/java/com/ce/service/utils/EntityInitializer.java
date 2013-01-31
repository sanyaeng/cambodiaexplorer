package com.ce.service.utils;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * @author sanya
 * 
 */
public class EntityInitializer {

    private DatastoreService                       dataStore = null;
    private Map<String, List<Map<String, String>>> entities  = null;
    private static Logger                          logging   = Logger.getLogger(EntityInitializer.class.getName());

    /**
     * Initialize the all entities
     */
    public void init() {
        this.dataStore = DatastoreServiceFactory.getDatastoreService();
        if (EntityHelper.getAllCategory().isEmpty()) {
            logging.info(">>>>>>>>> Start to add new entity");
            // Restaurant
            Entity entity = new Entity("BusinessCategory");
            entity.setProperty("catKey", "restauce");
            entity.setProperty("catDesc", "Restaurant");
            this.dataStore.put(entity);
            // Taxi
            entity = new Entity("BusinessCategory");
            entity.setProperty("catKey", "taxice");
            entity.setProperty("catDesc", "Taxi");
            this.dataStore.put(entity);

            // night club
            entity = new Entity("BusinessCategory");
            entity.setProperty("catKey", "nightclubce");
            entity.setProperty("catDesc", "Night Club");
            this.dataStore.put(entity);
            logging.info(">>>>>>>>> Add new entity copleted");
        }
    }

    /**
     * @return the entities
     */
    public Map<String, List<Map<String, String>>> getEntities() {
        return entities;
    }

    /**
     * @param entities
     *            the entities to set
     */
    public void setEntities(Map<String, List<Map<String, String>>> entities) {
        this.entities = entities;
    }

    /**
     * Read the entity from the spring context and create in the datastore
     */
    private void createEntityAndDefaultValue() {
        // if (EntityHelper.getAllCategory().isEmpty()) {

        // }
    }
}
