package com.example.ldbtracktimer.database;



import com.example.ldbtracktimer.model.LapTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Diese Klasse ist eine DAO-Klasse.
 * DAO seht für Data Access Object.
 * Führt die CRUD Operationen auf der Datenbanktabelle aus
 */
public class DaoLapTime extends ASqlKeyWords{
	//region 0 Konstanten
	/**
	 * Name der Datenbanktabelle: laptimes
	 * Namen der Spalten der Datenbanktabelle: _id, track, driver, laptime
	 * _id ist Primärschlüssel
	 */
	protected static final String COL_NAME_ID                    = "_id";
	protected static final String COL_NAME_ID_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK + COL_NAME_ID + CHAR_COL_BACK_TICK;

	protected static final String COL_NAME_TRACK                   = "track";
	protected static final String COL_NAME_TRACK_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK + COL_NAME_TRACK + CHAR_COL_BACK_TICK;
	
	
	protected static final String COL_NAME_DRIVER                   = "driver";
	protected static final String COL_NAME_DRIVER_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK + COL_NAME_DRIVER + CHAR_COL_BACK_TICK;
	
	protected static final String COL_NAME_LAPTIME                  = "laptime";
	protected static final String COL_NAME_LAPTIME_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK + COL_NAME_LAPTIME + CHAR_COL_BACK_TICK;

	private static final String TABLE_NAME = "laptimes";
	//endregion


	//region 1 Datensatz hinzufügen

