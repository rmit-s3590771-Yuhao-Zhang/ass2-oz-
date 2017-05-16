import java.util.ArrayList;

import com.rmit.APass2.Athlete;
import com.rmit.APass2.Cyclist;
import com.rmit.APass2.Driver;
import com.rmit.APass2.GameFullException;
import com.rmit.APass2.NoRefereeException;
import com.rmit.APass2.Official;
import com.rmit.APass2.Participants;
import com.rmit.APass2.Sprinter;
import com.rmit.APass2.SuperAthlete;
import com.rmit.APass2.Swimmer;
import com.rmit.APass2.TooFewAthleteException;
import com.rmit.APass2.WrongTypeException;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author tirsky
 *
 */
public class Ozlympic extends Application{
	Stage window;
	Scene scene1, scene2, scene3,
		  scene4, scene5, scene6;
	ArrayList<Athlete> ChoosenAthletes = new ArrayList<>();
	Official ChoosenOfficaial = null;
	final int NotEnoughAthleteException = 4;
	final int TooMuchAthleteException = 8;
	private String sportType;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Driver driver = new Driver();
		driver.databaseCheck();
		driver.txtCheck();
		driver.getData();
		driver.initialisation();
	//scene1 Welcome View set up
		VBox Scene1VBox = new VBox();
		HBox Scene1HBox = new HBox();
		Text title1 = new Text();
		Label MainLabel = new Label();
		Button buttonWelcome = new Button("Welcome!");
		Button buttonExit = new Button("Exit!");
		title1.setText("Welcome to Ozlympic game!");
		buttonWelcome.setPrefSize(300, 50);
		buttonExit.setPrefSize(300, 50);
		MainLabel.setPrefSize(300, 100);
		MainLabel.setFont(Font.font(22));
		Scene1HBox.setPadding(new Insets(2));
		Scene1HBox.setSpacing(20);
		Scene1HBox.setAlignment(Pos.BOTTOM_CENTER);
		Scene1HBox.getChildren().addAll(buttonWelcome,buttonExit);
		Scene1VBox.setPadding(new Insets(2));
		Scene1VBox.setSpacing(20);
		Scene1VBox.setAlignment(Pos.CENTER);
		Scene1VBox.getChildren().addAll(MainLabel,Scene1HBox);
		scene1 = new Scene(Scene1VBox,700,700);
		primaryStage.setTitle("Ozlympic Game");
		primaryStage.setScene(scene1);
		primaryStage.show();
		
	//Scene2 Menu set up
		VBox Scene2VBox = new VBox();
		Text title2 = new Text();
		Button buttonNewGame = new Button("New Game!");
		Button buttonGameHistory = new Button("Check history");
		Button buttonresult = new Button("Check points");
		Button buttonExit2 = new Button("Exit!");
		if(!driver.databaseCheck()){
			title2.setText("Can not find DataBase" );
			title2.setFill(Color.RED);
			if(!driver.txtCheck()){
				title2.setText("Can not find Database and participants.txt");
				title2.setFill(Color.RED);
			}
		}
		else { 
			title2.setText("Let's start a  new game first!");
			title2.setFill(Color.GREEN);
		}
		buttonNewGame.setPrefSize(400, 50);
		buttonGameHistory.setPrefSize(400, 50);
		buttonresult.setPrefSize(400, 50);
		buttonExit2.setPrefSize(400, 50);
		Scene2VBox.setPadding(new Insets(4));
		Scene2VBox.setSpacing(10);
		Scene2VBox.setAlignment(Pos.CENTER);
		title1.setFont(Font.font("Arial", FontWeight.BLACK, 20));
		Scene2VBox.getChildren().addAll(title1,buttonNewGame,buttonGameHistory,buttonresult,buttonExit2);
		scene2 = new Scene(Scene2VBox,400,400);
		
