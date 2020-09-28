package com.banvien.myplatform.core.exception;

/**
 * Copyright (c) 2012 BanVien Platform. All rights reserved.
 */

/**
 *
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1606292286362027615L;

	/**
	 * Constructor for ServiceException 
	 * @param message Exception message
	 */
	public ServiceException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor for ServiceException.
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 * data access API such as JDBC)
	 */
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
