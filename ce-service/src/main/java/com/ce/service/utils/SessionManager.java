/**
 * 
 */
package com.ce.service.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sanyaeng
 * 
 */
public class SessionManager {

	public static enum SessionVariables {
		USER_KEY, USER_ROLE
	}

	/***
	 * getAttribute from session
	 * 
	 * @param request
	 * @param key
	 * @return Object
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		final HttpSession session = request.getSession(false);
		if (null != session) {
			return session.getAttribute(key);
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getAttribute(HttpServletRequest request, Object key) {
		return getAttribute(request, key.toString());
	}

	/**
	 * setAttribute to the session
	 * 
	 * @param request
	 * @param key
	 * @param value
	 * @param forceCreate
	 */
	public static void setAttribute(HttpServletRequest request, String key,
			Object value, boolean forceCreate) {
		HttpSession session = request.getSession(forceCreate);

		if (null != session) {
			session.setAttribute(key, value);
		}
	}

	/**
	 * Remove the existing session attribute
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeSession(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		if (null != session) {
			session.removeAttribute(key);
		}
	}

	/**
	 * setAttribute to the session
	 * 
	 * @param request
	 * @param key
	 * @param value
	 * @param forceCreate
	 */
	public static void setAttribute(HttpServletRequest request, Object key,
			Object value) {
		setAttribute(request, key.toString(), value, true);
	}
}
