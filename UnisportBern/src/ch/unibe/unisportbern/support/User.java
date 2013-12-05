package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import com.parse.ParseException;

import android.content.Context;

import ch.unibe.unisportbern.parse.ParseMethodes;

public class User {
	
	private String username;
	private ArrayList<User> friends;
	private ParseMethodes parse;
	private Context context;
	
	public User(String username, Context context){
		
		this.username = username;
		this.friends = new ArrayList<User>();
		this.context = context;
		this.parse = new ParseMethodes(context);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void fetchFriends(){
		friends = parse.getFriends();
	}
	
	public ArrayList<User> getFriends(){
		return friends;
	}
	
	public void addFriend(String friend, String username){
		parse.addFriend(friend, username);	
	}
	
	public ArrayList<Course> getFriendsFavourites(String username){
		return parse.getFriendsFavorites(username);
	}
	
	public void addFavourites(Course favourite, String myUsername){
		parse.addFavourites(favourite, myUsername);
	}
	
	public User searchOtherUser(String otherUser){
		return parse.searchUser(otherUser);
	}
	
	public String toString (){
		return this.username;
	}
}