		//scene3 main view set up
		AnchorPane rootPane1  = new AnchorPane();
		HBox Scene3HBox = new HBox();
		VBox Scene3VBox = new VBox();
		Text title3 = new Text();
		Label gameLB = new Label("Select a game type");
		Label athleteLB = new Label("Select 4~8 athletes");
		Label officialLB = new Label("Select one official");
		ListView<String> gameList = new ListView<String>(FXCollections.observableArrayList(Driver.swimming, Driver.cycling, Driver.running));
		ListView<Participants> ParticipantsList1 = new ListView<>();
		ListView<Participants> ParticipantsList2 = new ListView<>();
		Button buttonMainMenu = new Button("Main menu");
		Button buttonStartGame = new Button("Run Game!");
		Button buttonExit3 = new Button("Exit!");
		ArrayList<Participants> participants1 = new ArrayList<>();
		ArrayList<Participants> participants2 = new ArrayList<>();
		ArrayList<Athlete> athletesList = driver.getAthleteList();
		ArrayList<Official> officialList = driver.getOfficialList();
		participants1.addAll(athletesList); 
		participants2.addAll(officialList); 
		gameLB.setPrefSize(150, 30);
		athleteLB.setPrefSize(150, 30);
		officialLB.setPrefSize(150, 30);
		title3.setFill(Color.RED);
		title3.setFont(Font.font("Arial",FontWeight.BLACK,16));
		buttonMainMenu.setPrefSize(300, 50);
		buttonStartGame.setPrefSize(300, 50);
		buttonExit3.setPrefSize(300, 50);
		ObservableList<Participants> ParticipantsObservableList1 = FXCollections.observableArrayList(participants1);
		ObservableList<Participants> ParticipantsObservableList2 = FXCollections.observableArrayList(participants2);

		ParticipantsList1.setItems(ParticipantsObservableList1);
		ParticipantsList2.setItems(ParticipantsObservableList2);
		ParticipantsList1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Scene3HBox.getChildren().addAll(buttonMainMenu,buttonStartGame,buttonExit3);
		Scene3VBox.getChildren().addAll(gameLB,gameList,athleteLB,ParticipantsList1,ParticipantsList2,Scene3HBox);
		rootPane1.getChildren().addAll(Scene3VBox);
		
		//scene4 game history set up
		AnchorPane rootpane2 = new AnchorPane();
		HBox scene4HBox = new HBox();
		VBox scene4VBox = new VBox();
		Button buttonMainMenu2 = new Button("Main Menu");
		Button buttonExit4 = new Button("Exit!");
		ListView<String> gameHistoryList = new ListView<>();
		buttonMainMenu2.setPrefSize(300, 50);
		buttonExit4.setPrefSize(300, 50);
		gameHistoryList.setPrefWidth(350);
		scene4HBox.getChildren().addAll(buttonMainMenu2,buttonExit4);
		scene4VBox.getChildren().addAll(gameHistoryList,scene4HBox);
		rootpane2.getChildren().addAll(scene4VBox);
		
		//scene5 set up
		AnchorPane rootpane3 = new AnchorPane();
		HBox scene5HBox = new HBox();
		VBox scene5VBox = new VBox();
		Button buttonMainMenu3= new Button("Main Screen");
		Button buttonExit5 = new Button("Exit!");
		ListView<String> athletePointList = new ListView<>();
		buttonMainMenu3.setPrefSize(300,50);
		buttonExit5.setPrefSize(300, 50);
		athletePointList.setPrefWidth(400);
		scene5HBox.getChildren().addAll(buttonMainMenu3,buttonExit5);
		scene5VBox.getChildren().addAll(athletePointList,scene5HBox);
		rootpane3.getChildren().addAll(scene5VBox);
		
		//scene 6 set up 
		AnchorPane rootpane4 = new AnchorPane();
		HBox scene6HBox = new HBox();
		VBox scene6VBox = new VBox();
		Button buttonMainMenu4= new Button("Main Screen");
		Button buttonExit6 = new Button("Exit!");
		ListView<String> resultList = new ListView<>();
		buttonMainMenu4.setPrefSize(300,50);
		buttonExit5.setPrefSize(300, 50);
		resultList.setPrefWidth(400);
		scene6HBox.getChildren().addAll(buttonMainMenu4,buttonExit6);
		scene6VBox.getChildren().addAll(resultList,scene6HBox);
		rootpane4.getChildren().addAll(scene6VBox);
		
