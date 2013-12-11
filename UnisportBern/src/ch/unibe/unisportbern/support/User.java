package ch.unibe.unisportbern.support;

/**
 * is responsible for displaying a user of this application.
 * 
 * @author Karan Sethi
 */

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
