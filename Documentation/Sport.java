
public class Sport {	
	
	private int id;
	private String name;
	private Course[] courses;
	
	public Sport(int id, String name, Course[] courses){
		this.id = id;
		this.name = name;
		this.courses = courses;
	}
	
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public Course[] getCourses(){
		return courses;
	}
	
}
