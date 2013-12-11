package ch.unibe.unisportbern.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import android.content.Context;
import android.widget.Toast;
import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.notification.FriendsFavouriteNotification;
import ch.unibe.unisportbern.notification.FriendsNotification;
import ch.unibe.unisportbern.notification.INotification;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.User;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * This class is responsible for the communication with the server parse. 
 * @author Karan Sethi
 *
 */

public class ParseMethodes extends Observable {
	private ParseUser user = new ParseUser();
	private DBMethodes db;
	private Context context;
	private ArrayList<User> usersList = new ArrayList<User>();
	private ArrayList <Course> favoritesList = new ArrayList <Course>();
	private ArrayList <INotification> notificationList = new ArrayList <INotification>();
	private List <ParseObject> notiobject = new ArrayList<ParseObject>();
	private List <ParseObject> friendsCID = new ArrayList <ParseObject>();
	private List <String> friendsUsername = new ArrayList <String>();
	private boolean invalidUsername =false;
	
	public ParseMethodes(Context context){
		this.context = context;
		 Parse.initialize(context, "p2hZnDFwdzTSLTkPD9t1rWyosBUkJessBArNyAAJ", "vU9rXONSs6QTJYmk38wzHzhrjMGktFXmCf6rsmpM");
		 db = new DBMethodes(context);
	}
	
	/**
	 * This method is responsible for signing up into the account of the application. 
	 * @param username given username from the user to sign up.
	 *@param password given password from the user to sign up.
	 */
	
	public void signingUp(String username, String password){
		
		
			user.setUsername(username);
			user.setPassword(password);
			user.signUpInBackground(new SignUpCallback() {

				@Override
				public void done(com.parse.ParseException e) {
					if (e == null) {
						Toast.makeText(context, R.string.ToastSignUp, Toast.LENGTH_LONG).show();
				    	} else {
				    		invalidUsername = true;
				    		setChanged();
				    		notifyObservers();
				    	}
				}
			});
	}
	
	/**
	 * This method is responsible for logging in into the account of the application. 
	 * @param username given username from the user to log in.
	 *@param password given password from the user to log in.
	 */
	
	public void loggingIn(final String username, final String password ){
		
		ParseUser.logInInBackground(username, password,  new LogInCallback() {
			@Override
			public void done(ParseUser user, com.parse.ParseException e) {
				if (user != null) {
					Toast.makeText(context, R.string.ToastLogIn, Toast.LENGTH_LONG).show();
				    } else {
				    	signingUp(username, password);
				    }
			}
		});
	}
	
	/**
	 * check if username valid or not
	 * 
	 * @return invalidUsername true if login / signup username invalid. 
	 */
	
	public boolean isInvalid() {
		return invalidUsername;
	}
	
	/**
	 * This method is responsible for automatic logging in into the application
	 * @return true if the automatic login is succesful 
	 * @return false if there is not anything found in the cache. 
	 */
	
