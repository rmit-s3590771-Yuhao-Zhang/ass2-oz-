package com.rmit.APass2;

import java.util.Random;

/**
 * 
 * @author tirsky
 *
 */
public class Cyclist extends Athlete{

	public Cyclist(String ID, String type, String name, int age, String state) {
		// TODO Auto-generated constructor stub
		super(ID, type, name, age, state);
	}
	final int range = 301;
	final int start = 500;

	// implement compete
	public int compete(String gameType) {
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}
}
