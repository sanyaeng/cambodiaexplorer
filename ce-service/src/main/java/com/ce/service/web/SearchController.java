/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ce.service.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ce.service.utils.ItemHelper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.rupp.client.model.CommunicationOption;
import com.rupp.client.model.Item;
import com.rupp.client.response.ItemResults;

/**
 * 
 * @author sanya
 */
@Controller
public class SearchController {

    static final Logger      LOGGER    = LoggerFactory.getLogger(SearchController.class);

    private DatastoreService dataStore = null;

    private DatastoreService getDataStore() {
        if (dataStore == null) {
            dataStore = DatastoreServiceFactory.getDatastoreService();
        }
        return this.dataStore;
    }

    /**
     * Get all item located in the given location
     * 
     * @param location
     *            Province as a string
     * @return
     * @throws EntityNotFoundException
     */
    @RequestMapping(value = "getItems/{location}", method = RequestMethod.GET)
    public @ResponseBody
    ItemResults getItemsByLocation(@PathVariable String location) throws EntityNotFoundException {
        List<Item> itemList = new ArrayList<Item>();
        // Query
        Query q = new Query("item");
        q.addFilter("location", Query.FilterOperator.EQUAL, location);

        PreparedQuery pq = getDataStore().prepare(q);
        ItemResults itemResult = new ItemResults();
        for(Entity result : pq.asIterable()) {
            Item item = new Item();
            item.setDisplayName((String) result.getProperty("displayName"));
            item.setDisplayAddress((String) result.getProperty("displayAddress"));
            item.setLat((String) result.getProperty("latitude"));
            item.setLon((String) result.getProperty("longitude"));
            item.setCategory((Key) result.getProperty("category"));
            item.setItemId((Long) result.getKey().getId());
            item.setCatKey((String) ((dataStore.get(result.getParent()).getProperty("catKey"))));
            item.setFormatedText((String) result.getProperty("formatedText"));
            item.setLocation((String) result.getProperty("location"));
            item.setItemKey(result.getKey());

            CommunicationOption main = new CommunicationOption();
            Query comQ = new Query("communication");
            comQ.setAncestor(result.getKey());
            PreparedQuery compq = dataStore.prepare(comQ);
            for(Entity communication : compq.asIterable()) {
                if (Constants.MAIN.equals(communication.getProperty("type"))) {
                    main.setDisplayText((String) communication.getProperty("value"));
                    main.setType(Constants.MAIN);
                }
            }
            item.setMainCommunication(main);
            itemList.add(item);
        }
        itemResult.setItemList(itemList);
        return itemResult;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound(NotFoundException e) {
        LOGGER.warn(e.getMessage());
    }

    /**
     * Search all items in the same category for one given location
     * 
     * @param location
     *            Province as a string
     * @param catkey
     *            category key
     * @param request
     *            {@link HttpServletRequest}
     * @return JSON String of the ItemResults
     * @throws EntityNotFoundException
     */
    @RequestMapping(value = "getItems/{location}/{catkey}")
    public @ResponseBody
    ItemResults getResultFromCatKey(@PathVariable String location, @PathVariable String catkey, HttpServletRequest request)
            throws EntityNotFoundException {
        List<Item> itemList = new ArrayList<Item>();
        ItemResults itemResult = new ItemResults();
        Entity category = ItemHelper.getCategory(catkey);

        Query q = new Query("item");
        q.setAncestor(category.getKey());
        q.addFilter("location", Query.FilterOperator.EQUAL, location);
        PreparedQuery pq = getDataStore().prepare(q);
        for(Entity resultItem : pq.asIterable()) {
            Item item = new Item();
            item.setDisplayName((String) resultItem.getProperty("displayName"));
            item.setDisplayAddress((String) resultItem.getProperty("displayAddress"));
            item.setLat((String) resultItem.getProperty("latitude"));
            item.setLon((String) resultItem.getProperty("longitude"));
            item.setCategory((Key) resultItem.getProperty("category"));
            item.setItemId((Long) resultItem.getKey().getId());
            item.setCatKey((String) ((dataStore.get(resultItem.getParent()).getProperty("catKey"))));
            item.setFormatedText((String) resultItem.getProperty("formatedText"));
            item.setItemKey(resultItem.getKey());

            CommunicationOption main = new CommunicationOption();
            Query comQ = new Query("communication");
            comQ.setAncestor(resultItem.getKey());
            PreparedQuery compq = dataStore.prepare(comQ);
            for(Entity communication : compq.asIterable()) {
                if (Constants.MAIN.equals(communication.getProperty("type"))) {
                    main.setDisplayText((String) communication.getProperty("value"));
                    main.setType(Constants.MAIN);
                }
            }
            item.setMainCommunication(main);
            itemList.add(item);
        }
        itemResult.setItemList(itemList);
        LOGGER.info("Passed");
        return itemResult;
    }

    private List<Entity> getLimitEntity(int limit, Entity category, String location) {
        Query q = new Query("item");
        q.setAncestor(category.getKey());
        q.addFilter("location", Query.FilterOperator.EQUAL, location);
        PreparedQuery pq = getDataStore().prepare(q);
        return pq.asList(FetchOptions.Builder.withLimit(limit));

    }

    @RequestMapping(value = "getItems/{location}/{catkey}/{page}")
    public @ResponseBody
    ItemResults getResultFromCatKey(@PathVariable String location, @PathVariable String catkey, @PathVariable int page,
            HttpServletRequest request) throws EntityNotFoundException {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        List<List<Item>> itemList = new ArrayList<List<Item>>();
        ItemResults itemResult = new ItemResults();
        Entity category = ItemHelper.getCategory(catkey);
        List<Entity> items = this.getLimitEntity(page * Constants.ITEM_PER_PAGE, category, location);

        List<Item> listItem = null;
        for(int i = 0; i < items.size(); i++) {
            if (listItem == null) {
                listItem = new ArrayList<Item>();
            }
            Item item = new Item();
            Entity entity = items.get(i);
            item.setDisplayName((String) entity.getProperty("displayName"));
            item.setDisplayAddress((String) entity.getProperty("displayAddress"));
            item.setLat((String) entity.getProperty("latitude"));
            item.setLon((String) entity.getProperty("longitude"));
            item.setCategory((Key) entity.getProperty("category"));
            item.setItemId((Long) entity.getKey().getId());
            item.setCatKey((String) ((dataStore.get(entity.getParent()).getProperty("catKey"))));
            item.setFormatedText((String) entity.getProperty("formatedText"));
            item.setItemKey(entity.getKey());

            CommunicationOption main = new CommunicationOption();
            Query comQ = new Query("communication");
            comQ.setAncestor(entity.getKey());
            PreparedQuery compq = dataStore.prepare(comQ);
            for(Entity communication : compq.asIterable()) {
                if (Constants.MAIN.equals(communication.getProperty("type"))) {
                    main.setDisplayText((String) communication.getProperty("value"));
                    main.setType(Constants.MAIN);
                }
            }
            item.setMainCommunication(main);
            listItem.add(item);
            if ((i + 1) % Constants.ITEM_PER_PAGE == 0) {
                itemList.add(listItem);
                listItem = null;
            }
        }
        if (itemList.size() < page) {
            itemResult.setItemList(new ArrayList<Item>());
        }
        else {
            itemResult.setItemList(itemList.get(page - 1));
        }

        itemResult.setTotalItem(ItemHelper.countItem(category, location));
        LOGGER.info("Passed");
        return itemResult;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editItem(@PathVariable Long id, HttpServletRequest request) {
        Item item = ItemHelper.getItem(id);
        request.setAttribute("item", item);
        return "edit";
    }
}
