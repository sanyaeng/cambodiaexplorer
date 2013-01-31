/**
 * 
 */
package com.ce.service.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ce.service.dao.BusinessCategoryDao;
import com.ce.service.domain.BusinessCategory;
import com.ce.service.json.JBussinessCategory;

/**
 * @author sanyaeng
 * 
 */
public class CambodiaExplorerService {

    @Autowired
    private BusinessCategoryDao businessCategoryDao;

    /**
     * Save the new category
     * 
     * @param businessCategory
     * @return the json business category object
     */
    public JBussinessCategory saveNewCategory(BusinessCategory businessCategory) {
        businessCategoryDao.persist(businessCategory);

        return this.convert(businessCategory);
    }

    /**
     * Get all business category
     * 
     * @return the list of json business category object
     */
    public List<JBussinessCategory> getAllBusinessCategory() {
        Iterable<BusinessCategory> categories = businessCategoryDao.queryAll();
        List<JBussinessCategory> jCategories = new ArrayList<JBussinessCategory>();
        Iterator<BusinessCategory> i = categories.iterator();
        while (i.hasNext()) {
            BusinessCategory cat = i.next();
            jCategories.add(convert(cat));
        }
        return jCategories;
    }

    /**
     * convert from the Business category object to the json object
     * 
     * @return
     */
    public JBussinessCategory convert(BusinessCategory businessCat) {
        JBussinessCategory jCategory = new JBussinessCategory();
        jCategory.setId(businessCat.getId());
        jCategory.setCatKey(businessCat.getCatKey());
        jCategory.setCatDesc(businessCat.getCatDesc());

        return jCategory;
    }

}
