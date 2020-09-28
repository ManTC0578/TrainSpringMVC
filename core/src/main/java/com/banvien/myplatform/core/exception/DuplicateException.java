package com.banvien.myplatform.core.exception;

/**
 * Copyright (c) 2012 BanVien Platform. All rights reserved.
 *
 */

/**
 *  <p>This exception hierarchy aims to let user code find and handle the duplicate object
 * when save or update object into the database</p>
 * 
 *
 *
 */
public class DuplicateException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8375504038901162449L;

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 */

	/**
	 * Constructor for DuplicateException.
	 * @param msg the detail message
	 */
	public DuplicateException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor for DuplicateException.
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 * data access API such as JDBC)
	 */
	public DuplicateException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
