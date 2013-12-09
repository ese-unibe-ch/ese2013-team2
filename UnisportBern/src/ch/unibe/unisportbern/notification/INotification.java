package ch.unibe.unisportbern.notification;

import android.content.Context;

public interface INotification {
	
	public String toString();

	public boolean isFriendsNotification();

	public String getUserName();

	public String getFriendName();

	public void delete(NotificationManager notificationManager);

	public void startActivity(Context context);
	
}
