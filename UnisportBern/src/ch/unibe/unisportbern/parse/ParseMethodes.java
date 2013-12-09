package ch.unibe.unisportbern.parse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.net.ParseException;
import android.os.UserManager;

import ch.unibe.unisportbern.notification.FriendsFavouriteNotification;
import ch.unibe.unisportbern.notification.FriendsNotification;
import ch.unibe.unisportbern.notification.INotification;
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

public class ParseMethodes extends Observable implements Comparator<ParseObject> {
	ParseUser user = new ParseUser();
	User dbuser;
	private DBMethodes db;
	Context context;
	private ArrayList<User> usersList = new ArrayList<User>();
	private ArrayList <Course> favoritesList = new ArrayList <Course>();
	private ArrayList <INotification> notificationList = new ArrayList <INotification>();
	private ArrayList <INotification> tmpNotificationList = new ArrayList<INotification>();
	private List <ParseObject> notiobject = new ArrayList<ParseObject>();
	private List <ParseObject> friendsCID = new ArrayList <ParseObject>();
	private List <String> friendsUsername = new ArrayList <String>();
	
	
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
	
	public void addFavourites(Course favourite){
		ParseObject favourites = new ParseObject ("FAVOURITES");
		favourites.put("username", ParseUser.getCurrentUser().getString("username"));
		favourites.put("cid", favourite.getId());
		favourites.saveInBackground();
		addFavouriteNotificationForFriend(favourite.getId());
		
	}
	
	
	private void addFavouriteNotificationForFriend(final int id) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		//query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					for(int i=0; i<objects.size(); i++){
						ParseObject favNoti = new ParseObject ("FAVOURITESNOTIFICATION");
						favNoti.put("cid", id);
						favNoti.put("sender", ParseUser.getCurrentUser().get("username"));
						favNoti.put("receiver", objects.get(i).getString("friendsID"));
						favNoti.put("notification", true);
					    friendsCID.add(i, favNoti);
					}
					ParseObject.saveAllInBackground(friendsCID);
					
						
			        // The query was successful.
				}
			     else {
			        // Something went wrong.
			     }
			}
			
		});
		
	}

	public ArrayList <Course> getFriendsFavorites(){
		return favoritesList;
	}
	
	public ArrayList <Course> fillFriendsFavorites(String friendsUsername){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITES");
		query.whereEqualTo("username", friendsUsername);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						if(objects.get(i).getString("friendsID")==null)
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
	
	public void deleteFavourite(int cid){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITES");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
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
	
	public void addFriend(final String friend, String username) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					for(int i = 0; i<objects.size(); i++){
						friendsUsername.add(objects.get(i).getString("friendsID"));
					}
					if (isFriendAdded(friend)!=true){
						fillFriendRows(friend);
					}
					// The query was successful.
				} else {
					// Something went wrong.
				}
			}

		});
	}
	
	private boolean isFriendAdded(String friend){
		for (int i = 0; i< friendsUsername.size(); i++){
			if(friendsUsername.get(i).equals(friend))
				return true;
		}
		return false;
	}

	
	private void fillFriendRows(String friend){
		ParseObject friends = new ParseObject("FRIENDS");
		friends.put("username", ParseUser.getCurrentUser().getString("username"));
		friends.put("friendsID", friend);
		friends.put("notification", true);
		usersList.add(new User(friend));
		friends.saveInBackground();
		setChanged();
		notifyObservers();
	}
	
	
	
	public void deleteFriend(String myUsername, String friendUsername, final int position) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", myUsername);
		query.whereEqualTo("friendsID", friendUsername);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					objects.get(0).deleteInBackground();
					
					usersList.remove(position);
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
					// The query was successful.
				} else {
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
						if(objects.get(i).getString("friendsID")!=null)
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
		query.whereContains("username", otherUser);
		//query.whereEqualTo("username", otherUser);
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
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITESNOTIFICATION");
		query.whereEqualTo("cid", notification.getCourse().getId());
		query.whereEqualTo("friend", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
					objects.get(0).deleteInBackground();
				
			}
			
		});
		 
	}
	

	public ArrayList <INotification> getNotifications(){
		return notificationList;
	}
	
	public void fillINotificationList(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("notification", true);
		//query.whereEqualTo("friendsID", ParseUser.getCurrentUser().getString("username"));
		query.whereEqualTo("friendsID", "iii");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				for(int i=0; i<objects.size(); i++){
					notiobject.add(objects.get(i));
				}
				fillFriendsList();
				
				
			}
		});
	}
					
			
				
		
	
	protected void fillFriendsList() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		//query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.whereEqualTo("username", "iii");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				for(int i=0; i<objects.size(); i++){
					friendsUsername.add(objects.get(i).getString("friendsID"));
				}
				fillFavouriteNotificatonList();
			}
				
				
			
		});
		
	}

	private void fillFavouriteNotificatonList() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITESNOTIFICATION");
		//query.whereEqualTo("receiver", ParseUser.getCurrentUser().getString("username"));
		query.whereEqualTo("receiver", "iii");
		query.whereEqualTo("notification", true);
		query.whereContainedIn("sender", friendsUsername);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				for(int i=0; i<objects.size(); i++){
					notiobject.add(objects.get(i));
				}
				makeNotification();
				setChanged();
				notifyObservers();
				
				}
				
				
			
		});
	}
	
	private void makeNotification(){
		for(int i=0; i<notiobject.size(); i++){
			if(notiobject.get(i).getString("friendsID")==null){
				notificationList.add(new FriendsFavouriteNotification(makeUser(notiobject.get(i).getString("sender")), db.getCourse(notiobject.get(i).getInt("cid"))));
			}
			else notificationList.add(new FriendsNotification(makeUser(notiobject.get(i).getString("username"))));
		}
	}


	private User makeUser(String user){
		return new User(user);
	}

	@Override
	public int compare(ParseObject arg0, ParseObject arg1) {
		
		return compareTo (arg0.getDate("createdAt").getSeconds(), arg1.getDate("createdAt").getSeconds());
	}
	
	public int compareTo(int a, int b){
		if(a<b)
			return -1;
		else if (b<a){
			return 1;
		}
		else{
			return 0;
		}
	}

	
	


}

