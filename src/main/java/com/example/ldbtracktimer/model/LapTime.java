package com.example.ldbtracktimer.model;

import com.example.ldbtracktimer.settings.AppTexts;

public class LapTime {
	
	//region 0. Konstanten
	protected static final String DEF_VALUE_STR = ">noValueSetYet<";
	protected static final int DEF_VALUE_INT = -1;
	//endregion
	
	//region 1. Decl and Init Attribute
	private String strTrackName;
	
	private String strDriverName;
	private String strLapTime;
	private int iId;
	//endregion

	//region 2. Konstruktoren
	/**
	 * Standardkonstruktor zum Initialisieren der Attribute mit den Standardwerten
	 */
	public LapTime() {
		this.iId = DEF_VALUE_INT;
		this.strTrackName = DEF_VALUE_STR;
		this.strDriverName = DEF_VALUE_STR;
		this.strLapTime = DEF_VALUE_STR;
	}
	
	
	/**
	 * 2. Konstruktor zum direkt setzen des Rennstreckennamens, des Fahrernamens und der Rundenzeit bei der Objektinstanziierung
	 * @param strTrackName :{@link  String} : Rennstreckenname
	 * @param strDriverName :{@link  String} : Fahrername
	 * @param strLapTime :{@link  String} : Rundenzeit
	 */
	public LapTime(String strTrackName, String strDriverName, String strLapTime) {
		this.strTrackName = strTrackName;
		this.strDriverName = strDriverName;
		this.strLapTime = strLapTime;
	}
	
	
	
	/**
	 * 3. Konstruktor zum direkt setzen des Rennstreckennamens, der Startnummer und des Fahrernamens bei der Objektinstanziierung
	 * @param iId : int : : DbId
	 * @param strTrackName :{@link  String} : Rennstreckenname
	 * @param strDriverName :{@link  String} : Fahrername
	 * @param strLapTime :{@link  String} : Rundenzeit
	 */
	public LapTime(int iId, String strTrackName, String strDriverName, String strLapTime) {
		this.iId = iId;
		this.strTrackName = strTrackName;
		this.strDriverName = strDriverName;
		this.strLapTime = strLapTime;
	}
	//endregion
	
	//region 3. Getter and Setter
	
	public String getStrTrackName() {
		return strTrackName;
	}
	
	public void setStrTrackName(String strTrackName) {
		this.strTrackName = strTrackName;
	}
	
	
	public String getStrDriverName() {
		return strDriverName;
	}
	
	public void setStrDriverName(String strDriverName) {
		this.strDriverName = strDriverName;
	}
	
	public String getStrLapTime() {
		return strLapTime;
	}
	
	public void setStrLapTime(String strLapTime) {
		this.strLapTime = strLapTime;
	}
	
	public int getiId() {
		return iId;
	}
	
	public void setiId(int iId) {
		this.iId = iId;
	}
	
	
	//endregion
	
	//region 4 BeautifulGuiText
	/**
	 * Gibt einen schoen formatierten Text zur Anzeige bestimmter Attribute auf der Gui zurück
	 *
	 * @return strBeautifulGuiText : {@link String} : Optimierter Gui-Text
	 */
	public String getBeautifulGuiText() {
		
		String strLapInformation =
				AppTexts.ROUND + this.getiId() + AppTexts.TWO_TAPS_LINE_TWO_TAPS + AppTexts.RACETRACK + this.getStrTrackName() + AppTexts.TWO_TAPS_LINE_TWO_TAPS  + AppTexts.DRIVERNAME + this.getStrDriverName() +
		AppTexts.TWO_TAPS_LINE_TWO_TAPS + AppTexts.LAPTIME + this.getStrLapTime();
		
		return strLapInformation;
	}
	//endregion
	
	//region 5 toString
	
	@Override
	public String toString() {
		return "LapTime{" +
				"strTrackName='" + strTrackName + '\'' +
				", strDriverName='" + strDriverName + '\'' +
				", strLapTime='" + strLapTime + '\'' +
				", iId=" + iId +
				'}';
	}
	//endregion
}


	


