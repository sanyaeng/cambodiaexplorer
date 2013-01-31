/**
 * 
 */
package com.ce.service.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.mardao.core.domain.AbstractLongEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sanyaeng
 * 
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User extends AbstractLongEntity implements UserDetails {// extends AEDStringEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8078135435062671082L;

    @Basic
    private String            displayName;

    @Basic
    private String            username;

    @Basic
    private String            password;

    @Override
    public String toString() {
        return String.format("UserEntity{id:%d, username:%s, password:***, display:%s}", getId(), username, displayName);
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private static final GrantedAuthority            GRANTED_USER     = new SimpleGrantedAuthority("ROLE_USER");
    public static final Collection<GrantedAuthority> AUTHORITIES_USER = Arrays.asList(GRANTED_USER);

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AUTHORITIES_USER;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
