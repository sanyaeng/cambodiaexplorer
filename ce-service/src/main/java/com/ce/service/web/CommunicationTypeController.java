package com.ce.service.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ce.service.dao.CommunicationTypeDao;
import com.ce.service.domain.CommunicationType;
import com.ce.service.json.JCommunicationType;

/**
 * Concrete Controller related to entity CommunicationType.
 */
@Controller
@RequestMapping(value = "/CommunicationType")
public class CommunicationTypeController {

    @Autowired
    private CommunicationTypeDao communicationTypeDao;

    /**
     * Add the new communication type
     * 
     * @param request
     * @param response
     * @param comType
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<JCommunicationType> addNewType(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute CommunicationType comType) {
        this.communicationTypeDao.persist(comType);
        return new ResponseEntity<JCommunicationType>(this.convert(comType), HttpStatus.OK);
    }

    /**
     * Get all communication type available
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ResponseEntity<List<JCommunicationType>> getAllCommunicationType(HttpServletRequest request,
            HttpServletResponse response) {
        Iterable<CommunicationType> comTypes = this.communicationTypeDao.queryAll();
        List<JCommunicationType> jComTypes = new ArrayList<JCommunicationType>();
        for(CommunicationType comType : comTypes) {
            jComTypes.add(this.convert(comType));
        }
        return new ResponseEntity<List<JCommunicationType>>(jComTypes, HttpStatus.OK);

    }

    /**
     * Update the provided communication type
     * 
     * @param request
     * @param response
     * @param type
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<JCommunicationType> updateCommunicationType(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute CommunicationType type) {
        this.communicationTypeDao.update(type);
        return new ResponseEntity<JCommunicationType>(this.convert(type), HttpStatus.OK);
    }

    /**
     * function to convert from domain to json object
     * 
     * @param comType
     * @return
     */
    private JCommunicationType convert(CommunicationType comType) {
        JCommunicationType jCommunicationOption = new JCommunicationType();
        jCommunicationOption.setId(comType.getType());
        jCommunicationOption.setComType(comType.getType());
        jCommunicationOption.setComTypeDesc(comType.getDescription());
        return jCommunicationOption;
    }

}
