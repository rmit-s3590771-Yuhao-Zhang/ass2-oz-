package com.rmit.APass2;


/**
 * 
 * @author samadikun
 *
 */

import javax.swing.JOptionPane;

public class NoRefereeException extends Exception {

	public NoRefereeException() {
		// TODO Auto-generated constructor stub
		String text = "<html><span style='font-size:20px'>Please choose one official for the game!</span></html>";
		JOptionPane.showMessageDialog(null,text,"Need one official",2);
		return;
	}

	public NoRefereeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoRefereeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoRefereeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoRefereeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
}
