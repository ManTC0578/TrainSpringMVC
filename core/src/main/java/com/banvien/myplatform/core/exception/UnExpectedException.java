package com.banvien.myplatform.core.exception;

/**
 * 
 */

/**
 * @author hieule
 *
 */
public class UnExpectedException extends RuntimeException {
	/**
	 * Determines if a de-serialized file is compatible with this class.
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -1981901066839387271L;

	/**
	 * Constructor for DuplicateException.
	 * @param msg the detail message
	 */
	public UnExpectedException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor for DuplicateException.
	 * @param msg the detail message
	 * @param cause the root cause (usually from using a underlying
	 * data access API such as JDBC)
	 */
	public UnExpectedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
