package ch.unibe.unisportbern.views.details.reminder;

import java.util.ArrayList;
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
	
	private String time;
	private String sportsName;

	@Override
    public void onReceive(Context context, Intent intent) {
			
	 int timeMin = 0;
	 ArrayList<Integer>timeElements;
	 ArrayList<Integer>dateElements;
	 Bundle extras = intent.getExtras();
	 timeMin= extras.getInt("radioTime");
	 timeElements= extras.getIntegerArrayList("timeEl");
	 dateElements= extras.getIntegerArrayList("dateEl");
	 time = extras.getString("time");
	 sportsName = extras.getString("sports");
			
	 Calendar calendar = Calendar.getInstance();
	 calendar.set(Calendar.MONTH, dateElements.get(1));
	 calendar.set(Calendar.YEAR, dateElements.get(2));
	 calendar.set(Calendar.DAY_OF_MONTH, dateElements.get(0));
		 
	 calendar.set(Calendar.HOUR_OF_DAY, timeElements.get(0));
	 calendar.set(Calendar.MINUTE, timeElements.get(1));
	 calendar.add(Calendar.MINUTE, -timeMin);
	 calendar.set(Calendar.SECOND, 0);
	 Log.i("My AlarmManager", "DealBootReceiver invoked, configuring AlarmManager");
	 AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	 Intent i = new Intent(context, AlarmReceiver.class);
	 i.putExtra("time", time);
	 i.putExtra("sports", sportsName);
	 PendingIntent pendingIntent =
		            PendingIntent.getBroadcast(context, 0,i , 0);
	 alarmMgr.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
		      
	}
		
}


