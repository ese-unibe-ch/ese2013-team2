package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;

import com.example.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

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

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inflater.inflate(R.layout.child_row, null);
		}
		
		TextView phases = (TextView) convertView.findViewById(R.id.phases);
		TextView info = (TextView) convertView.findViewById(R.id.info);
		
		phases.setText("phases:\n" + courseList.get(groupPosition).getPhases());
		info.setText(courseList.get(groupPosition).getInformation());
		
		return convertView;
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
		checkbox.setChecked(db.isFavourite(course));
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				Course course = courseList.get(groupPosition);
				DBMethodes db = new DBMethodes(context);
				
				if (isChecked && !db.isFavourite(course))
					db.addFavorite(course);
				
				else if (!isChecked && db.isFavourite(course))
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
