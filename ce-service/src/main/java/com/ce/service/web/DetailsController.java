/**
 * 
 */
package com.ce.service.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ce.service.utils.ItemHelper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.rupp.client.model.CommunicationOption;
import com.rupp.client.model.ItemDetails;

/**
 * @author sanya
 * 
 */
@Controller
public class DetailsController {

    @RequestMapping(value = "getItemDetails/{catKey}/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ItemDetails getItemsByLocation(@PathVariable String catKey, @PathVariable Long id) throws EntityNotFoundException {
        ItemDetails details = null;
        Entity category = ItemHelper.getCategory(catKey);
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        Entity item = dataStore.get(KeyFactory.createKey(category.getKey(), "item", id));

        Query detailQ = new Query("detail");
        detailQ.setAncestor(item.getKey());

        PreparedQuery detailpq = dataStore.prepare(detailQ);
        for(Entity detail : detailpq.asIterable()) {
            details = new ItemDetails();
            details.setFormattedText((String) detail.getProperty("formatedText"));
            details.setOpenHour((String) detail.getProperty("openHour"));
        }

        CommunicationOption main = new CommunicationOption();
        List<CommunicationOption> optional = new ArrayList<CommunicationOption>();
        Query comQ = new Query("communication");
        comQ.setAncestor(item.getKey());
        PreparedQuery compq = dataStore.prepare(comQ);
        for(Entity communication : compq.asIterable()) {
            if (Constants.MAIN.equals(communication.getProperty("type"))) {
                main.setDisplayText((String) communication.getProperty("value"));
                main.setType(Constants.MAIN);
            }
            else {
                CommunicationOption com = new CommunicationOption();
                com.setDisplayText((String) communication.getProperty("value"));
                com.setType((String) communication.getProperty("type"));
                optional.add(com);
            }
        }
        details.setCommunicationOption(optional);
        return details;
    }

}