		//function
		scene3 = new Scene(Scene3VBox);
		scene4 = new Scene(scene4VBox);
		scene5 = new Scene(scene5VBox);
		scene6 = new Scene(scene6VBox);
		buttonWelcome.setOnAction(e -> {
			window.setScene(scene2);
		});
		buttonExit.setOnAction(e ->{
			window.close();
		});
		buttonNewGame.setOnAction(e ->{
			window.setScene(scene3);
		});
		buttonGameHistory.setOnAction(e -> {
			ArrayList<String> gameHistory = driver.getgamesHistory();
			ObservableList<String> gameHistroyObservableList = FXCollections.observableArrayList(gameHistory);
			gameHistoryList.setItems(gameHistroyObservableList);
			window.setTitle("Game History");
			window.setScene(scene4);		
		});
		buttonresult.setOnAction(e -> {
			driver.printSortAthelets();
			ArrayList<String> athletePoint = driver.getathletePoint();
			ObservableList<String> athletePointObservableList = FXCollections.observableArrayList(athletePoint);
			athletePointList.setItems(athletePointObservableList);
			window.setTitle("Athlete Points");
			window.setScene(scene4);			
		});	
		buttonExit2.setOnAction(e -> {			
			window.close();		
		});	
		
		
		MultipleSelectionModel<Participants> selectionModel = ParticipantsList1.getSelectionModel();
		selectionModel.clearSelection();
		ParticipantsList1.setCellFactory(lv -> {
			ListCell<Participants> cell = new ListCell<Participants>() {
				@Override
				public void updateItem(Participants item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}
			};
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				ParticipantsList1.requestFocus();
				if (!cell.isEmpty()) {
					int index = cell.getIndex();
					if (selectionModel.getSelectedIndices().contains(index)) {
						selectionModel.clearSelection(index);
					} else {
						selectionModel.select(index);
					}
					event.consume();
					}
			});
			return cell;
		});
		buttonMainMenu.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene2);

		});
		gameList.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		            sportType=new_val;
		    });
			
		buttonStartGame.setOnAction(e -> {
			
			ObservableList<Participants> OBSelectedAthlete = ParticipantsList1.getSelectionModel().getSelectedItems();
			ObservableList<Participants> OBSelectedOfficial = ParticipantsList2.getSelectionModel().getSelectedItems();

			try {
				ChoosenAthletes = new ArrayList<>();
				ChoosenOfficaial = null;
				for(Participants athlete : OBSelectedAthlete){
					switch(sportType){
					case Driver.swimming:
						if(athlete instanceof Swimmer || athlete instanceof SuperAthlete)
							ChoosenAthletes.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					case Driver.cycling:
						if(athlete instanceof Cyclist || athlete instanceof SuperAthlete)
							ChoosenAthletes.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					case Driver.running:
						if(athlete instanceof Sprinter || athlete instanceof SuperAthlete)
							ChoosenAthletes.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					}	
				}
				for(Participants official : OBSelectedOfficial){
					if(official instanceof Official){
						ChoosenOfficaial = ((Official)official);
					}
					else 
						throw new WrongTypeException();					
				}
				if (ChoosenAthletes.size() <= NotEnoughAthleteException)
					throw new TooFewAthleteException();
				else if (ChoosenAthletes.size() >= TooMuchAthleteException)
					throw new GameFullException();
				else if (ChoosenOfficaial == null)
					throw new NoRefereeException();
				
				
				driver.startgame(sportType, ChoosenAthletes, ChoosenOfficaial);
				window.setTitle("Game Result");
				window.setScene(scene5);
				gameList.getSelectionModel().clearSelection();
				ParticipantsList1.getSelectionModel().clearSelection();
				ParticipantsList2.getSelectionModel().clearSelection();
				
			} catch (TooFewAthleteException e1) {
				title2.setText("TooFewAthleteException");
			} catch (GameFullException e2) {
				title2.setText("GameFullException");
			} catch (NoRefereeException e3) {
				title2.setText("NoRefereeException");
			} catch (WrongTypeException e1){
				title2.setText("Wrong type exception");
			} catch (Exception e2){
				title2.setText("Please select a game type");
			}				
			

	
			
			ArrayList<String> result = driver.getresult();
			ObservableList<String> resultObservableList = FXCollections.observableArrayList(result);
			resultList.setItems(resultObservableList);
		});
		buttonExit3.setOnAction(e -> {			
			window.close();		
		});	
		
		buttonMainMenu2.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene2);
		});
		buttonExit4.setOnAction(e -> {			
			window.close();		
		});	
		
		buttonMainMenu3.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene2);
		});
		buttonExit5.setOnAction(e -> {			
			window.close();		
		});	
		
		buttonMainMenu4.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene2);
		});
		buttonExit6.setOnAction(e -> {			
			window.close();		
		});	
	}
	
}
