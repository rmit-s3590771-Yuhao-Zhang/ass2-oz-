package com.rmit.APass2;

/**
 * 
 * @author samadikun
 *
 */

import javax.swing.JOptionPane;

public class GameFullException extends Exception {

	public GameFullException() {
		// TODO Auto-generated constructor stub
		String text = "<html><span style='font-size:20px'>At most only 8 athletes can participate</span></html>";
		JOptionPane.showMessageDialog(null,text,"Too many Athlete",2);
		return;
	}

	public GameFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GameFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GameFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
