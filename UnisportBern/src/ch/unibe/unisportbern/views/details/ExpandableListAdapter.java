package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;

import com.example.unisportbern.R;

import ch.unibe.unisportbern.support.Sport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter{

	private Context context;
	private ArrayList<Sport> sports;
	
	public ExpandableListAdapter(Context cont, ArrayList<Sport> sp){
		context = cont;
		sports = sp;
	}
	
	@Override
	public Object getChild(int s, int c) {
		return sports.get(s).getCourse(c);
	}

	@Override
	public long getChildId(int s, int c) {
		return sports.get(s).getCourse(c).getId();
	}

	@Override
	public View getChildView(int s, int c, boolean flag, View pView, ViewGroup pViewGroup) {
		
		if (pView ==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			pView = inflater.inflate(R.layout.details_child_row, null);
		}
		
		return pView;
				
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return sports.get(groupPosition).getCourses().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return sports.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return sports.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return sports.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

		Sport sport = sports.get(groupPosition);
		if (view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.details_group_heading,null);
		}
		
		TextView heading = (TextView) view.findViewById(R.id.nameView);
		heading.setText(sport.getName());
		
		return view;		
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
