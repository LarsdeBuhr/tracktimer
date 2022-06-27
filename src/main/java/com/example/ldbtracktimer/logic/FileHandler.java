package com.example.ldbtracktimer.logic;

import com.example.ldbtracktimer.model.LapTime;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Die Klasse speichert die Passwort-Daten des Programms in einer CSV-Datei
 * Klasse ist ein Singleton und stellt somit einen threadsicheren Dateizugriff zur Verfuegung
 */
public class FileHandler {
	
	//region 0 Konstanten
	private static final String CSV_FILE_PATH          = "src/main/java/com/example/ldbtracktimer/resource/passwordFile.csv";
	//endregion
	
	//region 1 Decl and Init Attributes
	private static FileHandler onlyInstance;
	//endregion
	
	
	//region 2 Konstruktor
	/**
	 * Privater Kosntruktor, damit von außen keine Instanz dieser Klasse erzeuget werden kann
	 * Der Konstruktor kann nur über die Funktion getInstance() aufgerufen werden
	 */
	private FileHandler(){
	}
	//endregion
	
	//region 3 getInstance
	
	/**
	 * Funktion zur Gewährleistung einer synchronisierten, thread- und zugriffssicheren Nutzung der Klasse
	 *
	 * @return: instance:{@link FileHandler}: Einzige Instanz zur Laufzeit
	 */
	public static synchronized FileHandler getInstance() {
		if (onlyInstance == null) {
			onlyInstance = new FileHandler();
		}
		return onlyInstance;
	}
	//endregion
	
	//region 4 Save File
	/**
	 * Speichert die uebergebene Liste {@List} von {@Guest} in eine CSV Datei
	 * @param  : {@link List} {@link } : die gespeichert werden sollen
	 */
	public void saveToCsvFile(String password) {
		File               csvFile = new File(CSV_FILE_PATH);
		FileOutputStream   fos     = null;
		OutputStreamWriter osw     = null;
		BufferedWriter     out     = null;
		
		try {
			fos = new FileOutputStream(csvFile);
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			out = new BufferedWriter(osw);
			
			out.write(password + "\n");
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	/**
	 * Liest die Datei {@List CsvFileHandler#CSV_FILE_PATH}
	 *
	 * @return passwordFromFile : {@String} : Passwort aus der Datei
	 */
	public String readFromCsvFile() {
		String passwordFromFile = null;
		
		File              csvFile = new File(CSV_FILE_PATH);
		FileInputStream   fis     = null;
		InputStreamReader isr     = null;
		BufferedReader    in      = null;
		
		try {
			if (csvFile.exists()) {
				
				fis = new FileInputStream(csvFile);
				isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				in = new BufferedReader(isr);
				
				boolean eof = false;
				
				while (!eof) {
					String strReadCsvLine = in.readLine();
					
					if (strReadCsvLine == null) {
						eof = true;
					} else {
						passwordFromFile = strReadCsvLine;
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return passwordFromFile;
	}
	
	//endregion
}
