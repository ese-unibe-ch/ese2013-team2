package ch.unibe.unisportbern.views.sportsTab;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.details.DActivity;
import ch.unibe.unisportbern.views.search.SearchHandler;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class SportsFragment extends Fragment{

	protected static final String NAME = "SportName";
	protected static final String ID = "SportID";
	View view;
	SearchHandler searchHandler;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.sports_fragment_layout, container, false);
		
		ListView listView = (ListView) view.findViewById(R.id.listViewSports);
		
		// no search results
		if (searchHandler == null)
			listView.setAdapter(new CustomArrayAdapter(getActivity(), getAllSports()));
		
		// search results available
		else if (searchHandler != null)
			listView.setAdapter(new CustomArrayAdapter(getActivity(), searchHandler.toStringArray(getActivity())));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				DBMethodes dbmet = new DBMethodes(getActivity());
				
				ArrayList<Sport> sports = dbmet.getAllSport();
				
				Intent intent = new Intent(getActivity(), DActivity.class);
				intent.putExtra(NAME, sports.get(arg2).getName());
				intent.putExtra(ID, sports.get(arg2).getId());
				startActivity(intent);
			}
		});
		
		return view;
	}

	private String[] getAllSports() {
		DBMethodes dbMethodes = new DBMethodes(getActivity());
		
		ArrayList<Sport> sports = dbMethodes.getAllSport();
		String[] stringArray = new String[sports.size()];
		
		for (int i=0; i < sports.size(); i++)
			stringArray[i] = sports.get(i).getName();
		
		return stringArray;
	}

}
