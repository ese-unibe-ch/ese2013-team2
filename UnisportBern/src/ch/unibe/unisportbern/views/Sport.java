package ch.unibe.unisportbern.views;


public class Sport {
	private int id;
	private String name;
	private Course[] courses;


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

	public Course[] getCourses(){
		return courses;
	}


}