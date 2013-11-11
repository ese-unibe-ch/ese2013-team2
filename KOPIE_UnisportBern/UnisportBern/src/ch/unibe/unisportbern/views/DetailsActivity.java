package ch.unibe.unisportbern.views;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private Sport sport;
	private ArrayList<Course> courses;

	ArrayList<TextView> views = new ArrayList<TextView>();
	ArrayList<detailsView> detailViews = new ArrayList<detailsView>();
	
	public final static String NAME = "SportName";
	public final static String ID = "SportID";
	public final static int TITLESIZE = 40; //equal to profile_title in resources
	public final static int TEXTSIZE = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSport();
		getCourses();
		setContentView(getLayout());
		//TextView sportName = (TextView) findViewById(R.id.sportName);
		//sportName.setText(sport.getName());
		
		// placeTextViews();
	}

	private View getLayout() {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(1);
		TextView sportName = new TextView(this);
		sportName.setText(sport.getName());
		sportName.setGravity(Gravity.CENTER);
		sportName.setTextSize(TITLESIZE);
		layout.addView(sportName);
		

		// loops for every course, creating a textview(view) and the corresponding
		// detailsView for further details and adding both to an arraylist.
		
		for (Course c : courses) {
			
			TextView view = generateView(c);
			
			detailsView detailsView = generateDetailsView(this, c);

			detailViews.add(detailsView);
			views.add(view);
			
			
			layout.addView(view);
			layout.addView(detailsView);
		}

		return layout;
	}
	
	private detailsView generateDetailsView(Context cont, Course c) {
		detailsView detailsview = new detailsView(cont, c);
		// TODO: add details
		
		detailViews.add(detailsview);
		
		return detailsview;
	}

	private TextView generateView(Course c){
		TextView view = new TextView(this);
		view.setTextSize(TEXTSIZE);
		view.setClickable(true);
		view.setText(c.getName() + " " + c.getDay() + " " + c.getTime());
		
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleVisibility(v);
			}
		});
		
		views.add(view);
		
		return view;
	}
	
	/**
	 * toggles the corresponding details view belonging to the clicked TextView v
	 * 
	 */
	protected void toggleVisibility(View v) {
		detailsView dV = detailViews.get(views.indexOf(v));
		
		if (dV.isShown())
			dV.setVisibility(View.GONE);
		else
			dV.setVisibility(View.VISIBLE);
	}

	private void getSport() {
		Intent intent = this.getIntent();
		int id = intent.getIntExtra(ID, 0);
		String name = intent.getStringExtra(NAME);
		this.sport = new Sport(id, name);
	}

	private void getCourses() {
		DBMethodes dbMethodes = new DBMethodes(this);

		try {
			courses = dbMethodes.getAllCourses(sport);
		} catch (Exception e) {
		}
	}

}
