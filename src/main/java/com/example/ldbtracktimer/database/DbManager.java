package com.example.ldbtracktimer.database;

import com.example.ldbtracktimer.model.LapTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Threadsicher Zugriff auf die Datenbank
 */
public class DbManager {
	//region 0.Konstanten
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

	private static final String DB_LOCAL_SERVER_IP_ADDRESS = "localhost";
	private static final String DB_LOCAL_NAME              = "/tracktimer";

	private static final String DB_LOCAL_CONNECTION_URL =
			"jdbc:mariadb://" + DB_LOCAL_SERVER_IP_ADDRESS + DB_LOCAL_NAME;

	private static final String DB_LOCAL_USER_NAME = "root";
	private static final String DB_LOCAL_USER_PW   = "";
	
	//endregion

	//region 1. Decl. and Init Attribute
	private static DbManager instance;
	private        DaoLapTime daoLapTime;
	//endregion

	//region 2. Konstruktoren
	/**
	 * Erzeugen einer neuen Instanz von DaoLapTime
	 */
	private DbManager() {
		this.daoLapTime = new DaoLapTime();
	}
	//endregion

	//region 3. Get Instance

	/**
	 * Funktion zur Gewährleistung einer synchronisierten, thread- und zugriffssicheren Nutzung der Klasse
	 * @return instance : DbManager : Einzige Instanz zur Laufzeit
	 */
	public static synchronized DbManager getInstance() {
		if (instance == null) {
			instance = new DbManager();
		}
		return instance;
	}
	//endregion

	//region 4. Database Connection
	/**
	 * Gibt eine generierte Datenbankverbindung mit Lese(r) als auch Schreibrechten(w)
	 * zurück oder null sollte etwas schiefgelaufen sein.
	 * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank mit rw - Rechten
	 */
	private Connection getRwDbConnection() throws Exception {
		Connection rwDbConnection = null;

		try {
			//1: Registeren des JDBC driver
			Class.forName(JDBC_DRIVER);

			//2. Offenen einer Verbindung
			rwDbConnection = DriverManager.getConnection(DB_LOCAL_CONNECTION_URL, DB_LOCAL_USER_NAME, DB_LOCAL_USER_PW);

		} catch (SQLNonTransientConnectionException sqlNoConnectionEx) {
			throw new Exception("Keine Datenbankverbindung");
		} catch (ClassNotFoundException classNotFoundEx) {
			throw new Exception("JDBC Treiber konnte nicht geladen werden");
		}
		return rwDbConnection;
	}
	
	/**
	 * Checkt ob die Datenbank online ist oder nicht
	 * @return isOnline : boolean : true : Datenbank ist online : false nicht
	 */
	public boolean isDatabaseOnline() {
		boolean isOnline = true;
		try {
			this.getRwDbConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
			isOnline = false;
		}
		return isOnline;
	}
	//endregion


	//region 5. CRUD -Opeations User
	/**
	 * CREATE
	 * Einfügen eines einzelnen {@link com.example.ldbtracktimer.model.LapTime}s in die Datenbank
	 * @param timeToInsert : {@link com.example.ldbtracktimer.model.LapTime} : Zum Einfügen in die Datenbank
	 */
	public void insertLapTimeIntoDbTbl(LapTime timeToInsert) {

		try {
			if (this.isDatabaseOnline()) {
				//Neue Verbindung erstellen
				this.daoLapTime.insertDataRecordIntoDbTbl(this.getRwDbConnection(), timeToInsert);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	
	/**
	 * READ
	 * Liest alle Daten aus der Datenbanktabelle aus
	 * @return allLapTimesFromDb : {@link List} - {@link LapTime}: Alle Daten aus der Datenbank-Tabelle
	 */
	public List<LapTime> getAllLapTimesFromDb() {
		//Neue Verbindung erstellen
		List<LapTime> allLapTimesFromDb = new ArrayList<>();

		try {
			if (this.isDatabaseOnline()) {
				allLapTimesFromDb = this.daoLapTime.getAllDataRecordsFromDbTbl(this.getRwDbConnection());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return allLapTimesFromDb;
	}


	/**
	 * UPDATE
	 * Ändert die Werte der Rundenzeiten in der Datenbank ab
	 * @param lapTimeToUpdate : {@link LapTime} : Rundendaten die geändert werden sollen.
	 */
	public void updateLapTimeInDbTbl(LapTime lapTimeToUpdate) {
		try {
			if (this.isDatabaseOnline()) {
				//Neue Verbindung erstellen
				this.daoLapTime.updateDataRecordIntoDbTbl(this.getRwDbConnection(), lapTimeToUpdate);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	
	/**
	 * DALETE
	 * Loescht den {@link LapTime} mit der übergenen ID aus der Datenbanktabelle
	 * @param iId : int : Id der {@link LapTime} die geloescht werden soll
	 */
	public void deleteLaptimeInDbTblById(int iId) {
		try {
			if (this.isDatabaseOnline()) {
				//Neue Verbindung erstellen
				this.daoLapTime.deleteDataRecordInDbTblById(this.getRwDbConnection(), iId);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * DELETE
	 * Löscht alle Daten aus der Datenbanktabelle
	 */
	public void deleteAllLaptimesInDbTbl() {
		try {
			if (this.isDatabaseOnline()) {
				//Neue Verbindung erstellen
				this.daoLapTime.deleteAllDataRecordsInDbTbl(this.getRwDbConnection());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//endregion
}
