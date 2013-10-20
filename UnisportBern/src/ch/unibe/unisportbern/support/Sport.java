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


	/**
	 * returns a the course from position i in the list of courses
	 */
	public Course getCourse(int i) {
		return courses.get(i);
	}


}
