package ch.unibe.unisportbern.views.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.FavouritesManager;
import ch.unibe.unisportbern.views.details.SportsAdapter;

/**
 * a slightly altered version of the ProfileFragment for displaying the profile of a friend instead of the users.
 * 
 * @author Thomas
 *
 */
public class FriendsProfileFragment extends Fragment{
	
	protected String userName;
	protected ExpandableListView listView;
	protected SportsAdapter adapter;
	
	private FavouritesManager manager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.profile_layout, container, false);
		
		TextView tv = (TextView) view.findViewById(R.id.textView_name);
		setUsername();
		tv.setText(userName);
		
		listView = (ExpandableListView) view.findViewById(R.id.expandableListViewFavourites);
		
		manager = new FavouritesManager(getActivity());
		manager.createView(listView);
		manager.fillFriendsFavouritesList(userName);
		
		return view;
	}
	
	protected void setUsername(){
		userName = getArguments().getString("friendName");
	}

}
