package ch.unibe.unisportbern.notification;

import android.content.Context;

/**
 * all the classes that display a notification implement this interface
 * 
 * @author Karan Sethi
 */

public interface INotification {
	
	public String toString();

	public boolean isFriendsNotification();

	public String getFriendName();

	public void delete(NotificationManager notificationManager);

	public void startActivity(Context context);
	
}
