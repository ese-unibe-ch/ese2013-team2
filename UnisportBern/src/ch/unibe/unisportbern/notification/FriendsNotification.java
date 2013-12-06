package ch.unibe.unisportbern.notification;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.User;

public class FriendsNotification implements INotification {
	
	private User newFriend;
	
	
	public FriendsNotification(User newFriend){
		this.newFriend = newFriend;
	}
	
	public User getFriend(){
		return newFriend;
	}
	
	

}
