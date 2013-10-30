package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;

import com.example.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.Sport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class SportsAdapter extends BaseExpandableListAdapter  {
	private Context context;
	private ArrayList<Course> courseList;
	private Sport sport;



	public SportsAdapter(Context context, Sport sport, ArrayList<Course> courseList) {
		super();
		this.context = context;
		this.courseList = courseList;
		this.sport = sport;
	}
	
	

	@Override
	public Object getChild(int index, int stub) {
		return courseList.get(index);
	}

	@Override
	public long getChildId(int index, int stub) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inflater.inflate(R.layout.child_row, null);
		}
		
		TextView phases = (TextView) convertView.findViewById(R.id.phases);
		TextView info = (TextView) convertView.findViewById(R.id.info);
		
		phases.setText("phases:\n" + courseList.get(groupPosition).getPhasesAsString());
		info.setText(courseList.get(groupPosition).getInfo());
		
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
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			   LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inf.inflate(R.layout.group_heading, null);
			  }
		
		convertView.setMinimumHeight(100);
		
		TextView courseName = (TextView) convertView.findViewById(R.id.CourseName);
		TextView courseDate = (TextView) convertView.findViewById(R.id.CourseDate);
		
		courseName.setText(courseList.get(groupPosition).getName());
		courseDate.setText(courseList.get(groupPosition).getDay() + courseList.get(groupPosition).getTime());
		
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
