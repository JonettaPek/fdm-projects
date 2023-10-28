package com.example.fdmgroup.forexplatform.exception;

import com.example.fdmgroup.forexplatform.model.Trader;

public class TraderNotFoundException extends RuntimeException {

	public TraderNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TraderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TraderNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TraderNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TraderNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
