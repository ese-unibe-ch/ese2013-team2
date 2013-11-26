package ch.unibe.unisportbern.parse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.net.ParseException;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.User;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseAnalytics;

public class ParseMethodes {
	ParseUser user = new ParseUser();
	//ParseObject userdata = new ParseObject("Userdata");
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
		
		ParseUser.logInInBackground(dbuser.getUsername(), dbuser.getPassword(), new LogInCallback() {

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
	
	public void updateFavorites() throws com.parse.ParseException{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Userdata");
		
		// Retrieve the object by id
		query.getInBackground(user.getObjectId(), new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject userdata, com.parse.ParseException e) {
				// TODO Auto-generated method stub
                    if (e == null) {
					ArrayList <Course> allFav = db.getAllFavorites();
					ArrayList <Integer> cids = new ArrayList <Integer>();
					for(int i = 0; i < allFav.size(); i++){
						cids.add(allFav.get(i).getId());	
					}
					userdata.put("favourites", cids);
					userdata.saveInBackground();
					}
				
			}
			
		});		
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
	
	public void addFriend(User friend){
		String username = friend.getUsername();
		user.put("friends", username);
	}
	
	public ArrayList<User> getFriends(){
		ArrayList<User> friends = new ArrayList<User>();
		ArrayList<String> friendsUser = convertToArrayList(user.getJSONArray("friends"));
		for(int i=0; i<friendsUser.size(); i++){
		    friends.add(this.getParseUsers(friendsUser.get(i)).get(i));
		}
		
		return friends;
	}
	
	private User makeUser(ParseUser object){
		return dbuser = new User(object.getString("username"), object.getString("password"), context);
	}
	
	private ArrayList<String> convertToArrayList(JSONArray array){
		ArrayList<String> list = new ArrayList<String>();
		if (array!=null){
			for (int i = 0; i<array.length();i++){
				try {
					list.add(array.get(i).toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	private ArrayList <User> getParseUsers(String friendUser){
		
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		final ArrayList<User> userList = new ArrayList<User>();
		query.whereEqualTo("friends", friendUser);
		query.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> objects, com.parse.ParseException e) {
				
				if (e == null) {
					
					for (int i=0; i<objects.size(); i++){
						userList.add(makeUser(objects.get(i)));
					}
			        // The query was successful.
			    } else {
			        // Something went wrong.
			    }
				
			}
		  
		    
	

		});
		return userList;
	}


}

