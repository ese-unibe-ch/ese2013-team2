package ch.unibe.unisportbern.views.search;


import java.util.ArrayList;

import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.IEvent;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
 
public class SearchHandler implements Parcelable
{	
	private int nameOrTime; // search by name(0) or time(1)
	private String name;
	private int day;
	private int time;
    
	public SearchHandler(int nameVsTime, String n, int i, int j){
		nameOrTime = nameVsTime;
		name = n;
		day = i;
		time = j;
    }
    
	public SearchHandler(Parcel in) {
		this.nameOrTime = in.readInt();
		this.name = in.readString();
		this.day = in.readInt();
		this.time = in.readInt();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(nameOrTime);
		dest.writeString(name);
		dest.writeInt(day);
		dest.writeInt(time);
	}
    
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SearchHandler createFromParcel(Parcel in)
        {
            return new SearchHandler(in);
        }
 
        public SearchHandler[] newArray(int size)
        {
            return new SearchHandler[size];
        }
    };
    
    public ArrayList<IEvent> getSearchResult(Activity activity){
		DBMethodes dbMethodes = new DBMethodes(activity);
		
		if (nameOrTime == 0)
			return searchByName(dbMethodes);
		
		else if (nameOrTime == 1)
			return searchByTime(dbMethodes);
    	
		return null;
    }

	private ArrayList<IEvent> searchByName(DBMethodes dbMethodes) {
		ArrayList<IEvent> list = dbMethodes.searchSport(name);
		return list;
	}

	private ArrayList<IEvent> searchByTime(DBMethodes dbMethodes) {
		return dbMethodes.searchCourse(day, time);
	}
}

