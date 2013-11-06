package com.example.listradiobutton2;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

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
		// TODO Auto-generated method stub
		
	       Intent kintent = new Intent(this, NotificationReceiverActivity.class);
		    PendingIntent pIntent = PendingIntent.getActivity(this, 0, kintent, 0);
	      Notification noti = new Notification.Builder(this)
	      .setContentTitle("Badminton at  " + "13.30")
	      .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher)
	      .setContentIntent(pIntent).build();
	      //.addAction(R.drawable.ic_launcher, "Call", pIntent)
	      //.addAction(R.drawable.ic_launcher, "More", pIntent)
	      //.addAction(R.drawable.ic_launcher, "And more", pIntent).build();
	  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	  // hide the notification after its selected
	  noti.flags |= Notification.FLAG_AUTO_CANCEL;

	  notificationManager.notify(0, noti);
		
	}
	
	

}