	public boolean automaticLogin(){
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method adds the favourite of a user into the parse server.
	 * @param favourite adds this specific favourtie as id
	 * 
	 */
	
	public void addFavourites(Course favourite){
		ParseObject favourites = new ParseObject ("FAVOURITES");
		favourites.put("username", ParseUser.getCurrentUser().getString("username"));
		favourites.put("cid", favourite.getId());
		favourites.saveInBackground();
		addFavouriteNotificationForFriend(favourite.getId());
		
	}
	
	/**
	 * This method deletes the favourite of a user in the parse server.
	 * @param id of the favourite course that should be deleted.
	 * 
	 */
	
	public void deleteFavourite(int cid){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITES");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.whereEqualTo("cid", cid);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					objects.get(0).deleteEventually();
				}
			}
			
		});
	}
	
	/**
	 * This method retrieves the list of the favourite courses of a friend.
	 * @return favouriteList is the list of favourite courses of a specific friend.
	 * 
	 */
	
	public ArrayList <Course> getFriendsFavorites(){
		return favoritesList;
	}
	
	/**
	 * This method gets the list of favourite courses of a friend and saves them into a list.
	 * @param friendsUsername it is the name of a friend.
	 * @return favouritesList 
	 * 
	 */
	
	public ArrayList <Course> fillFriendsFavorites(String friendsUsername){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FAVOURITES");
		query.whereEqualTo("username", friendsUsername);
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						if(objects.get(i).getString("friendsID")==null)
						favoritesList.add(db.getCourse(objects.get(i).getInt("cid")));
					}
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
			    } 
			}
		});
		return favoritesList;
	}
	
	/**
	 * This method adds a friend with the specific friend name and adds a notifcation tag into the parse table.
	 * @param friend username of friend
	 * 
	 * 
	 */
	
	public void addFriend(final String friend) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					for(int i = 0; i<objects.size(); i++){
						friendsUsername.add(objects.get(i).getString("friendsID"));
					}
					if (isFriendAdded(friend)!=true){
						fillFriendRows(friend);
					}
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
	
	/**
	 * This method deletes the friend with the specific friendsusername.
	 * @param friendUsername username of friend
	 * @param position where the friend should be deleted in the list. 
	 * 
	 */
	
	public void deleteFriend(String friendUsername, final int position) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.whereEqualTo("friendsID", friendUsername);
		usersList.remove(position);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					objects.get(0).deleteInBackground(new DeleteCallback() {
						
						@Override
						public void done(com.parse.ParseException e) {
							ParseMethodes.this.setChanged();
							ParseMethodes.this.notifyObservers();
						}
					});
				} 
			}

		});
	}
	
	/**
	 * This method retrieves the list that has been filled with user.
	 * @return usersList list filled with users.
	 * 
	 * 
	 */
	
	public ArrayList<User> getUsers(){
		return usersList;
	}
	
	/**
	 * This method fills the list of users with the names of the users friends.
	 * 
	 */
	public void fillFriendsLists(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
		query.findInBackground(new FindCallback<ParseObject>(){
			
			@Override
			public void done(List<ParseObject> objects, com.parse.ParseException e) {
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						if(objects.get(i).getString("friendsID")!=null)
						usersList.add(makeUser(objects.get(i).getString("friendsID")));
					}
					ParseMethodes.this.setChanged();
					ParseMethodes.this.notifyObservers();
			    } 
			}
		});
	}
	
	/**
	 * This method gives the order to search for all users in the parse and fill them into a list.
	 * @param otherUser searches for the related name of that user
	 * 
	 * 
	 */
	
	
	public void orderSearch (String otherUser){
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereContains("username", otherUser);
		query.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> objects, com.parse.ParseException e) {
				
				if (e == null) {
					for (int i = 0 ; i< objects.size(); i++){
						usersList.add(makeUser(objects.get(i).getString("username")));
					}
					setChanged();
					notifyObservers();
					
			    } 
			}

		});
	}
	
	/**
	 * This method creates a new table with the friends and the cid in the parse. when the friend joins a favourite course.
	 * @param id of the specific course
	 * 
	 * 
	 */
	public void addFavouriteNotificationForFriend(final int id) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("username", ParseUser.getCurrentUser().getString("username"));
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
				}
			     
			}
			
		});
		
	}
	
	/**
	 * This method deletes the notification of a friend taking part in a course. It deletes the row in parse with the specific friend and courseid
	 * @param notification that is to be deleted.
	 * 
	 * 
	 */
	
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
	
	
	/**
	 * deletes a specific friendnotification from the parse database, 
	 * @param notification that is to be deleted.
	 * 
	 * 
	 */
	

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
	
	
	
	/**
	 * This method gets a list of all notifications.
	 * @return list that has been filled with type INotifications.
	 * 
	 * 
	 */
	

	public ArrayList <INotification> getNotifications(){
		return notificationList;
	}
	
	/**
	 * This method fills a list of all INotifications into a list, that it can be displayed.
	 * 
	 * 
	 * 
	 */
	
	public void fillINotificationList(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("FRIENDS");
		query.whereEqualTo("notification", true);
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
					
	private void fillFriendsList() {
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
		query.whereEqualTo("receiver", ParseUser.getCurrentUser().getString("username"));
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

}

