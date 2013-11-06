package com.example.listradiobutton2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
                            
 
public class MyAlarmService extends Service
{
      
   private NotificationManager mManager;
 
    @Override
    public IBinder onBind(Intent arg0)
    {
       // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public void onCreate()
    {
       // TODO Auto-generated method stub 
       super.onCreate();
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
 
   /*@Override
   public int onStartCommand(Intent intent, int flags, int startId)
   {
       super.onStartCommand(intent, flags, startId);
       Intent kintent = new Intent(this, NotificationReceiverActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, kintent, 0);
       Notification noti = new Notification.Builder(this)
       .setContentTitle("New mail from " + "test@gmail.com")
       .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher)
       .setContentIntent(pIntent).build();
       //.addAction(R.drawable.ic_launcher, "Call", pIntent)
       //.addAction(R.drawable.ic_launcher, "More", pIntent)
       //.addAction(R.drawable.ic_launcher, "And more", pIntent).build();
   NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
   // hide the notification after its selected
   noti.flags |= Notification.FLAG_AUTO_CANCEL;

   notificationManager.notify(0, noti);
   return  START_STICKY_COMPATIBILITY;

 }*/
 
   @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
 
}