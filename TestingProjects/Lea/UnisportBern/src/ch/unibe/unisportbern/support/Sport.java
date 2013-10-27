package ch.unibe.unisportbern.support;

import java.util.ArrayList;

public class Sport {
	private int id;
	private String name;
	private ArrayList<Course> courses;


	public Sport(int id, String name){
		this.id = id;
		this.name = name;
	}


	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}

	public ArrayList<Course> getCourses(){
		return courses;
	}


	public Course getCourse(int c) {
		return courses.get(c);
	}


}
