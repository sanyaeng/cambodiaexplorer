/**
 * To avoid the blocking on the mobile application, i create this class to add the static data.
 * This class is used to add the static value into database.
 */
package com.ce.service.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author sanya
 * 
 */
@Controller
public class DatabaseController {

    @RequestMapping(value = "addProvinces", method = RequestMethod.GET)
    public String addProvinces(HttpServletRequest request, HttpServletResponse response) {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();

        Entity phnompenh = new Entity("provinces");
        phnompenh.setProperty("long_name", "Phnom Penh");
        phnompenh.setProperty("short_name", "phnompenh");

        Entity siemreap = new Entity("provinces");
        siemreap.setProperty("long_name", "Siem Reap");
        siemreap.setProperty("short_name", "siemreap");

        Entity kampongchhnang = new Entity("provinces");
        kampongchhnang.setProperty("long_name", "Kampong Chhnang");
        kampongchhnang.setProperty("short_name", "kampongchhnang");

        Entity kampongspeu = new Entity("provinces");
        kampongspeu.setProperty("long_name", "Kampong Chhnang");
        kampongspeu.setProperty("short_name", "kampongspeu");

        Entity kampongthom = new Entity("provinces");
        kampongthom.setProperty("long_name", "Kampong Thom");
        kampongthom.setProperty("short_name", "kampongthom");

        Entity kampot = new Entity("provinces");
        kampot.setProperty("long_name", "Kampot");
        kampot.setProperty("short_name", "kampot");

        Entity kandal = new Entity("provinces");
        kandal.setProperty("long_name", "Kandal");
        kandal.setProperty("short_name", "kandal");

        Entity kohkong = new Entity("provinces");
        kohkong.setProperty("long_name", "Koh Kong");
        kohkong.setProperty("short_name", "kohkong");

        Entity kep = new Entity("provinces");
        kep.setProperty("long_name", "kep");
        kep.setProperty("short_name", "kep");

        Entity kratie = new Entity("provinces");
        kratie.setProperty("long_name", "Kratie");
        kratie.setProperty("short_name", "kratie");

        Entity mondulkiri = new Entity("provinces");
        mondulkiri.setProperty("long_name", "Mondulkiri");
        mondulkiri.setProperty("short_name", "mondulkiri");

        Entity ratanakkiri = new Entity("provinces");
        ratanakkiri.setProperty("long_name", "Ratanakkiri");
        ratanakkiri.setProperty("short_name", "ratanakkiri");

        Entity pailin = new Entity("provinces");
        pailin.setProperty("long_name", "Pailin");
        pailin.setProperty("short_name", "pailin");

        Entity sihanoukville = new Entity("provinces");
        sihanoukville.setProperty("long_name", "Sihanouk ville");
        sihanoukville.setProperty("short_name", "sihanoukville");

        Entity preahvihear = new Entity("provinces");
        preahvihear.setProperty("long_name", "Preah Vihear");
        preahvihear.setProperty("short_name", "preahvihear");

        Entity pursat = new Entity("provinces");
        pursat.setProperty("long_name", "Pursat");
        pursat.setProperty("short_name", "pursat");

        Entity preyveng = new Entity("provinces");
        preyveng.setProperty("long_name", "Prey Veng");
        preyveng.setProperty("short_name", "preyveng");

        Entity steungtreng = new Entity("provinces");
        steungtreng.setProperty("long_name", "Steung Treng");
        steungtreng.setProperty("short_name", "steungtreng");

        Entity svayrieng = new Entity("provinces");
        svayrieng.setProperty("long_name", "Svay Rieng");
        svayrieng.setProperty("short_name", "svayrieng");

        Entity takeo = new Entity("provinces");
        takeo.setProperty("long_name", "Takeo");
        takeo.setProperty("short_name", "takeo");

        List<Entity> items = Arrays.asList(takeo, svayrieng, steungtreng, preyveng, pursat, preahvihear, phnompenh, siemreap,
                kampongchhnang, kampongspeu, kampongthom, kampot, kandal, kohkong, kep, kratie, mondulkiri, ratanakkiri, pailin,
                sihanoukville);
        dataStore.put(items);

        return "addprovinces";
    }

