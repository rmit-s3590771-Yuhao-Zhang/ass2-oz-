package com.rmit.APass2;

/**
 * 
 * @author samadikun
 *
 */

import javax.swing.JOptionPane;

public class WrongTypeException extends Exception {

	public WrongTypeException() {
		// TODO Auto-generated constructor stub
		String text = "<html><span style='font-size:20px'>Please choose only qualified athletes!</span></html>";
		JOptionPane.showMessageDialog(null,text,"Wrong athlete type",2);
		return;
	}

	public WrongTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WrongTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
