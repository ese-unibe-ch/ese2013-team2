package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;

import com.example.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.JsonCourse;
import ch.unibe.unisportbern.support.Sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

public class DActivity extends Activity{

	
	public final static String NAME = "SportName";
	public final static String ID = "SportID";
	
	private Sport sport;
	private ArrayList<Course> courses;
	private SportsAdapter sportsadapter;
	
	private ExpandableListView myList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getSport();
		getCourses();
		
		setContentView(R.layout.details_layout);
		
		TextView sportName = (TextView) findViewById(R.id.sportName);
		sportName.setText(sport.getName());
		
		myList = (ExpandableListView) findViewById(R.id.expandableListView);
		sportsadapter = new SportsAdapter(this, sport, courses);
		myList.setAdapter(sportsadapter);
		myList.setOnGroupClickListener(myListGroupClicked);
		
	}
	
	private void getCourses() {
		// ////////////////////////////////////////
		// replace when db is finished //
		// ////////////////////////////////////////
		JsonCourse json = new JsonCourse();

		try {
			courses = json.getAllCourses(sport);
		} catch (Exception e) {
		}
		// ///////////////////////////////////////
	}
	
	private void getSport() {
		Intent intent = this.getIntent();
		int id = intent.getIntExtra(ID, 0);
		String name = intent.getStringExtra(NAME);
		this.sport = new Sport(id, name);
	}

	//our group listener
	 private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {
	 
	  public boolean onGroupClick(ExpandableListView parent, View v,
	    int groupPosition, long id) {
		  
		  parent.expandGroup(groupPosition);
	     
	   return false;
	  }
	   
	 };
}
