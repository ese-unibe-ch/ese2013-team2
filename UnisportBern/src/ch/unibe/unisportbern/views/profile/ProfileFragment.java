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
	
	String userName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.profile_layout, container, false);
		
		ExpandableListView listFavourites = (ExpandableListView) view.findViewById(R.id.expandableListViewFavourites);
		listFavourites.setAdapter(new SportsAdapter(getActivity(), getList(), listFavourites));
		
		TextView tv = (TextView) view.findViewById(R.id.textView_name);
		tv.setText(getUsername());
		
		TextView textView = (TextView) view.findViewById(R.id.TextView_favHelp);
		if (listFavourites.getCount() == 0)
			textView.setText(R.string.FavHelpText);	 
		else textView.setText("");
		
		return view;
	}
	
	protected ArrayList<Course> getList(){
		DBMethodes db = new DBMethodes(getActivity());
		
		return db.getAllFavorites();
	}
	
	protected String getUsername(){
		//DBMethodes db = new DBMethodes(getActivity());
		//return db.getUser().getUsername();
		return "Name";
	}
	
}
