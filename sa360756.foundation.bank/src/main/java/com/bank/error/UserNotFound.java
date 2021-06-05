package com.bank.error;

public class UserNotFound extends RuntimeException{
	 public UserNotFound(String message) {
	       super(message); 
	    }

}
