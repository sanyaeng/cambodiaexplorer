/**
 * 
 */
package com.ce.service.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/api/businessDetail")
public class BusinessDetailsController {

    @Autowired
    private BusinessDetailsDao detailDao;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<JBusinessDetails> add(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "payment") String[] payment, @RequestParam(value = "imgUrl") String[] imgUrls,
            BusinessDetails details) {

        if (null != payment) {
            details.setPaymentAccepted(Arrays.asList(payment));
        }

        if (null != imgUrls) {
            details.setImageUrls(Arrays.asList(imgUrls));
        }

        detailDao.persist(details);
        return new ResponseEntity<JBusinessDetails>(HttpStatus.OK);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ResponseEntity<JBusinessDetails> getDetail(HttpServletRequest request, HttpServletResponse response,
            @RequestParam Long id) {
        BusinessDetails detail = detailDao.findByPrimaryKey(id);
        if (null == detail) {
            return new ResponseEntity<JBusinessDetails>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<JBusinessDetails>(convert(detail), HttpStatus.OK);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET, params = {"businessId"})
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

    private JBusinessDetails convert(BusinessDetails details) {
        JBusinessDetails businessDetails = new JBusinessDetails();
        return businessDetails;
    }
}