    /**
     * This function will create and add the sample data into the datastore
     */
    @RequestMapping(value = "sampleData", method = RequestMethod.GET)
    public String addStaticData(HttpServletRequest request, HttpServletResponse response) {
        DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
        Key restauKey = null;
        // Get the restaurant category to put in the item
        Query q = new Query("category");
        // PreparedQuery contains the methods for fetching query results
        // from the datastore
        PreparedQuery pq = dataStore.prepare(q);

        for(Entity result : pq.asIterable()) {
            String catKey = (String) result.getProperty("catKey");
            if (Constants.NIGHTCLUBS.equals(catKey)) {
                addNightClub(result.getKey(), dataStore);
            }
            else if (Constants.TAXI.equals(catKey)) {
                this.addTaxi(result.getKey(), dataStore);
            }
            else if (Constants.RESTAU.equals(catKey)) {
                this.addRestaurant(result.getKey(), dataStore);
            }
        }
        return "sampledata";
    }

    /**
     * 
     */
    private void addNightClub(Key nightClubKey, DatastoreService dataStore) {
        // Add Item1
        Entity item1 = new Entity("item", nightClubKey);
        item1.setProperty("displayName", "Olympic Luxury Club");
        item1.setProperty("displayAddress", "No. 26, St. 199, Sangkat Toul Svay Prey I, Khan Chamkar Morn, 12308, Phnom Penh");
        item1.setProperty("location", "phnompenh");
        item1.setProperty("latitude", "11.552579");
        item1.setProperty("longitude", "104.908340");
        item1.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item1.setProperty("item_id", getLastId());
        // dataStore.put(item1);
        // Add Item2
        Entity item2 = new Entity("item", nightClubKey);
        item2.setProperty("displayName", "City Light Night Club");
        item2.setProperty("displayAddress", "No. 102E3, St. 199, 12308, Phnom Penh");
        item2.setProperty("location", "phnompenh");
        item2.setProperty("latitude", "11.550834");
        item2.setProperty("longitude", "104.906988");
        item2.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item2.setProperty("item_id", getLastId());
        // dataStore.put(item2);
        // Add Item3
        Entity item3 = new Entity("item", nightClubKey);
        item3.setProperty("displayName", "Golden Town Night Club");
        item3.setProperty("displayAddress", "No. 412, Preah Monivong (St. 93), 12302, Phnom Penh");
        item3.setProperty("location", "phnompenh");
        item3.setProperty("latitude", "11.536706");
        item3.setProperty("longitude", "104.923553");
        item3.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item3.setProperty("item_id", getLastId());
        // dataStore.put(item3);
        // Add Item4
        Entity item4 = new Entity("item", nightClubKey);
        item4.setProperty("displayName", "DJ Club");
        item4.setProperty("displayAddress", "No. 75, St. 174, corner of Trasak Paem (St. 63), 12210, Phnom Penh");
        item4.setProperty("location", "phnompenh");
        item4.setProperty("latitude", "11.564814");
        item4.setProperty("longitude", "104.922180");
        item4.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item4.setProperty("item_id", getLastId());
        // dataStore.put(item4);

        List<Entity> items = Arrays.asList(item1, item2, item3, item4);
        dataStore.put(items);

        for(Entity item : items) {
            // Add Details of the item1
            Entity detailsItem = new Entity("detail", item.getKey());
            detailsItem.setProperty("formatedText", "<i>" + item.getProperty("displayName")
                    + ", We provide the great services that you ever wanted.<i>");
            detailsItem.setProperty("openHour", "Every Day: 7:00 - 23:59");
            // detailsItem.setProperty("item_id", item.getProperty("item_id"));

            // Add Communication option
            Entity mainCommunication = new Entity("communication", item.getKey());
            mainCommunication.setProperty("type", "phone");
            mainCommunication.setProperty("value", "12 234 567");
            // mainCommunication.setProperty("item_id", item.getProperty("item_id"));

            Entity communication1 = new Entity("communication", item.getKey());
            communication1.setProperty("type", "web");
            communication1.setProperty("value", "http://www.sampletaxi.com");
            // communication1.setProperty("item_id", item.getProperty("item_id"));

            Entity communication2 = new Entity("communication", item.getKey());
            communication2.setProperty("type", "mail");
            communication2.setProperty("value", "sample@sampletaxi.com");
            // communication2.setProperty("item_id", item.getProperty("item_id"));

            List<Entity> entities = Arrays.asList(detailsItem, mainCommunication, communication1, communication2);
            dataStore.put(entities);
        }
    }

