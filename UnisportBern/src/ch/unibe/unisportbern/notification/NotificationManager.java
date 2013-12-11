package ch.unibe.unisportbern.notification;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.widget.ListView;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.friends.CustomAdapter;

public class NotificationManager implements Observer {
	
	/**
	 * This class manages all the notification that implement the interface INotification.
	 * 
	 * @author Karan Sethi
	 */
	
	private ArrayList <INotification> notificationsList;
	private Context context;
	private ParseMethodes parse;
	private CustomAdapter adapter;
	
	
	public NotificationManager (Context context){
		this.context = context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	/**
	 * is responsible for for managing all the notifications that implent INotification.
	 * 
	 * @author Karan Sethi
	 */
	
	public void checkNotification(){
		parse.fillINotificationList();
	}
	
	public void deleteNotification(FriendsNotification friendNotification){
		parse.deleteFriendNotification(friendNotification);
	}
	
	public void deleteNotification(FriendsFavouriteNotification favouriteNotification){
		parse.deleteFriendsFavouriteNotification(favouriteNotification);
	}
	
	public void deleteNotification(INotification notification){
		notification.delete(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		notificationsList = parse.getNotifications();
		adapter.setValues(this.notificationToString(notificationsList));
		adapter.notifyDataSetChanged();
		
	}
	
	/**
	 * this method sets the adapter of the interface CustomAdapter into the listview, it is called by class Friendsfragment
	 * @param list This is the ListView called to set the adapter.
	 * @param friendsadapter This is the adapter of the type customadapter interface
	 */
	
	public void createView(CustomAdapter friendsAdapter, ListView list) {
		adapter = friendsAdapter;
		list.setAdapter(adapter);	
		
		
	}
	
	
	private String [] notificationToString(ArrayList <INotification> notification){
		String message [] = new String [notification.size()];
		for (int i=0; i<notification.size();i++){
			message[i]= notification.get(i).toString();
		}
		
		return message;
		
		
	}

}
