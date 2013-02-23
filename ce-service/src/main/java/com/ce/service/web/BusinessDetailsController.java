/**
 * 
 */
package com.ce.service.web;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.ce.service.dao.BusinessDetailsDao;
import com.ce.service.domain.BusinessDetails;
import com.ce.service.json.JBusinessDetails;

/**
 * @author sanya
 * 
 */
@Controller
@RequestMapping(value = "businessDetail")
public class BusinessDetailsController {
    private final static Logger LOG = LoggerFactory.getLogger(BusinessDetailsController.class);

    @Autowired
    private BusinessDetailsDao  detailDao;

    @RequestMapping(value = "api/v10/add", method = RequestMethod.POST)
    public ResponseEntity<JBusinessDetails> add(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ajaxPayment[]", required = false) String[] ajaxPayment,
            @RequestParam(value = "ajaxImgUrls[]", required = false) String[] ajaxImgUrls,
            @RequestParam(value = "ajaxSpecialities[]", required = false) String[] ajaxSpecialities,
            @RequestParam(value = "ajaxServices[]", required = false) String[] ajaxServices,
            @RequestParam(value = "ajaxOpenHours[]", required = false) String[] ajaxOpenHours,
            @ModelAttribute BusinessDetails details) {

        LOG.debug("ajaxPayment leng {}", ajaxPayment.length);

        if (null != ajaxPayment) {
            details.setPaymentAccepted(Arrays.asList(ajaxPayment));
        }
        if (null != ajaxOpenHours) {
            details.setOpenHours(Arrays.asList(ajaxOpenHours));
        }
        if (null != ajaxSpecialities) {
            details.setSpecialities(Arrays.asList(ajaxSpecialities));
        }
        if (null != ajaxImgUrls) {
            details.setImageUrls(Arrays.asList(ajaxImgUrls));
        }
        if (null != ajaxServices) {
            details.setServices(Arrays.asList(ajaxServices));
        }
        detailDao.persist(details);
        return new ResponseEntity<JBusinessDetails>(HttpStatus.OK);
    }

    @RequestMapping(value = "api/v10/detail", method = RequestMethod.GET)
    public ResponseEntity<JBusinessDetails> getDetail(HttpServletRequest request, HttpServletResponse response,
            @RequestParam Long id) {
        BusinessDetails detail = detailDao.findByPrimaryKey(id);
        if (null == detail) {
            return new ResponseEntity<JBusinessDetails>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<JBusinessDetails>(convert(detail), HttpStatus.OK);
    }

    @RequestMapping(value = "api/v10/detail", method = RequestMethod.GET, params = {"businessId"})
    public ResponseEntity<List<JBusinessDetails>> getDetails(HttpServletRequest request, HttpServletResponse response,
            @RequestParam Long businessId) {
        Iterable<BusinessDetails> detail = detailDao.queryByBusinessId(businessId);
        if (null == detail) {
            return new ResponseEntity<List<JBusinessDetails>>(HttpStatus.NOT_FOUND);
        }
        List<JBusinessDetails> jDetails = new ArrayList<JBusinessDetails>();
        for(BusinessDetails businessDetails : detail) {
            jDetails.add(convert(businessDetails));
        }
        if (jDetails.isEmpty()) {
            return new ResponseEntity<List<JBusinessDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<JBusinessDetails>>(jDetails, HttpStatus.OK);
    }

    public static JBusinessDetails convert(BusinessDetails details) {
        JBusinessDetails businessDetails = new JBusinessDetails(details.getId(), details.getBusinessId(), details.getInfo(),
                details.getPaymentAccepted(), details.getImageUrls(), details.getOpenHours(), details.getSpecialities(),
                details.getServices());

        return businessDetails;
    }
}
