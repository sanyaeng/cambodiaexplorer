/**
 * 
 */
package com.ce.service.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ce.service.BaseController;
import com.ce.service.dao.BusinessCategoryDao;
import com.ce.service.dao.BusinessDao;
import com.ce.service.dao.UserDao;
import com.ce.service.domain.Business;
import com.ce.service.domain.BusinessCategory;
import com.ce.service.domain.User;
import com.ce.service.json.JBussiness;
import com.ce.service.json.JUser;
import com.ce.service.security.CeUserDetailsService;
import com.ce.service.utils.CambodiaExplorerService;
import com.ce.service.utils.StringUtils;

/**
 * @author sanyaeng
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserDao                 userDao;
    @Autowired
    private BusinessDao             businessDao;
    @Autowired
    private BusinessCategoryDao     businessCategoryDao;

    @Autowired
    private CambodiaExplorerService cambodiaExplorerService;

    @Autowired
    private CeUserDetailsService    userService;

    private final static Logger     LOG = LoggerFactory.getLogger(UserController.class);

    /**
     * Admin login page
     * 
     * @param request
     * @param response
     * @param models
     * @return
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model models) {
        return "login";
    }

    /**
     * Admin logout page
     * 
     * @param request
     * @param response
     * @param models
     * @return
     */
    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, HttpServletResponse response, Model models) {
        return "login";
    }

    @RequestMapping(value = "api/addNew", method = {RequestMethod.POST})
    public ResponseEntity<JUser> addNew(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
        try {
            userService.loadUserByUsername(user.getUsername());
            return new ResponseEntity<JUser>(HttpStatus.CONFLICT);
        }
        catch (UsernameNotFoundException e) {
            final User registeredUser = userService.insertUser(user.getUsername(), user.getPassword(), user.getDisplayName());

            return new ResponseEntity<JUser>(this.convert(registeredUser), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "api/getMe", method = RequestMethod.GET)
    public ResponseEntity<JUser> getMe(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        LOG.debug("get current user {}", principal);
        if (null == principal) {
            return new ResponseEntity<JUser>(HttpStatus.UNAUTHORIZED);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<JUser>(convert(user), HttpStatus.OK);
    }

    /**
     * Get all the business
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "api/getAllBusiness", method = RequestMethod.GET)
    public ResponseEntity<List<JBussiness>> getAllBusinesses(HttpServletRequest request, HttpServletResponse response,
            Principal principle) {
        List<JBussiness> jBusinesses = new ArrayList<JBussiness>();
        if (null != principle) {
            Iterable<Business> businesses = businessDao.queryByUsername(principle.getName());
            jBusinesses = convert(businesses);
        }
        return new ResponseEntity<List<JBussiness>>(jBusinesses, HttpStatus.OK);
    }

    private List<JBussiness> convert(Iterable<Business> businesses) {
        List<JBussiness> jBusinesses = new ArrayList<JBussiness>();
        for(Business business : businesses) {
            JBussiness jBusiness = new JBussiness();
            jBusiness.setBusinessAddress(business.getBusinessLongAddress());
            jBusiness.setBusinessLat(business.getLattitue());
            jBusiness.setBusinessLon(business.getLongitute());
            jBusiness.setBusinessLongDescription(business.getBusinessDescription());
            jBusiness.setBusinessName(business.getBusinessName());
            // if (business.getBusinessCategory() != null) {
            // BusinessCategory category = businessCategoryDao.findByPrimaryKey(business.getBusinessCategory().toString());
            // jBusiness.setCategory(cambodiaExplorerService.convert(category));
            // }
        }
        return jBusinesses;
    }

    private JUser convert(User user) {
        JUser jUser = new JUser();
        jUser.setUsername(user.getUsername());
        jUser.setDisplayName(user.getDisplayName());
        jUser.setId(user.getId());

        return jUser;
    }
}
