package com.rmit.APass2;

import java.util.Random;


/**
 * 
 * @author tirsky
 *
 */
public class SuperAthlete extends Athlete{

	public SuperAthlete(String ID, String type, String name, int age, String state) {
		// TODO Auto-generated constructor stub
		super(ID, type, name, age, state);
	}
	private int range = 0;
	private int start = 0;

	public int compete(String gameType) {
		switch (gameType) {
		case Driver.running:
			range = 11;
			start = 10;
			break;
		case Driver.swimming:
			range = 101;
			start = 100;
			break;
		case Driver.cycling:
			range = 301;
			start = 500;
			break;
		}
		int seconds;
		Random r = new Random();
		seconds = r.nextInt(range) + start;
		return seconds;

	}

}
