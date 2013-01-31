/**
 * 
 */
package com.ce.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ce.service.dao.BusinessCategoryDao;
import com.ce.service.dao.BusinessDao;
import com.ce.service.domain.BusinessCategory;

/**
 * @author sanya
 * 
 */
public class CambodiaExplorerController {

    protected String              PAGE_STYLE = "pagestyle";

    @Autowired
    protected BusinessDao         businessDao;
    @Autowired
    protected BusinessCategoryDao businessCategoryDao;

    protected String getPageStyle() {
        return "home";
    }

    @ModelAttribute(value = "businesstype")
    public Iterable<BusinessCategory> getBusinessCategory() {
        Iterable<BusinessCategory> categories = businessCategoryDao.queryAll();
        // models.addAttribute("businesstype", categories);
        return categories;
    }
}
