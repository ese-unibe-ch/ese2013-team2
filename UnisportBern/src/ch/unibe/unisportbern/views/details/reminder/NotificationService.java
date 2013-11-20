package ch.unibe.unisportbern.views.details.reminder;


import ch.unibe.unisportbern.R;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;

/**
 * This class sets what should stand in the notification and sends the notification.
 * @author Karan
 *
 */

public class NotificationService extends IntentService {
	
	public NotificationService() {
	      super("Notification Service");
	   }
	
	   @Override
	   public void onStart(Intent intent, int startId) {
	      super.onStart(intent, startId);
	   }

	@Override
	protected void onHandleIntent(Intent intent) {
	  Notification noti = new Notification.Builder(this)
	    .setContentTitle(intent.getStringExtra("sports") + " at "   + intent.getStringExtra("time"))
	    .setContentText("Subject").setSmallIcon(R.drawable.unisport_logo).build();
	  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	  // hide the notification after its selected
	  noti.flags |= Notification.FLAG_AUTO_CANCEL;

	  notificationManager.notify(0, noti);
		
	}

}
 