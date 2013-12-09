package ch.unibe.unisportbern.notification;

import android.content.Context;
import android.content.Intent;

import ch.unibe.unisportbern.support.User;
import ch.unibe.unisportbern.views.friends.ProfileWrapperActivity;

public class FriendsNotification implements INotification {
	
	private User newFriend;
	
	
	public FriendsNotification(User newFriend){
		this.newFriend = newFriend;
	}
	
	public User getFriend(){
		return newFriend;
	}
	
	@Override
	public String toString(){
		return newFriend.getUsername() + " has added you as friend";
	}

	@Override
	public boolean isFriendsNotification() {
		return true;
	}

	@Override
	public String getFriendName() {
		return this.getFriend().getUsername();
	}

	@Override
	public void delete(NotificationManager manager) {
		manager.deleteNotification(this);
		
	}

	@Override
	public void startActivity(Context context) {
		Intent intent = new Intent(context, ProfileWrapperActivity.class);	
		intent.putExtra("friendName", newFriend.getUsername());
		
		context.startActivity(intent);
	}
	
	

}
