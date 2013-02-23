package com.ce.service.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ce.service.dao.BusinessCategoryDao;
import com.ce.service.dao.BusinessDao;
import com.ce.service.dao.BusinessDetailsDao;
import com.ce.service.domain.Business;
import com.ce.service.domain.BusinessCategory;
import com.ce.service.domain.BusinessDetails;
import com.ce.service.json.JBusinessDetails;
import com.ce.service.json.JBussiness;
import com.ce.service.json.JBussinessCategory;
import com.ce.service.model.OpenHours;
import com.ce.service.utils.CambodiaExplorerService;

/**
 * Concrete Controller related to entity Business.
 */
@Controller
@RequestMapping(value = "/business")
public class BusinessController {// extends GeneratedBusinessController {

    static final Logger     LOG = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    BusinessDao             businessDao;

    @Autowired
    BusinessCategoryDao     busCatDao;

    @Autowired
    BusinessDetailsDao      businessDetailDao;

    @Autowired
    CambodiaExplorerService cambodiaExplorerService;

    @RequestMapping(value = "api/v10/addNewBusiness", method = RequestMethod.POST)
    public ResponseEntity<JBussiness> addNewBusiness(HttpServletRequest request, HttpServletResponse response,
            Principal principl, @ModelAttribute(value = "business") Business business,
            @RequestParam(value = "openTime[]", required = false) String[] openTime,
            @RequestParam(value = "commOption[]", required = false) String[] commOption) {

        business.setUsername(principl.getName());
        if (null != openTime) {
            // business.setOpenHours(Arrays.asList(openTime));
        }
        if (null != commOption) {
            business.setComoption(Arrays.asList(commOption));
        }
        businessDao.persist(business);
        return new ResponseEntity<JBussiness>(convert(business), HttpStatus.OK);

    }

    @RequestMapping(value = "api/v10/getAllUserBusiness", method = RequestMethod.GET)
    public ResponseEntity<List<JBussiness>> getAllBusinesses(HttpServletRequest req, HttpServletResponse res, Principal principal) {

        Iterable<Business> businesses = this.businessDao.queryByUsername(principal.getName());
        Iterable<BusinessDetails> businessDetail = this.businessDetailDao.queryAll();
        return new ResponseEntity<List<JBussiness>>(this.convert(businesses, businessDetail), HttpStatus.OK);
    }

    @RequestMapping(value = "api/v10/getUserBusiness", method = RequestMethod.GET)
    public ResponseEntity<JBussiness> getBusiness(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "id", required = true) long id, Principal principal) {
        Business business = this.businessDao.findByPrimaryKey(id);
        if (null == business) {
            return new ResponseEntity<JBussiness>(HttpStatus.NOT_FOUND);
        }
        // check if the business is belong to the
        if (!(principal.getName().equals(business.getUsername()))) {
            return new ResponseEntity<JBussiness>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<JBussiness>(this.convert(business), HttpStatus.OK);
    }

    @RequestMapping(value = "api/v10/allCategories", method = RequestMethod.GET)
    public ResponseEntity<List<JBussinessCategory>> getAllBusinessCategories() {
        List<JBussinessCategory> categories = cambodiaExplorerService.getAllBusinessCategory();
        return new ResponseEntity<List<JBussinessCategory>>(categories, HttpStatus.OK);
    }

    /**
     * convert the business object list to the json value
     * 
     * @param businesses
     * @return
     */
    private List<JBussiness> convert(Iterable<Business> businesses, Iterable<BusinessDetails> businessDetail) {
        List<JBussiness> buzes = new ArrayList<JBussiness>();
        for(Business business : businesses) {
            JBussiness jBusiness = new JBussiness();
            jBusiness.setBusinessId(business.getId());
            jBusiness.setBusinessName(business.getBusinessName());
            jBusiness.setBusinessAddress(business.getBusinessLongAddress());
            jBusiness.setBusinessLat(business.getLattitue());
            jBusiness.setBusinessLon(business.getLongitute());
            jBusiness.setBusinessShortDescription(business.getBusinessDescription());
            BusinessCategory cat = busCatDao.findByPrimaryKey(business.getBusinessCategoryId());
            jBusiness.setCategory(this.convert(cat));
            for(BusinessDetails detail : businessDetail) {
                if (business.getId() == detail.getBusinessId()) {
                    jBusiness.setBusinessDetail(BusinessDetailsController.convert(detail));
                    break;
                }
            }
            buzes.add(jBusiness);
        }
        return buzes;
    }

    private List<OpenHours> getOpeningHours(List<String> openingHours) {
        if (openingHours != null) {
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            int i = 0;
            List<OpenHours> openHoursList = new ArrayList<OpenHours>();
            for(String open : openingHours) {
                OpenHours openHours = new OpenHours();
                String dayOfWeek = days[i];
                openHours.setDay(dayOfWeek);
                openHours.setOpen(open);
                openHoursList.add(openHours);
                i++;
            }
            return openHoursList;
        }
        return null;
    }

    private JBussiness convert(Business business) {
        JBussiness jBusiness = new JBussiness();
        jBusiness.setBusinessName(business.getBusinessName());
        jBusiness.setBusinessAddress(business.getBusinessLongAddress());
        jBusiness.setBusinessLat(business.getLattitue());
        jBusiness.setBusinessLon(business.getLongitute());
        jBusiness.setBusinessShortDescription(business.getBusinessDescription());

        return jBusiness;
    }

    /**
     * Convert business category to be the json object
     * 
     * @param cat
     * @return
     */
    private JBussinessCategory convert(BusinessCategory cat) {
        JBussinessCategory jCat = new JBussinessCategory();
        jCat.setId(cat.getId());
        jCat.setCatDesc(cat.getCatDesc());
        jCat.setCatKey(cat.getCatKey());
        return jCat;
    }

}
