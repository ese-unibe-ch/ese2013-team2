package com.hmkcode.android;

import java.util.ArrayList;

public class GetJson {

	private ArrayList<Sport> allSports;
	private JsonNameId getNameId;

	public GetJson() {
		getNameId = new JsonNameId();
	}

	/**
	 * performs all json related actions to retrieve data form server.
	 * 
	 * @return returns a list of all Sports
	 */
	public ArrayList<Sport> execute() {
		getNameId.execute("http://scg.unibe.ch/ese/unisport/sports.php");

		allSports = getNameId.getAllSportsIdName();		
		
		return allSports;
		
	}
	
	public ArrayList<Sport> getSports(){
		return allSports;
	}

}
