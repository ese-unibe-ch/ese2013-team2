package com.example.listradiobutton2;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NotificationActivity extends Activity {
	private PendingIntent pendingIntent;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
     {
       
      super.onCreate(savedInstanceState);
      //setContentView(R.layout.activity_main);
     
		Calendar calendar = Calendar.getInstance();
	      
	      calendar.setTimeInMillis(System.currentTimeMillis());
	     // calendar.add(Calendar.MINUTE, 2);
	     
	      //calendar.set(Calendar.MONTH, 11);
	      //calendar.set(Calendar.YEAR, 2013);
	      //calendar.set(Calendar.DAY_OF_MONTH, 13);
	 
	      //calendar.set(Calendar.HOUR_OF_DAY, 20);
	      //calendar.set(Calendar.MINUTE, 48);
	      //calendar.set(Calendar.SECOND, 0);
	      //calendar.set(Calendar.AM_PM,Calendar.PM);
	      Log.i("My AlarmManager", "DealBootReceiver invoked, configuring AlarmManager");
	      AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
	      pendingIntent =
	               PendingIntent.getBroadcast(this, 0, new Intent(this, MyReceiver.class), 0);

	      // use inexact repeating which is easier on battery (system can phase events and not wake at exact times)
	      //alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(),
	               //AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
	      
	      alarmMgr.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
	   }
    
    } //end onCreate
	
	


