package ch.unibe.unisportbern.support;


public class User {
	
	private String username;
	
	public User(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	public String toString(){
		return this.username;
	}
}
