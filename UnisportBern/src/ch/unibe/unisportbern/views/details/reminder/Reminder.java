package ch.unibe.unisportbern.views.details.reminder;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;


public class Reminder {
	
	
	private ArrayList <Integer> timeElements;
	private ArrayList <Integer> dateElements;
	
	public void saveToReminderIntent(String startTime, String date, Intent intent, int min, String sportName, Context context){
		timeElements  = this.splitTimeElements(startTime);
		dateElements = this.splitTimeElements(date);
		intent.putExtra("sports", sportName);
		intent.putExtra("minutes", min);
		intent.putExtra("time", startTime);
		intent.putIntegerArrayListExtra("timeEl", timeElements);
		intent.putIntegerArrayListExtra("dateEl", dateElements);
	}
	 private ArrayList<Integer> splitTimeElements(String element){
		  String[] temp;
		  String startTime = element.substring(0, element.length());
		  System.out.println("");
		  String delimiter = "\\.";
		  temp = startTime.split(delimiter);
		  ArrayList<Integer> elementList = new ArrayList<Integer>();
		  for(int i =0; i < temp.length ; i++){
			  if(temp[i].substring(0,1).equals("0"))
				  temp[i]= temp[i].substring(1);
			 elementList.add(Integer.parseInt(temp[i])) ;  
		  }
		  return elementList;
	}
	

}
