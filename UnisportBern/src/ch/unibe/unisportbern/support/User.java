package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import com.parse.ParseException;

import android.content.Context;

import ch.unibe.unisportbern.parse.ParseMethodes;

public class User {
	
	private String username;
	private String password;
	private ArrayList<User> friends;
	private ArrayList<Course> favourites;
	private ParseMethodes parse;
	private Context context;
	
	public User(String username, String password, Context context){
		
		this.username = username;
		this.password = password;
		this.friends = new ArrayList<User>();
		this.favourites = new ArrayList<Course>();
		this.context = context;
		this.parse = new ParseMethodes(context);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public ArrayList<User> getFriends(){
		return friends;
	}
	
	public void addFriend(){
		
	}
	
	public ArrayList<Course> getFriendsFavourites(){
		
		return favourites;
	}
	
	public void updateOwnFavourites(){
		try {
			parse.updateFavorites();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> searchOtherUser(String otherUser){
		return new ArrayList<User>();
	}
}
