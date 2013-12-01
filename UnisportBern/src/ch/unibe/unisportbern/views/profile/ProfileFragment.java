package ch.unibe.unisportbern.views.profile;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.views.details.SportsAdapter;

import ch.unibe.unisportbern.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * responibilities:
 * - displays all profile information containing username, the users favourite
 * courses and upcoming courses that might be interesting
 * 
 * - all data is stored in the database, not in the ProfileFragment.
 * 
 * @author Thomas Steinmann
 *
 */
public class ProfileFragment extends Fragment{
	
	protected String userName;
	private ExpandableListView listFavourites;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.profile_layout, container, false);
		
		listFavourites = (ExpandableListView) view.findViewById(R.id.expandableListViewFavourites);
		listFavourites.setAdapter(new SportsAdapter(getActivity(), getCourses()));
		
		TextView tv = (TextView) view.findViewById(R.id.textView_name);
		setUsername();
		tv.setText(userName);
		
		setHelpText(view, isHelpTextDisplayed());
		
		return view;
	}

	protected boolean isHelpTextDisplayed() {
		return (listFavourites.getCount() == 0);
	}

	private void setHelpText(View view, boolean isHelpTextDisplayed) {
		TextView textView = (TextView) view.findViewById(R.id.TextView_favHelp);
		if (isHelpTextDisplayed())
			textView.setText(R.string.FavHelpText);	 
		else textView.setText("");
	}
	
	protected ArrayList<Course> getCourses(){
		DBMethodes db = new DBMethodes(getActivity());
		
		return db.getAllFavorites();
	}
	
	protected void setUsername(){
		//TODO:
		//DBMethodes db = new DBMethodes(getActivity());
		//userName = db.getUser().getUsername();
		userName = "UserName";
	}
	
}