	/**
	 * Fuegt einen einzelnen Datensatz in die Datebanktabelle ein
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib- und Lesezugriff
	 * @param laptimeToInsertIntoDbTable : {@link LapTime} : Model welches eingefügt werden soll
	 */
	public void insertDataRecordIntoDbTbl(Connection dbRwConnection, LapTime laptimeToInsertIntoDbTable) {

		//Decl and Init
		Statement dbStatementToExecute = null;

		try {
			//1. Db Connection ist bereits von DbManger generiert
			//2. Statementobjekt zum tatsaechlichen ausfuehren des unten als String generierten SQL-Statements
			dbStatementToExecute = dbRwConnection.createStatement();

			//3. SQL-String angepasst auf die Tabelle generieren
			/*
			 * INSERT INTO laptimes(
			 *      `track`,
			 *      `driver`,
			 *      `laptime`
			 * )
			 *  VALUES (
			 *      'Nuerburgring',
			 *      'Mustermann',
			 *      '1:25:12'
			 * );
			 *
			 */
			String strSqlStmtInsert = INSERT_TBL + TABLE_NAME +
					CHAR_OPEN_PARENTHESIS
					+ COL_NAME_TRACK_INC_COL_BACK_TICKS + CHAR_COMMA
					+ COL_NAME_DRIVER_INC_COL_BACK_TICKS + CHAR_COMMA
					+ COL_NAME_LAPTIME_INC_COL_BACK_TICKS
					+ CHAR_CLOSE_PARENTHESIS
					+ VALUES_OPERATOR
					+ CHAR_OPEN_PARENTHESIS
					+ CHAR_VALUE_BACK_TICK + laptimeToInsertIntoDbTable.getStrTrackName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
					+ CHAR_VALUE_BACK_TICK + laptimeToInsertIntoDbTable.getStrDriverName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
					+ CHAR_VALUE_BACK_TICK + laptimeToInsertIntoDbTable.getStrLapTime() + CHAR_VALUE_BACK_TICK
					+ CHAR_CLOSE_PARENTHESIS_SEMICOLON;

	
			//4. SQL - String an Statement objekt zum Ausführen geben
			dbStatementToExecute.execute(strSqlStmtInsert);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}

			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	//endregion

	//region 2 Datensatz abändern
	/**
	 * Aendert einen einzelen Datensatz in der Datebanktabelle
	 *
	 * @param dbRwConnection         : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param laptimeToUpdateInDbTable : {@link LapTime} : Model welches geaendert werden soll
	 */
	public void updateDataRecordIntoDbTbl(Connection dbRwConnection, LapTime laptimeToUpdateInDbTable) {

		Statement dbStatementToExecute = null;
		try {
			//1. Db Connection ist bereits vom DbManager geoeffnet wordem
			//2. Statementobjek generieren lassen
			dbStatementToExecute = dbRwConnection.createStatement();

			/*
			 * UPDATE `laptimes`
			 * SET
			 * `track`= 'Spa'
			 * `driver`= 'Musterfrau'
			 * `laptime`= '2:22:22'
			 * WHERE `_id` = 5
			 */
			String strSqlStmtUpdate = UPDATE_TBL + TABLE_NAME
					+ SET_OPERATOR
					
					+ COL_NAME_TRACK_INC_COL_BACK_TICKS
					+ EQUALS_OPERATOR
					+ CHAR_VALUE_BACK_TICK + laptimeToUpdateInDbTable.getStrTrackName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
					
					+ COL_NAME_DRIVER_INC_COL_BACK_TICKS
					+ EQUALS_OPERATOR
					+ CHAR_VALUE_BACK_TICK + laptimeToUpdateInDbTable.getStrDriverName() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
					
					+ COL_NAME_LAPTIME_INC_COL_BACK_TICKS
					+ EQUALS_OPERATOR
					+ CHAR_VALUE_BACK_TICK + laptimeToUpdateInDbTable.getStrLapTime() + CHAR_VALUE_BACK_TICK
					
					+ WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + laptimeToUpdateInDbTable.getiId();
			

			//4. SQL - String an Statement objekt zum ausfuerhren geben
			dbStatementToExecute.executeUpdate(strSqlStmtUpdate);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}

			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	//endregion


	//region 3 Datensätze lesen

	/**
	 * Gibt alle Datensaetze einer Datenbanktabelle als {@link List} zurueck und sortiert diese absteigend
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link LapTime} : Liste aller Datensaetze
	 */
	public List<LapTime> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {

		//Decl. and Init
		List<LapTime> allLaptimesFromDbTable = new ArrayList<>();

		Statement dbStatementToExecute = null;

		try {
			//1. Rw Db Connection ist bereits vom DbManger geöffnet und integriert
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();

			//3. Query generieren und absetzen und Ergebnismenge merken
			String strSqlStmtGetAll = SELECT_ALL_DATA_FROM + TABLE_NAME + ORDER_BY + COL_NAME_LAPTIME + ASC;
			
			ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAll);

			//4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
			while (resultSetFromExecutedQuery.next()) {

				//5. Aus der Ergebenismenge einen Eintrag beschafften
				LapTime lapTimeFromDbTable = this.getModelFromResultSet(resultSetFromExecutedQuery);

				//6. Modelobjekt zur passenden Liste adden
				allLaptimesFromDbTable.add(lapTimeFromDbTable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}

			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}

		return allLaptimesFromDbTable;
	}
	
	//endregion


	//region 4 Datensatz löschen

	/**
	 * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Id des Objektes welches in der Datenbanktabelle gelöscht werden soll
	 */
	public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
		Statement dbStatementToExecute = null;

		try {
			//1 Db Connection bereits vom DbManager geoeffent
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();

			/*
			 * 3. Statement generieren
			 * String strSqlDeleteUserById = "DELETE FROM `laptimes` WHERE `_id` = " + iId;
			 */

			String strSqlDeleteUserById = ASqlKeyWords.DELETE_FROM_TBL + TABLE_NAME + WHERE_CONDITION
					+ COL_NAME_ID_INC_COL_BACK_TICKS
					+ ASqlKeyWords.EQUALS_OPERATOR + iId;

			dbStatementToExecute.executeUpdate(strSqlDeleteUserById);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}

			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * Loescht alle Datensätze aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 */
	
	public void deleteAllDataRecordsInDbTbl(Connection dbRwConnection) {
		Statement dbStatementToExecute = null;
		
		try {
			//1 Db Connection bereits vom DbManager geoeffent
			//2. Geneieren des Statenements
			dbStatementToExecute = dbRwConnection.createStatement();
			
			/*
			 * 3. Statement geneireren
			 * String strSqlDeleteUserById = "TRUNCATE TABLE `laptimes`"
			 */
			String strSqlDeleteUserById = TRUNCATE_TABLE + TABLE_NAME;
			
			dbStatementToExecute.executeUpdate(strSqlDeleteUserById);
			
			/*
			 * 4. Autoincrement des Primärschlüssels auf Eins zurücksetzen
			 * String strSqlDeleteUserById = "ALTER TABLE `laptimes` AUTO_INCREMENT = 1"
			 */
			String strAutoincrementReset = ALTER_TBL +  TABLE_NAME + AUTO_INCREMENT_TABLE_BY_ONE;
			dbStatementToExecute.executeUpdate(strAutoincrementReset);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (dbStatementToExecute != null) {
				//5. Schliessen der des Statements
				try {
					dbStatementToExecute.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				//6. Schliessen der Verbindung
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	//endregion

	//region Model aus ResultSet Formen

	/**
	 * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
	 * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
	 * @return LapTime : {@link LapTime} : LapTime Model
	 * @throws Exception
	 */
	protected LapTime getModelFromResultSet(ResultSet currentResultSet) throws Exception {

		//6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auslesen der Daten
		int    iId      = currentResultSet.getInt(COL_NAME_ID);
		String strTrack  = currentResultSet.getString(COL_NAME_TRACK);
		String strDriver  = currentResultSet.getString(COL_NAME_DRIVER);
		String strLapTime  = currentResultSet.getString(COL_NAME_LAPTIME);
		
		//7. Neues Modelobjekt generieren und zurueck geben
		return new LapTime(iId, strTrack, strDriver, strLapTime);
	}
	//endregion
}
