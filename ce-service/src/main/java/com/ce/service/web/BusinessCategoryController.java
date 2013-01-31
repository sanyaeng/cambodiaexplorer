package com.ce.service.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ce.service.domain.BusinessCategory;
import com.ce.service.json.JBussinessCategory;
import com.ce.service.utils.CambodiaExplorerService;

/**
 * Concrete Controller related to entity BusinessCategory.
 */
@Controller
@RequestMapping(value = "/BusinessCategory")
public class BusinessCategoryController {

    static final Logger             LOG = LoggerFactory.getLogger(BusinessCategoryController.class);

    @Autowired
    private CambodiaExplorerService cambodiaExplorerService;

    /**
     * Create new business category
     * 
     * @param catKey
     * @param catDesc
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<JBussinessCategory> addNewBusinessCategory(@ModelAttribute BusinessCategory businessCategory) {

        LOG.debug(">>>>>>>> Start creating new Category");
        JBussinessCategory jCategory = cambodiaExplorerService.saveNewCategory(businessCategory);
        return new ResponseEntity<JBussinessCategory>(jCategory, HttpStatus.OK);
    }

    /**
     * Function to retrieve all the available business categories
     * 
     * @return
     */
    @RequestMapping(value = "getAllBusinessesCategries", method = RequestMethod.POST)
    public ResponseEntity<List<JBussinessCategory>> getAllBusinessCategories() {
        // List<BusinessCategory> categories = businessCategoryDao.findAll();
        // List<JBussinessCategory> jCategories = new ArrayList<JBussinessCategory>();
        // for(BusinessCategory cat : categories) {
        // JBussinessCategory category = new JBussinessCategory();
        // category.setCatKey(cat.getCatKey());
        // category.setCatDesc(cat.getCatDesc());
        // jCategories.add(category);
        // }
        List<JBussinessCategory> categories = cambodiaExplorerService.getAllBusinessCategory();
        return new ResponseEntity<List<JBussinessCategory>>(categories, HttpStatus.OK);
    }

}
