package com.rmit.APass2;

import java.util.Random;

/**
 * 
 * @author tirsky
 *
 */
public class Sprinter extends Athlete{

	public Sprinter(String ID, String type, String name, int age, String state) {
		// TODO Auto-generated constructor stub
		super(ID, type, name, age, state);
	}
	final int range = 11;
	final int start = 10;
	@Override
	public int compete(String gameType) {
		// TODO Auto-generated method stub
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;
	}

}
