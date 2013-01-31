/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ce.service.web;

/**
 *
 * @author os
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(id);
    }
    
}
