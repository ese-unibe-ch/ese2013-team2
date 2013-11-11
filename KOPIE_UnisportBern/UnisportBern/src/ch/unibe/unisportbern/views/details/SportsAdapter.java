package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import ch.unibe.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.JsonCoordinate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * is responsible for filling up the ExpandableList in DActivity
 * 
 * @author Thomas Steinmann
 */
public class SportsAdapter extends BaseExpandableListAdapter  {
	private Context context;
	private ArrayList<Course> courseList;

	public SportsAdapter(Context context, ArrayList<Course> courseList) {
		this.context = context;
		this.courseList = courseList;
	}

	
	@Override
	public Object getChild(int index, int stub) {
		return courseList.get(index);
	}

	@Override
	public long getChildId(int index, int stub) {
		return stub;
	}

	/**
	 * precondition: courses list has to contain data or else the list cannot be filled
	 * 
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inflater.inflate(R.layout.child_row, null);
		}
		
		TextView phases = (TextView) convertView.findViewById(R.id.phases);
		phases.setText("phases:\n" + courseList.get(groupPosition).getPhases());
		
		TextView info = (TextView) convertView.findViewById(R.id.info);
		info.setText(courseList.get(groupPosition).getInformation());
		
		setUpButtons(convertView, courseList.get(groupPosition));
		
		
		
		return convertView;
	}


	private void setUpButtons(View convertView, final Course course) {
		ImageButton buttonMap = (ImageButton) convertView.findViewById(R.id.button_map);
		ImageButton buttonReminder = (ImageButton) convertView.findViewById(R.id.button_reminder);
		
		buttonMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JsonCoordinate jsoncoordinate = new JsonCoordinate();
				
				try {
					String str = jsoncoordinate.getCoordinate(course);
					String uri = "http://maps.google.com/maps?daddr="+str;//"46.949134"+","+"7.4425";
				    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
				    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
				    context.startActivity(intent);
				
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
			}
		});
		
		buttonReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO calling reminder fragment
				Intent intent = new Intent(context, OptionsActivity.class);
				context.startActivity(intent);

			}
		});
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return courseList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return courseList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return courseList.get(groupPosition).getId();
	}

	/**
	 * precondition: courses list has to contain data or else the list cannot be filled
	 * 
	 */
	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		Course course = courseList.get(groupPosition);
		DBMethodes db = new DBMethodes(context);
		
		if (convertView == null) {
			   LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inf.inflate(R.layout.group_heading, null);
			  }
		
		TextView courseName = (TextView) convertView.findViewById(R.id.CourseName);
		TextView courseDate = (TextView) convertView.findViewById(R.id.CourseDate);
		
		courseName.setText(course.getName());
		courseDate.setText(course.getDay() + course.getTime());
		
		CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkBox);
		checkbox.setChecked(db.isFavorite(course));
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				Course course = courseList.get(groupPosition);
				DBMethodes db = new DBMethodes(context);
				
				if (isChecked && !db.isFavorite(course))
					db.addFavorite(course);
				
				else if (!isChecked && db.isFavorite(course))
						db.deleteFavorite(course);
			}
		});
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}