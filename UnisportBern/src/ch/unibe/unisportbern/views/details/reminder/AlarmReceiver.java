package ch.unibe.unisportbern.views.details.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * This class receives the timer information of the notification and invoces the Service in background
 * @author Karan
 *
 */


public class AlarmReceiver extends BroadcastReceiver {
	
	 @Override
	   public void onReceive(Context context, Intent intent) {
		 
		 
	      Log.i("My AlarmReceiver", "AlarmReceiver invoked, starting Service in background");
	      Intent i = new Intent (context, NotificationService.class);
	      i.putExtra("sports", intent.getStringExtra("sports"));
	      i.putExtra("time", intent.getStringExtra("time"));
	      context.startService(i);
	   }

}