    /**
     * Add the static taxi, it is just the sample data
     * 
     * @param restauKey
     * @param dataStore
     */
    private void addTaxi(Key taxiKey, DatastoreService dataStore) {
        // Add Item1
        Entity item1 = new Entity("item", taxiKey);
        item1.setProperty("displayName", "Lay Ty Taxi");
        item1.setProperty("displayAddress", "No. 62E02, St. 156, 12153, Phnom Penh");
        item1.setProperty("location", "phnompenh");
        item1.setProperty("latitude", "11.563679");
        item1.setProperty("longitude", "104.898856");
        item1.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item1.setProperty("item_id", getLastId());
        // dataStore.put(item1);
        // Add Item2
        Entity item2 = new Entity("item", taxiKey);
        item2.setProperty("displayName", "Taxi Asia");
        item2.setProperty("displayAddress", "No. 102E3, St. 199, 12308, Phnom Penh");
        item2.setProperty("location", "phnompenh");
        item2.setProperty("latitude", "11.550855");
        item2.setProperty("longitude", "104.906924");
        item2.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item2.setProperty("item_id", getLastId());
        // dataStore.put(item2);
        // Add Item3
        Entity item3 = new Entity("item", taxiKey);
        item3.setProperty("displayName", "Taxi Cambo");
        item3.setProperty("displayAddress", "No. 117, Yothapol Khemarak Phoumin (St. 271), Phnom Penh");
        item3.setProperty("latitude", "11.552768");
        item3.setProperty("longitude", "104.890015");
        item3.setProperty("location", "phnompenh");
        item3.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item3.setProperty("item_id", getLastId());
        // dataStore.put(item3);
        // Add Item4
        Entity item4 = new Entity("item", taxiKey);
        item4.setProperty("displayName", "Taxi Airport");
        item4.setProperty("displayAddress", "No. FA6, St. 265, 12158, Phnom Penh ");
        item4.setProperty("latitude", "11.558928");
        item4.setProperty("longitude", "104.928682");
        item4.setProperty("location", "phnompenh");
        item4.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item4.setProperty("item_id", getLastId());
        // dataStore.put(item4);

        List<Entity> items = Arrays.asList(item1, item2, item3, item4);
        dataStore.put(items);

        for(Entity item : items) {
            // Add Details of the item1
            Entity detailsItem = new Entity("detail", item.getKey());
            detailsItem.setProperty("formatedText", "<i>" + item.getProperty("displayName")
                    + ", We provide the great services that you ever wanted.<i>");
            detailsItem.setProperty("openHour", "Every Day: 7:00 - 23:59");
            // detailsItem.setProperty("item_id", item.getProperty("item_id"));

            // Add Communication option
            Entity mainCommunication = new Entity("communication", item.getKey());
            mainCommunication.setProperty("type", "phone");
            mainCommunication.setProperty("value", "12 234 567");
            // mainCommunication.setProperty("item_id", item.getProperty("item_id"));

            Entity communication1 = new Entity("communication", item.getKey());
            communication1.setProperty("type", "web");
            communication1.setProperty("value", "http://www.sampletaxi.com");
            // communication1.setProperty("item_id", item.getProperty("item_id"));

            Entity communication2 = new Entity("communication", item.getKey());
            communication2.setProperty("type", "mail");
            communication2.setProperty("value", "sample@sampletaxi.com");
            // communication2.setProperty("item_id", item.getProperty("item_id"));

            List<Entity> entities = Arrays.asList(detailsItem, mainCommunication, communication1, communication2);
            dataStore.put(entities);
        }
    }

