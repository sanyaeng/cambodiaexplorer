/**
 * 
 */
package com.ce.service.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sanyaeng
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * The admin form
     * 
     * @param request
     * @param response
     * @param models
     * @param principle
     * @return
     */
    @RequestMapping(value = "/adminform.html", method = RequestMethod.GET)
    public void adminForm(HttpServletRequest request, HttpServletResponse response, Model models, Principal principle) {
        // Collection<GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        // Set<String> roles = new HashSet<String>();
        // for(GrantedAuthority authority : authorities) {
        // roles.add(authority.getAuthority());
        // }
        // SessionManager.setAttribute(request, SessionVariables.USER_ROLE, roles);
        // try {
        // response.sendRedirect("businesscategory.html");
        // }
        // catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // return "adminform";
    }

    @RequestMapping(value = "/businesscategory.html", method = RequestMethod.GET)
    public String businessCategory(HttpServletRequest request, HttpServletResponse response) {
        return "businesscategory";
    }

    @RequestMapping(value = "/communication.html", method = RequestMethod.GET)
    public String communicationPage(HttpServletRequest request, HttpServletResponse response, Model models) {
        return "communicationoptions";
    }
}
