package ch.unibe.unisportbern.notification;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.User;

public class FriendsNotification implements INotification {
	
	private User newFriend;
	private User myUsername;
	
	
	public FriendsNotification(User newFriend, User myUsername){
		this.newFriend = newFriend;
		this.myUsername = myUsername;
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
	public String getUserName() {
		return this.myUsername.getUsername();
	}

	@Override
	public String getFriendName() {
		return this.getFriend().getUsername();
	}
	
	

}
