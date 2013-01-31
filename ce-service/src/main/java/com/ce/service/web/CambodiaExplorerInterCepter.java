/**
 * 
 */
package com.ce.service.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ce.service.utils.SessionManager;
import com.ce.service.utils.SessionManager.SessionVariables;

/**
 * @author sanyaeng
 * 
 */
public class CambodiaExplorerInterCepter extends HandlerInterceptorAdapter {

    public static final String BODY_PAGE_NAME        = "bodyPage";
    public static final String INDEX_PAGE_NAME       = "index";
    public static final String SPRING_SECURITY_LOGIN = "login";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (modelAndView == null) { // avoid redirect permenant
            return;
        }

        // /googlef257fdc58fcb22d9.html google verification
        if ("googlef257fdc58fcb22d9".equals(modelAndView.getViewName())) {
            response.setContentType("text/html; charset=UTF-8");
            return;
        }

        // robots.txt access
        if ("robots".equals(modelAndView.getViewName())) {
            response.setContentType("text/plain");
            return;
        }
        // sitemap.xml access
        if ("sitemap".equals(modelAndView.getViewName())) {
            response.setContentType("application/xhtml+xml; charset=UTF-8");
            return;
        }

        response.setContentType("text/html; charset=UTF-8");

        String index = INDEX_PAGE_NAME;

        @SuppressWarnings("unchecked")
        Set<String> roles = (HashSet<String>) SessionManager.getAttribute(request, SessionVariables.USER_ROLE);
        if (null != roles) {
            if (roles.contains("ROLE_ADMIN")) {
                index = "admin/" + index;
            }
        }
        /*
         * if (modelAndView.getViewName().equals(SPRING_SECURITY_LOGIN)) { index = "admin/" + index; }
         */
        // render the view page section
        modelAndView.addObject(BODY_PAGE_NAME, modelAndView.getViewName());

        // check title & description page according to page

        modelAndView.setViewName(index);
    }
}
