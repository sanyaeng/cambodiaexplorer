package com.ce.service.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ce.service.utils.ItemHelper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends CambodiaExplorerController {

    private static final Logger logger    = LoggerFactory.getLogger(HomeController.class);

    private DatastoreService    dataStore = null;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model models, Principal principl) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>> Welcome home! <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        // models.addAttribute("categories", EntityHelper.getAllCategory());
        // models.addAttribute(PAGE_STYLE, "home");

        setDataToView(request);
        return "home";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addItem(HttpServletRequest request, HttpServletResponse response) {
        dataStore = DatastoreServiceFactory.getDatastoreService();
        logger.info("Adding item...");
        dataStore.put(ItemHelper.getItem(request));
        // When we save, we will get an item key
        setDataToView(request);
        return "home";
    }

    /**
     * Set data back to view
     * 
     * @param request
     */
    private void setDataToView(HttpServletRequest request) {
        // request.setAttribute("categories", EntityHelper.getAllCategory());
        request.setAttribute("items", ItemHelper.getAllItem(null, null));
    }
}
