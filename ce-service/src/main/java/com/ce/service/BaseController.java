/**
 * 
 */
package com.ce.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import com.ce.service.domain.User;

/**
 * @author sanya
 * 
 */
public class BaseController {

    protected RestTemplate restTemplate;

    protected User getCurrentUser(HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
