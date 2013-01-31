/**
 * 
 */
package com.ce.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ce.service.dao.UserDao;
import com.ce.service.domain.User;

/**
 * @author sanya
 * 
 */
public class CeUserDetailsService implements UserDetailsService {

    private final static Logger LOG = LoggerFactory.getLogger(CeUserDetailsService.class);
    private UserDao             userDao;
    private PasswordEncoder     passwordEncoder;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public User insertUser(String userName, String password, String displayName) {
        User user = userDao.findByUsername(userName);
        if (null == user) {
            user = new User();
            user.setUsername(userName);
        }

        // update properties
        user.setDisplayName(displayName);

        if (null != password) {
            // password will be encrypted before set
            String encoded = passwordEncoder.encode(password);
            user.setPassword(encoded);
        }
        userDao.update(user);
        // user = (User) loadUserByUsername(user.getUsername());
        return user;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder
     *            the passwordEncoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
