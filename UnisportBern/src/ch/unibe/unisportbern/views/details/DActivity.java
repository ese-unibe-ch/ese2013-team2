package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * Responsibilities: - does not contain any data that it's responsible for. all
 * data is stored in the databank - manages the representation of a sport,
 * showing it's courses and additional information - provides functionality to
 * subscribe to courses, show further details, set a reminder or rate a course.
 * 
 * @author Thomas Steinmann
 */
public class DActivity extends Activity {

	public final static String NAME = "SportName";
	public final static String ID = "SportID";

	private Sport sport;
	private ArrayList<Course> courses;
	private SportsAdapter sportsadapter;

	private ExpandableListView myList;

	/**
	 * preconditions: none postconditions: the ExpandableList is filled with the
	 * courses and the whole display is rendered.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getSport();
		getCourses();

		setContentView(R.layout.details_layout);

		TextView sportName = (TextView) findViewById(R.id.sportName);
		sportName.setText(sport.getName());

		myList = (ExpandableListView) findViewById(R.id.expandableListView);
		sportsadapter = new SportsAdapter(this, courses);
		myList.setAdapter(sportsadapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		setDialogButton();

	}

	private void setDialogButton() {
		Button testButton = (Button) findViewById(R.id.buttonTest);
		testButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DialogFragment dialog = new RemDialog();
				dialog.show(getFragmentManager(), "dialog");
			}
		});
	}

	private void getCourses() {
		DBMethodes dbMethodes = new DBMethodes(this);
		try {
			courses = dbMethodes.getAllCourses(sport);
		} catch (Exception e) {
		}
	}

	private void getSport() {
		Intent intent = this.getIntent();
		int id = intent.getIntExtra(ID, 0);
		String name = intent.getStringExtra(NAME);
		this.sport = new Sport(id, name);
	}

}
