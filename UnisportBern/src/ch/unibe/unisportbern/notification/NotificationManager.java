package ch.unibe.unisportbern.notification;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.parse.ParseUser;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.views.details.SportsAdapter;
import ch.unibe.unisportbern.views.friends.FriendsArrayAdapter;

public class NotificationManager implements Observer {
	
	private ArrayList <INotification> notificationsList;
	private Context context;
	private ParseMethodes parse;
	private NotificationArrayAdapter adapter;
	
	
	public NotificationManager (Context context){
		this.context = context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	public void checkNotification(){
		parse.fillNotificationList();
	}
	
	public void deleteFriendNotification(FriendsNotification friendNotification){
		parse.deleteFriendNotification(friendNotification);
	}
	
	public void deleteFavouriteNotification(FriendsFavouriteNotification favouriteNotification, int cid ){
		parse.deleteFavourite(ParseUser.getCurrentUser().getString("username"), cid );
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		notificationsList = parse.getNotifications();
		adapter.setValues(this.notificationToString(notificationsList));
		adapter.notifyDataSetChanged();
		
	}
	
	public void createView(NotificationArrayAdapter friendsAdapter, ListView list) {
		adapter = friendsAdapter;
		list.setAdapter(adapter);	
		
		
	}
	
	public String [] notificationToString(ArrayList <INotification> notification){
		String message [] = new String [notification.size()];
		for (int i=0; i<notification.size();i++){
			message[i]= notification.get(i).toString();
		}
		
		return message;
		
		
	}

}
