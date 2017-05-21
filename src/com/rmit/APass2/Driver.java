package com.rmit.APass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;


/**
 * 
 * @author tirsky
 *
 */
public class Driver {
	private Set<String> itemSet = new TreeSet<>();
	private ArrayList<Athlete> athleteList = new ArrayList<>();
	private ArrayList<Official> officialList = new ArrayList<>();
	private ArrayList<String> result = new ArrayList<>();
	private ArrayList<String> gamesHistory = new ArrayList<>();
	private ArrayList<String> athletePoint;
	private List<String> gameResult;
	private List<Integer> scoreList;
	public static final String swimming = "Swimming";
	public static final String cycling = "Cycling";
	public static final String running = "Running";
	private int gameNum = 0;
	public int athleteNo;
	private String gameid;
	private String text;
	private Game game = null;
	
	public boolean databaseCheck() {
		// TODO Auto-generated method stub
		File participants = new File("participants.db");
		if (participants.exists()) {
			return true;
		} else
			text = "<html><span style='font-size:20px'>No database detected, result will be saved "
					+ "locally</span></html>";
			JOptionPane.showMessageDialog(null,text,"No database detected",2);
			return false;
	}

	public boolean txtCheck() {
		// TODO Auto-generated method stub
		File participants = new File("participants.txt");
		if (participants.exists()) {
			return true;
		} else
			return false;
	}

	public void getData() {
		// TODO Auto-generated method stub
		if (databaseCheck()){
			readFromDataBase();
			printgameHistory();
			
		}else{
			readFromTxt();
		}
	}

	private void readFromTxt() {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try{
			FileOutputStream writer = new FileOutputStream("Game Result.txt");
			writer.write(("").getBytes());
			writer.close();
			br = new BufferedReader(new FileReader("participants.txt"));
			String column = null;
			while ((column = br.readLine()) != null){
				itemSet.add(column);
			}
		}catch (FileNotFoundException e1){
			e1.getMessage();
		}catch (IOException e2){
			e2.printStackTrace();
		}finally {
			try{
				if(br != null){
					br.close();
				}
			}catch (IOException e3){
					throw new RuntimeException("Did not close the file.");
			}
		}
	}
	

