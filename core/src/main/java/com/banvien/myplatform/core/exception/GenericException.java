package com.banvien.myplatform.core.exception;


@SuppressWarnings("serial")
public class GenericException extends Exception {

    public GenericException(String message)  {
        super(message);
    }

	public GenericException(Exception ex) {
		super(ex);
	}
       
}
