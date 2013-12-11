package ch.unibe.unisportbern.views.details.reminder;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

/**
 * This class is a helper class to save the reminder information into an Intent and split the information so that it can be used.
 * @author Karan Sethi
 *
 */

public class Reminder {
	
	
	private ArrayList <Integer> timeElements;
	private ArrayList <Integer> dateElements;
	
	/**
	 * It saves the information to an Intent so that they can be sent further. 
	 * @param starttime when the course begins
	 * @param intent this intent is filled with information
	 * @param min tell you how many minutes should be subtracted from the actual sportstime
	 * @param sportname name of the course.
	 *
	 */
	
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
