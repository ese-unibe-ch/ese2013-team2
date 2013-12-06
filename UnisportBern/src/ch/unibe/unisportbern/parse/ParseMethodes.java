package ch.unibe.unisportbern.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.net.ParseException;
import android.os.UserManager;

import ch.unibe.unisportbern.notification.FriendsFavouriteNotification;
import ch.unibe.unisportbern.notification.FriendsNotification;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.User;
import ch.unibe.unisportbern.views.friends.FriendsFragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseAnalytics;

public class ParseMethodes extends Observable {
	ParseUser user = new ParseUser();
	//ParseObject userdata = new ParseObject("Userdata");
	User dbuser;
	private DBMethodes db;
	Context context;
	private ArrayList <Integer> cid;
	private User users;
	private ArrayList<User> usersList = new ArrayList<User>();
	private ArrayList <Course> favoritesList = new ArrayList <Course>();
	
	
	public ParseMethodes(Context context){
		this.context = context;
		 Parse.initialize(context, "p2hZnDFwdzTSLTkPD9t1rWyosBUkJessBArNyAAJ", "vU9rXONSs6QTJYmk38wzHzhrjMGktFXmCf6rsmpM");
		 db = new DBMethodes(context);
	}
	
	public void signingUp(String username, String password){
		
		user.setUsername(username);
		user.setPassword(password);
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
		    if (e == null) {
		      // Hooray! Let them use the app now. Toast?	    	
		    } else {
		      // Sign up didn't succeed. Look at the ParseException
		      // to figure out what went wrong Toast?
		    }
		  }

		@Override
		public void done(com.parse.ParseException e) {
			// TODO Auto-generated method stub
			
		}
		});
	}
	public boolean automaticLogin(){
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
		  // do stuff with the user
			return true;
		} else {
		  // show the signup or login screen
			return false;
		}
	}
	
	
	public void loggingIn(String username, String password ){
		
		ParseUser.logInInBackground(dbuser.getUsername(), password,  new LogInCallback() {

			@Override
			public void done(ParseUser user, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				
				if (user != null) {
				      // Hooray! The user is logged in.
				    } else {
				      // Signup failed. Look at the ParseException to see what happened.
				    }
			}
		});
	}
	public String getUsername(){
		return null;
	}
	
	public void addFavourites(Course favourite, String username){
		ParseObject favourites = new ParseObject ("FAVOURTIES");
		favourites.put("username", username);
		favourites.put("cid", favourite.getId());
		favourites.saveEventually();
	}
	
	public ArrayList <Course> getFriendsFavorites(){
		return favoritesList;
	}
	
	public ArrayList <Course> fillFriendsFavorites(String friendsUsername){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURTIES");
		query.whereEqualTo("username", friendsUsername);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						favoritesList.add(db.getCourse(objects.get(i).getInt("cid")));
			        // The query was successful.
					}
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
			    } else {
			        // Something went wrong.
			      }	
			}
		});
		return favoritesList;
	}
	
	public void deleteFavourite(String myUsername, int cid){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURTIES");
		query.whereEqualTo("username", myUsername);
		query.whereEqualTo("cid", cid);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					objects.get(0).deleteEventually();
			        // The query was successful.
				}
			     else {
			        // Something went wrong.
			     }
			}
			
		});
	}
	
	public void addFriend(String friend, String username){
		ParseObject friends = new ParseObject ("FRIENDS");
		friends.put("username", username );
		friends.put("friendsID", friend);
		friends.put("notification", true);
		friends.saveEventually();
		ParseMethodes.this.setChanged();
		ParseMethodes.this.notifyObservers();
	}
	
	public void deleteFriendNotification(FriendsNotification notification){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("friendsID", notification.getFriend());
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				for (int i = 0 ; i< objects.size(); i++){
					objects.get(i).put("notification", false);
					objects.get(i).saveEventually();
				}
				
				
			}
			
		});
		 
	}
	
	public void deleteFriendsFavouriteNotification(FriendsFavouriteNotification notification){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURTIES");
		query.whereEqualTo("cid", notification.getCourse().getId());
		query.whereEqualTo("friendsID", notification.getFriend());
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				for (int i = 0 ; i< objects.size(); i++){
					objects.get(i).put("notification", false);
					objects.get(i).saveEventually();
				}
				
			}
			
		});
		 
	}
		
	
	public void deleteFriend(String myUsername, String friendUsername){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", myUsername);
		query.whereEqualTo("friendsID" , friendUsername);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					objects.get(0).deleteEventually();
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
			        // The query was successful.
				}
			     else {
			        // Something went wrong.
			     }
			}
			
		});
	}
	
	public ArrayList<User> getUsers(){
		return usersList;
	}
	
	public void fillFriendsList(String myUsername){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", myUsername);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						usersList.add(makeUser(objects.get(i).getString("friendsID")));
						
			        // The query was successful.
					}
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
					
			    } else {
			        // Something went wrong.
			      }	
			}
		});
	}
	
	
	public void orderSearch (String otherUser){
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", otherUser);
		query.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> objects, com.parse.ParseException e) {
				
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						usersList.add(makeUser(objects.get(i).getString("username")));
					}
					setChanged();
					notifyObservers();
			        // The query was successful.
					
			    } else {
			        // Something went wrong.
			    }
				
			}

		});
	}
	
	private User makeUser(String user){
		return new User(user);
	}

	
	


}

