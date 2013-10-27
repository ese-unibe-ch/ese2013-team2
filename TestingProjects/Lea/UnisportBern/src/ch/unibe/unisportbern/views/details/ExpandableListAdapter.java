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

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private Sport sport;
	private ArrayList<Course> courses;

	public ExpandableListAdapter(Context cont, ArrayList<Course> cour) {
		context = cont;
		this.courses = cour;
		sport = cour.get(0).getSport();
	}

	@Override
	public Object getChild(int c, int d) {
		return courses.get(c);
	}

	@Override
	public long getChildId(int c, int d) {
		return courses.get(c).getId();
	}

	@Override
	public View getChildView(int s, int c, boolean flag, View pView, ViewGroup pViewGroup) {

		if (pView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			pView = inflater.inflate(R.layout.details_child_row, null);
		}

		return pView;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return courses.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return courses.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return courses.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

		Course course = courses.get(groupPosition);
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.details_group_heading, null);
		}

		TextView heading = (TextView) view.findViewById(R.id.nameView);
		heading.setText(sport.getName());
		
		TextView headingDate = (TextView) view.findViewById(R.id.dateView);
		headingDate.setText(course.getDate().toString());

		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
