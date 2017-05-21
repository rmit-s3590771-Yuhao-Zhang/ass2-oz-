package com.rmit.APass2;


/**
 * 
 * @author samadikun
 *
 */

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.scene.text.Font;

public class TooFewAthleteException extends Exception {
	
	public TooFewAthleteException() {
		// TODO Auto-generated constructor stub

		String text = "<html><span style='font-size:20px'>Atleast 4 athletes needed To start a game</span></html>";
		JOptionPane.showMessageDialog(null,text,"Not enough Athlete",2);
		return;
	}

	public TooFewAthleteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TooFewAthleteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TooFewAthleteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TooFewAthleteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
