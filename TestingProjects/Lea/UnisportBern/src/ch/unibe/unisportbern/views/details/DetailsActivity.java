package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import com.example.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Date;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.CoursesFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class DetailsActivity extends Activity implements OnClickListener{

	private DBMethodes db;
	private ArrayList<Course> courses;
	private ExpandableListAdapter listAdapter;
	private ExpandableListView myList;
	private int sid;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_layout);
		
		Intent intent = getIntent();
		sid = intent.getIntExtra(CoursesFragment.EXTRA, 1);
		try {
			Context con = DetailsActivity.this;
			courses = DBMethodes.getAllCourses(con, sid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myList = (ExpandableListView) findViewById(R.id.expandableListViewCourses);
		listAdapter = new ExpandableListAdapter(DetailsActivity.this, courses);
		myList.setAdapter(listAdapter);
		myList.setOnGroupClickListener(myListCourseClicked);
	}
	
	OnGroupClickListener myListCourseClicked = new OnGroupClickListener() {
		
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
			myList.expandGroup(groupPosition);
			
			
			
			return false;
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	private void expandAll() {
		  int count = listAdapter.getGroupCount();
		  for (int i = 0; i < count; i++){
		   myList.expandGroup(i);
		  }
		 }
	
	private void collapseAll() {
		  int count = listAdapter.getGroupCount();
		  for (int i = 0; i < count; i++){
		   myList.collapseGroup(i);
		  }
		 }
	
	
}
