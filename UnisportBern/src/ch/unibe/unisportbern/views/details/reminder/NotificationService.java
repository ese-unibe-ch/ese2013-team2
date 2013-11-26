package ch.unibe.unisportbern.views.details.reminder;



import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.MainActivity;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
		Intent kintent = new Intent(this, MainActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, kintent, 0);
	  Notification noti = new Notification.Builder(this)
	    .setContentTitle(intent.getStringExtra("sports") + " at "   + intent.getStringExtra("time"))
	    .setContentText("").setSmallIcon(R.drawable.unisport_logo)
	  .setContentIntent(pIntent).build();
	  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	  // hide the notification after its selected
	  noti.flags |= Notification.FLAG_AUTO_CANCEL;

	  notificationManager.notify(0, noti);
		
	}

}
 