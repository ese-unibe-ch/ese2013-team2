package ch.unibe.unisportbern.views.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {
	
	 @Override
	   public void onReceive(Context context, Intent intent) {
	      Log.i("My AlarmReceiver", "AlarmReceiver invoked, starting DealService in background");
	      context.startService(new Intent(context, NotificationService.class));
	   }

}
