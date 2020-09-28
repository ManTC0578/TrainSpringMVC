package com.banvien.myplatform.core.exception;

/**
 * Copyright (c) 2012 BanVien Platform. All rights reserved.
 * 
 */

/**
 * <p>This exception hierarchy aims to let user code find and handle the object not found
 * when retrieving object from the database</p>
 * 
 *
 *
 */
public class ObjectNotFoundException extends ServiceException{
	/**
	 * Determines if a de-serialized file is compatible with this class.
	 */

	private static final long serialVersionUID = 7449787067784714217L;

	/**
	 * Constructor for ObjectNotFoundException 
	 * @param message Exception message
	 */
	public ObjectNotFoundException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor for ObjectNotFoundException.
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 * data access API such as JDBC)
	 */
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