    /**
     * Add the static data of the restaurant category
     * 
     * @param restauKey
     * @param dataStore
     */
    private void addRestaurant(Key restauKey, DatastoreService dataStore) {
        // Add Item1
        Entity item1 = new Entity("item", restauKey);
        item1.setProperty("displayName", "Nina Restaurant");
        item1.setProperty("displayAddress", "No. 160, Norodom Blv");
        item1.setProperty("location", "phnompenh");
        item1.setProperty("latitude", "11.567226");
        item1.setProperty("longitude", "104.902697");
        item1.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item1.setProperty("item_id", getLastId());
        // dataStore.put(item1);
        // Add Item2
        Entity item2 = new Entity("item", restauKey);
        item2.setProperty("displayName", "Kob� Restaurant");
        item2.setProperty("displayAddress", "No. 160, Norodom Blv");
        item2.setProperty("location", "phnompenh");
        item2.setProperty("latitude", "11.560667");
        item2.setProperty("longitude", "104.927073");
        item2.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item2.setProperty("item_id", getLastId());
        // dataStore.put(item2);
        // Add Item3
        Entity item3 = new Entity("item", restauKey);
        item3.setProperty("displayName", "Chow Restaurant");
        item3.setProperty("displayAddress", "No. 277, Sisowath, 12202, Phnom Penh");
        item3.setProperty("location", "phnompenh");
        item3.setProperty("latitude", "11.568388");
        item3.setProperty("longitude", "104.930570");
        item3.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item3.setProperty("item_id", getLastId());
        // dataStore.put(item3);
        // Add Item4
        Entity item4 = new Entity("item", restauKey);
        item4.setProperty("displayName", "Eden Restaurant");
        item4.setProperty("displayAddress", "No. 53, St. 334, 12302, Phnom Penh");
        item4.setProperty("location", "phnompenh");
        item4.setProperty("latitude", "11.549741");
        item4.setProperty("longitude", "104.922566");
        item4.setProperty("formatedText", "<i>, We provide the great services that you ever wanted.<i>");
        // item4.setProperty("item_id", getLastId());
        // dataStore.put(item4);

        List<Entity> items = Arrays.asList(item1, item2, item3, item4);
        dataStore.put(items);

        for(Entity item : items) {
            // Add Details of the item1
            Entity detailsItem = new Entity("detail", item.getKey());
            detailsItem.setProperty("formatedText", "<i>" + item.getProperty("displayName")
                    + ", We provide the great services that you ever wanted.<i>");
            detailsItem.setProperty("openHour", "Every Day: 7:00 - 23:59");
            // detailsItem.setProperty("item_id", item.getProperty("item_id"));

            // Add Communication option
            Entity mainCommunication = new Entity("communication", item.getKey());
            mainCommunication.setProperty("type", "phone");
            mainCommunication.setProperty("value", "12 234 567");
            // mainCommunication.setProperty("item_id", item.getProperty("item_id"));

            Entity communication1 = new Entity("communication", item.getKey());
            communication1.setProperty("type", "web");
            communication1.setProperty("value", "http://www.samplerestaurant.com");
            // communication1.setProperty("item_id", item.getProperty("item_id"));

            Entity communication2 = new Entity("communication", item.getKey());
            communication2.setProperty("type", "mail");
            communication2.setProperty("value", "restaurant@samplerestaurant.com");
            // communication2.setProperty("item_id", item.getProperty("item_id"));

            List<Entity> entities = Arrays.asList(detailsItem, mainCommunication, communication1, communication2);
            dataStore.put(entities);
        }
    }
}
