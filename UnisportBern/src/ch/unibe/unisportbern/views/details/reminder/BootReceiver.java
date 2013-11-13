package ch.unibe.unisportbern.views.details.reminder;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * This class gets the information when the timer must be set. 
 * @author Karan
 *
 */

public class BootReceiver extends BroadcastReceiver {

		@Override
		   public void onReceive(Context context, Intent intent) {
			
			int timeMin = 0;
			long timeMilli = 0;
			Bundle extras = intent.getExtras();
			if (extras != null) {
			    timeMin= extras.getInt("radioTime");
			    timeMilli = timeMin*60000;

			}
			
			Calendar calendar = Calendar.getInstance();
		      
		     //calendar.setTimeInMillis(System.currentTimeMillis());
		     //calendar.add(Calendar.MINUTE, 2);
		     
		      calendar.set(Calendar.MONTH, 10);
		      calendar.set(Calendar.YEAR, 2013);
		      calendar.set(Calendar.DAY_OF_MONTH, 10);
		 
		      calendar.set(Calendar.HOUR_OF_DAY, 20);
		      calendar.set(Calendar.MINUTE, 35);
		      calendar.set(Calendar.SECOND, 00);
		      calendar.set(Calendar.AM_PM,Calendar.PM);
		      Log.i("My AlarmManager", "DealBootReceiver invoked, configuring AlarmManager");
		      AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		      PendingIntent pendingIntent =
		               PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);

		      // use inexact repeating which is easier on battery (system can phase events and not wake at exact times)
		      //alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(),
		               //3000, pendingIntent);
		      alarmMgr.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
		      
		   }
		
	}


