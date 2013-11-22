package ch.unibe.unisportbern.views.details.reminder;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * This class gets the information when the timer must be set. 
 * @author Karan
 *
 */

public class BootReceiver extends BroadcastReceiver {
	
	private String time;
	private String sportsName;
	private int timeMin;
	private ArrayList<Integer>timeElements;
	private ArrayList<Integer>dateElements;

	@Override
    public void onReceive(Context context, Intent intent) {
	 saveReminderInfoToReceiver(intent);	
	 Calendar calendar = Calendar.getInstance();
	 setCalendarTime(calendar);
	 if(timeMin == -1){
		 this.cancelAlarm(context);
	 }
	 else{
		 this.setAlarm(context, calendar);
	 }  
	}
	
	private void saveReminderInfoToReceiver(Intent intent){
		
		 Bundle extras = intent.getExtras();
		 timeMin= extras.getInt("minutes");
		 timeElements= extras.getIntegerArrayList("timeEl");
		 dateElements= extras.getIntegerArrayList("dateEl");
		 time = extras.getString("time");
		 sportsName = extras.getString("sports");
		
	}
	private void setCalendarTime(Calendar calendar){
		 calendar.set(Calendar.MONTH, dateElements.get(1));
		 calendar.set(Calendar.YEAR, dateElements.get(2));
		 calendar.set(Calendar.DAY_OF_MONTH, dateElements.get(0));
			 
		 calendar.set(Calendar.HOUR_OF_DAY, timeElements.get(0));
		 calendar.set(Calendar.MINUTE, timeElements.get(1));
		 calendar.add(Calendar.MINUTE, -timeMin);
		 calendar.set(Calendar.SECOND, 0);
		
	}
	private void setAlarm(Context context, Calendar calendar){
		 AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		 Intent i = new Intent(context, AlarmReceiver.class);
		 i.putExtra("time", time);
		 i.putExtra("sports", sportsName);
		 PendingIntent pendingIntent =
			            PendingIntent.getBroadcast(context, 0,i , 0);
		 alarmMgr.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
		
	}
	private void cancelAlarm(Context context){
		 AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		 Intent i = new Intent(context, AlarmReceiver.class);
		 i.putExtra("time", time);
		 i.putExtra("sports", sportsName);
		 PendingIntent pendingIntent =
			            PendingIntent.getBroadcast(context, 0,i , 0);
		 alarmMgr.cancel(pendingIntent);
	}
	
		
}