	private void readFromDataBase() {
		try{
			FileOutputStream writer = new FileOutputStream("Game Result.txt");
			writer.write(("").getBytes());
			writer.close();
			Class.forName("org.sqlite.JDBC");
			Connection participants = DriverManager.getConnection("jdbc:sqlite:participants.db");
			Statement stmt = participants.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM participants;");
			int ColumnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				String column ="";
				for (int i = 1; i <= ColumnCount; i++) {
					if(i<ColumnCount){
						column += rs.getString(i) + ",  ";
					}else{
						column += rs.getString(i)+".  ";
					}
				}
					itemSet.add(column);
				}
			}catch (Exception e){
				System.err.println("error here" + e.getClass().getName()+" : "+e.getMessage());
		}
	}

	public void initialisation() {
		createDatabase();
		for (String s : itemSet) {
			String[] items = s.split(",\\s*");
			if (!validData(items)) {
				continue;
			}
			String ID = items[0];
			String type = items[1];
			String name = items[2];
			int age = Integer.parseInt(items[3]);
			String state = items[4];
			if (type.equals("Cyclist")) {
				athleteList.add(new Cyclist(ID, type, name, age, state));
			} else if (type.equals("Swimmer")) {
				athleteList.add(new Swimmer(ID, type, name, age, state));
			} else if (type.equals("Sprinter")) {
				athleteList.add(new Sprinter(ID, type, name, age, state));
			} else if (type.equals("Super")) {
				athleteList.add(new SuperAthlete(ID, type, name, age, state));
			} else if (type.equals("Officer")) {
				officialList.add(new Official(ID, type, name, age, state));
			}
		}
	}

	public ArrayList<Athlete> getAthleteList(){
		return athleteList;
	}
	private boolean validData(String[] data) {
		for (String s : data) {
			if ("".equals(s) || "null".equals(s))
				return false;
		}
		return true;
	}

	private void createDatabase() {
		String drop = "Drop table if exists result";
		String sql = " Create table result(" + "GameID text," + "OfficialID text," + "AthleteID text,"
				+ "Result integer," + "Score integer" + ");";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection gameResults = DriverManager.getConnection("jdbc:sqlite:gameResults.db");
			Statement stmt = gameResults.createStatement();
			stmt.execute(drop);
			stmt.execute(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}
	
	public void startgame(String gameType, ArrayList<Athlete> athletes, Official official) {
		athleteNo=athletes.size();
		String gameID = gameType.charAt(0) + (gameNum < 10 ? "0" : "") + gameNum;
		switch (gameType) {
		case swimming:
			game = new Swimming(gameID, gameType, athletes, official);
		case cycling:
			game = new Cycling(gameID, gameType, athletes, official);
		case running:
			game = new Running(gameID, gameType, athletes, official);
		}
		game.start();
		generateResult(official);
		
		////////////////////////////////////////////////////////////////////////
		printGameResult(game);
		gameResultDatabase();
		printgameHistory();
		gameNum++;
	}

	private void printgameHistory() {
		// TODO Auto-generated method stub
		gamesHistory = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("gameResults.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				gamesHistory.add(line);
			}
		} catch (IOException e) {

		} finally {
			try {
				if (reader != null)
					reader.close();

			} catch (IOException e) {

			}
		}
	}

	private void gameResultDatabase() {
		// TODO Auto-generated method stub
		Integer[] rank = {0,5,2,1};
		try {
			Class.forName("org.sqlite.JDBC");
			Connection gameResults = DriverManager.getConnection("jdbc:sqlite:gameResults.db");
			for (int i = 1; i < gameResult.size(); i++) {
				PreparedStatement pstmt = gameResults.prepareStatement("insert into result values(?,?,?,?,?)");
				pstmt.setString(1, gameid);
				pstmt.setString(2, gameResult.get(0));
				pstmt.setString(3, gameResult.get(i));
				pstmt.setInt(4, scoreList.get(i));
				pstmt.setInt(5, rank[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private List<Athlete> sortAthletes(List<Athlete> athletes) {
		List<Athlete> sortList = new ArrayList<Athlete>(athletes);
		Collections.sort(sortList, new Comparator<Athlete>() {
			@Override
			public int compare(Athlete a1, Athlete a2) {
				return a2.getPoints() - a1.getPoints();
			}
		});
		return sortList;
	}
	
	private void generateResult(Official official){
		gameResult = game.getAllResult(athleteNo);
		scoreList = official.getscoreList();
	}
	
	
	private void printGameResult(Game game2) {
		// TODO Auto-generated method stub
		result = new ArrayList<>();
		gameid = game.getID();
		Calendar cal = Calendar.getInstance();
		DateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			bw = new BufferedWriter(new FileWriter("gameResults.txt", true));
			out = new PrintWriter(bw);
			for (int i = 0; i < gameResult.size(); i++) {
				if (i == 0) {
					result.add(gameid + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
					out.println(gameid + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
				} else if (i == 1) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 5");
					out.println(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 5");
				} else if (i == 2) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 2");
					out.println(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 2");
				} else if (i == 3) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 1");
					out.println(gameResult.get(i) + " ," + scoreList.get(i-1) + " , 1");
				} else if (i>3){
					result.add(gameResult.get(i) + " ,"+ scoreList.get(i-1) + " , 0" );
					
				}
			}
			
		} catch (FileNotFoundException e1) {
			e1.getMessage();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e3) {
				throw new RuntimeException("Fail to Close File ");
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e4) {
				throw new RuntimeException("Fail to Close File ");
			}
		}

	}

	public ArrayList<Official> getOfficialList() {
		// TODO Auto-generated method stub
		return officialList;
	}

	public ArrayList<String> getgamesHistory() {
		// TODO Auto-generated method stub
		return gamesHistory;
	}

	public void printSortAthelets() {
		// TODO Auto-generated method stub
		athletePoint = new ArrayList<>();
		List<Athlete> sortList = sortAthletes(athleteList);
		for (int i = 0; i < sortList.size(); i++) {
			printAthelet(sortList.get(i));
		}
	}

	private void printAthelet(Athlete athlete) {
		// TODO Auto-generated method stub
		athletePoint.add("Points: " + athlete.getPoints() + ", " + athlete.toString());
	}

	public ArrayList<String> getathletePoint() {
		// TODO Auto-generated method stub
		return athletePoint;
	}

	public ArrayList<String> getresult() {
		// TODO Auto-generated method stub
		return result;
	}
	
	public List<Integer> getscore() {
		return scoreList;
	}
}

	

