package ch.unibe.unisportbern.support;

public class Sport {
	private int id;
	private String name;

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
}