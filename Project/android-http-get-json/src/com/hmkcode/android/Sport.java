package com.hmkcode.android;

public class Sport {
	private int id;
	private String name;


	public Sport(int id, String name){
		this.id = id;
		this.name = name;
	} 
	
	public void setID(int id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}



}
