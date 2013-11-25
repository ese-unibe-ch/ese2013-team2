package ch.unibe.unisportbern.parse;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.ParseException;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.User;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseAnalytics;

public class ParseMethodes {
	ParseUser user = new ParseUser();
	ParseObject userdata = new ParseObject("Userdata");
	User dbuser;
	DBMethodes db;
	ArrayList<String> friends = new ArrayList();
	Context context;
	
	public ParseMethodes(Context context){
		this.context = context;
		 Parse.initialize(context, "p2hZnDFwdzTSLTkPD9t1rWyosBUkJessBArNyAAJ", "vU9rXONSs6QTJYmk38wzHzhrjMGktFXmCf6rsmpM");
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
	
	public void loggingIn(){
		
		user.logInInBackground(dbuser.getUsername(), dbuser.getPassword(), new LogInCallback() {
			  public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      // Hooray! The user is logged in.
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    }
			  }

			@Override
			public void done(ParseUser user, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				
			}
			});
	}
	
	public String getUsername(){
		return null;
	}
	
	public void updateFavorites() throws com.parse.ParseException{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Userdata");
		
		// Retrieve the object by id
		/*query.getInBackground(, new GetCallback<ParseObject>() {
		  public void done(ParseObject userdata, ParseException e) {
		    if (e == null) {
		
		ArrayList <Course> allFav = db.getAllFavorites();
		ArrayList <Integer> cids = new ArrayList <Integer>();
		for(int i = 0; i < allFav.size(); i++){
			cids.add(allFav.get(i).getId());	
		}
		userdata.put("favourites", cids);
		userdata.save();
		    }
		  }
		});*/
	}
	
	public void searchByUsername(String username){
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", username);
		query.findInBackground(new FindCallback<ParseUser>() {
		  public void done(List<ParseUser> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    } else {
		        // Something went wrong.
		    }
		  }

		@Override
		public void done(List<ParseUser> arg0, com.parse.ParseException arg1) {
			// TODO Auto-generated method stub
			
		}
		});
		
	}
	

	
	public void addFriend(ParseUser user){
		this.user = user;
		String username = user.getUsername();
		friends.add(username);
		userdata.put("Friends", friends);
	}
	
	public ArrayList<String> getAllFriends(){
		return friends;
	}

	


}

