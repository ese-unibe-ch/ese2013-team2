package ch.unibe.unisportbern.notification;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.User;

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

}
