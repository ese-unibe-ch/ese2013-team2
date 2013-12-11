package ch.unibe.unisportbern.notification;

import android.content.Context;
import android.content.Intent;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.User;
import ch.unibe.unisportbern.views.details.DActivity;

/**
 * is responsible for displaying a notification when a friend has added a course as a favourite.
 * 
 * @author Karan Sethi
 */

public class FriendsFavouriteNotification implements INotification {
	
	private User friend;
	private Course favourite;
	
	
	public FriendsFavouriteNotification(User friend, Course favourite){
		this.friend = friend;
		this.favourite = favourite;
	}


	public Course getCourse() {
		return favourite;
	}
	
	public User getFriend(){
		return friend;
	}
	
	@Override
	public String toString(){
		return friend.getUsername() + " is taking part in " + favourite.getName();
	}


	@Override
	public boolean isFriendsNotification() {
		return false;
	}


	@Override
	public String getFriendName() {
		return getFriend().getUsername();
	}

	public int getCid(){
		return favourite.getId();
	}


	@Override
	public void delete(NotificationManager manager) {
		manager.deleteNotification(this);
	}


	@Override
	public void startActivity(Context context) {
		Intent intent = new Intent(context, DActivity.class);
		intent.putExtra("SportName", favourite.getSport().getName());
		intent.putExtra("SportID", favourite.getSport().getId());
		
		context.startActivity(intent);
	}

}
