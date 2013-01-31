/**
 * 
 */
package com.ce.service.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping({"/businessDetail", "/api/businessDetail"})
public class BusinessDetailsController {

    @Autowired
    private BusinessDetailsDao detailDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<JBusinessDetails> getDetail(HttpServletRequest request, HttpServletResponse response,
            @RequestParam Long id) {
        BusinessDetails detail = detailDao.findByPrimaryKey(id);
        if (null == detail) {
            return new ResponseEntity<JBusinessDetails>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<JBusinessDetails>(convert(detail), HttpStatus.OK);
    }

    private JBusinessDetails convert(BusinessDetails details) {
        JBusinessDetails businessDetails = new JBusinessDetails();
        return businessDetails;
    }
}
