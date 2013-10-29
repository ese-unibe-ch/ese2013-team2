package ch.unibe.unisportbern.views;

import java.util.ArrayList;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.JsonCourse;
import ch.unibe.unisportbern.support.Sport;

import com.example.unisportbern.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private Sport sport;
	private ArrayList<Course> courses;

	ArrayList<TextView> views = new ArrayList<TextView>();
	ArrayList<detailsView> detailViews = new ArrayList<detailsView>();
	
	public final static String NAME = "ch.unibe.unisportbern.views.name";
	public final static String ID = "ch.unibe.unisportbern.views.id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		TextView sportName = (TextView) findViewById(R.id.sportName);
		sportName.setText(sport.getName());
		getSport();
		getCourses();
		// placeTextViews();
	}

	private View getLayout() {
		RelativeLayout layout = new RelativeLayout(this);
		TextView sportName = new TextView(this);
		sportName.setText(sport.getName());
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
		int id = getIntent().getIntExtra(ID, 0);
		String name = getIntent().getStringExtra(NAME);
		sport = new Sport(id, name);
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

}
