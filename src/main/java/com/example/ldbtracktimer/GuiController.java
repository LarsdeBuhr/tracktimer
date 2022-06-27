package com.example.ldbtracktimer;

import com.example.ldbtracktimer.database.DbManager;
import com.example.ldbtracktimer.listview.ListViewItemCellFactoryCallback;
import com.example.ldbtracktimer.logic.FileHandler;
import com.example.ldbtracktimer.model.LapTime;
import com.example.ldbtracktimer.settings.AppTexts;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuiController implements Initializable {
	//region 1 Attribute
	@FXML
	public PasswordField txtPassword;
	@FXML
	public TitledPane    tpLaptimes;
	@FXML
	public Button btnLogIn;
	@FXML
	public TitledPane lpNewPassword;
	@FXML
	public TextField newPassword;
	@FXML
	public TextField txtRacetrack;
	@FXML
	public TextField txtDrivername;
	@FXML
	public TextField txtLaptime;
	@FXML
	public ListView lvLaptimes;
	@FXML
	public Button btnDeleteAllLapTimes;
	@FXML
	public Button btnDeleteData;
	@FXML
	public TextField txtIdToDelete;
	@FXML
	public Button btnChangeData;
	@FXML
	public TextField txtIdToChange;
	@FXML
	public TextField txtRacetrackToChange;
	@FXML
	public TextField txtDrivernameToChange;
	@FXML
	public TextField txtLaptimeToChange;
	@FXML
	public TitledPane tpChangeData;
	@FXML
	public Button btnStartTimer;
	@FXML
	public Button btnStopTimer;
	@FXML
	public Button btnClearDataFields;
	
	private ListViewItemCellFactoryCallback listViewItemCellFactoryCallback;
	private List<LapTime> lapTimeList;
	private int iTime;
	Timeline timeline;
	//endregion
	
	//region 2. Konstruktor und Init
	
	/**
	 * GUI initialisieren
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.logOut();
		this.lapTimeList = DbManager.getInstance().getAllLapTimesFromDb();
		this.updateListView();
		this.timer();
	}
	//endregion
	
	//region 3. Log-In and Log-Out
	
	/**
	 * Login Bereich
	 * Login Daten werden mit dem Inhalt der CSV Datei abgeglichen
	 * Nach erfolgreichem Login werden die anderen Bereiche des Programms sichtbar
	 * Dabei ist jedoch Das Feld zum Eingeben eines neuen Passwortes sowie der Bereich zm Ändern von Datensätzen eingeklappt
	 */
	@FXML
	private void logIn(){
		String strInputtedPassword = this.txtPassword.getText();
		String strSavedPassword = FileHandler.getInstance().readFromCsvFile();
		
		if(strInputtedPassword.equals(strSavedPassword)){
			this.tpLaptimes.setVisible(true);
			this.lpNewPassword.setVisible(true);
			this.lpNewPassword.setExpanded(false);
			this.tpChangeData.setVisible(true);
			this.tpChangeData.setExpanded(false);
		}
	}
	
	/**
	 * LogOut Bereich
	 * Nach dem Betätigen des Logout Buttons werden alle Bereiche bis auf die Anmeldemaske wieder ausgeblendet
	 */
	public void logOut() {
		this.tpLaptimes.setVisible(false);
		this.lpNewPassword.setVisible(false);
		this.tpChangeData.setVisible(false);
		
	}
	
	/**
	 * Ein neues Passwort wird in die CSV Datei geschrieben.
	 * Dies ist nur möglich wenn man sich zuvor korrekt angemeldet hat
	 */
	public void saveNewPassword() {
		FileHandler.getInstance().saveToCsvFile(this.newPassword.getText());
	}
	//endregion
	
	/**
	 * Befüllen der ListView
	 */
	//region 4. CRUD Listdata
	private void updateListView(){
		//Generiert den CallBack welcher die einzelenen Items/Zellen generiert
		this.listViewItemCellFactoryCallback = new ListViewItemCellFactoryCallback();
		
		//Drinks in Observable List packen und ListView anhaengen
		ObservableList<LapTime> lapTimeObservableList = FXCollections.observableList(this.lapTimeList);
		
		//Neue aktualisierte Liste der ListView uebergeben
		this.lvLaptimes.setItems(lapTimeObservableList);
		
		/*
		 * Die CellFactory bestimmt das ausseh der einzelen ListViewItems
		 * aehnlich wie in Android der Adapter.
		 */
		this.lvLaptimes.setCellFactory(this.listViewItemCellFactoryCallback);
	}
	
	/**
	 * Eine neue Rundenzeit wird via LapTime Model erzeugt und der lapTimeList sowie der Datenbank hinzugefügt
	 * Das Textfeld mit der Zeit wird nach dem Hinzufügen auf einen Nullwert zurückgesetzt
	 */
	@FXML
	private void insertLapTime(){
		
		if(txtRacetrack.getText().isEmpty() || txtDrivername.getText().isEmpty() || txtLaptime.getText().isEmpty()) {
			return;
		}else {
			
			LapTime newLapTime =
					new LapTime(txtRacetrack.getText(), txtDrivername.getText(), txtLaptime.getText());
			
			this.lapTimeList.add(newLapTime);
			
			DbManager.getInstance().insertLapTimeIntoDbTbl(newLapTime);
			
			this.loadAllDatas();
			
			this.txtLaptime.setText(String.format("%02d:%02d:%02d", 0,0,0));
			this.iTime = 0;
			
		}
	}
	
	/**
	 * Alle Eingabe-Datenfelder werden geleert
	 */
	public void clearDataFields() {
		this.txtRacetrack.setText(null);
		this.txtDrivername.setText(null);
		this.txtLaptime.setText(null);
	}
	
	/**
	 * Alle vorhandenen Daten werden aus der Datenbank geladen und in der ListView angezeigt
	 */
	public void loadAllDatas() {
		this.lapTimeList = DbManager.getInstance().getAllLapTimesFromDb();
		this.updateListView();
	}
	
	/**
	 * Alle Daten der ListView werden in der Datenbank gelöscht
	 * Danach wird die Anzeige aktualisiert, indem sich einmal der neue Datensatz von der Datenbank geladen wird
	 */
	public void deleteAllLapTimes() {
		
		DbManager.getInstance().deleteAllLaptimesInDbTbl();
		this.loadAllDatas();
	}
	
	/**
	 * Es wird ein durch den User ausgewählter Datensatz in der Datenbank gelöscht.
	 * Danach wird die Anzeige aktualisiert, indem sich einmal der neue Datensatz von der Datenbank geladen wird
	 */
	public void deleteData() {
		DbManager.getInstance().deleteLaptimeInDbTblById((Integer.parseInt(txtIdToDelete.getText())));
		this.loadAllDatas();
	}
	
	/**
	 * Daten können anhand ihrer ID ausgewählt und editiert werden. Die Änderungen werden sofort in der Datenbank durchgeführt
	 * Danach wird die Anzeige aktualisiert, indem sich einmal der neue Datensatz von der Datenbank geladen wird
	 */
	public void changeData() {
		
		LapTime laptimeToUpdate = new LapTime(Integer.parseInt(txtIdToChange.getText()), txtRacetrackToChange.getText(), txtDrivernameToChange.getText(), txtLaptimeToChange.getText());
		DbManager.getInstance().updateLapTimeInDbTbl(laptimeToUpdate);
		this.loadAllDatas();
		
	}
	
	/**
	 * Optionale Stoppuhrfunktion im Programm
	 * Ausgabe in Minuten:Sekunden:Hundertstel Sekunden
	 *
	 */
	public void timer(){
		//this.txtLaptime.setText(String.valueOf(iTime));
		
		this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			iTime++;
			
			this.txtLaptime.setText(String.format(AppTexts.TIMER_FORMAT, (iTime % 360000)/6000, (iTime % 6000)/100, iTime % 100));
		}));
		
		this.timeline.setCycleCount(Animation.INDEFINITE);
		
	}
	
	/**
	 * Startet die Stoppuhr Funktion
	 */
	public void startTimer( ) {
		this.timeline.play();
	}
	
	/**
	 * Stoppt die Stoppuhrfunktion
	 */
	public void stopTimer( ) {
		this.timeline.stop();
	}
	//endregion
}