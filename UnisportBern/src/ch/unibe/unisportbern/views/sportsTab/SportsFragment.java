package ch.unibe.unisportbern.views.sportsTab;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.IEvent;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.CustomArrayAdapter;
import ch.unibe.unisportbern.views.details.DActivity;
import ch.unibe.unisportbern.views.search.SearchDialog;
import ch.unibe.unisportbern.views.search.SearchHandler;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SportsFragment extends Fragment {

	protected static final String NAME = "SportName";
	protected static final String ID = "SportID";
	private View view;
	private SearchHandler searchHandler;
	private ArrayList<IEvent> events;
	private boolean searchActive;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		searchActive = false;
		
		view = inflater.inflate(R.layout.sports_fragment_layout, container, false);

		ListView listView = (ListView) view.findViewById(R.id.listViewSports);

		setupButtons(view);

		// no search results
		if (searchHandler == null)
			setEventsToAllSports();
		
		// search results available
		else if (searchHandler != null)
			setEventsToSearchResults();
		
		updateList();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterV, View view, int position, long arg3) {

				Intent intent = new Intent(getActivity(), DActivity.class);
				intent.putExtra(NAME, events.get(position).getSport().getName());
				intent.putExtra(ID, events.get(position).getSport().getId());
				startActivity(intent);
			}
		});
		
		return view;
	}

	/**
	 * callback method that is called to retrieve an intent from a searchDielog
	 * 
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == resultCode && resultCode == 0) {
			searchHandler = intent.getExtras().getParcelable("parameter");
			events = searchHandler.getSearchResult(getActivity());

			handleEmptyMessage();
			searchActive = true;
			updateList();
		}
	}

	private void handleEmptyMessage() {
		TextView textView = (TextView) view.findViewById(R.id.textViewSportsFragmentEmptyText);
		
		if (events.isEmpty())
			textView.setVisibility(View.VISIBLE);
		else textView.setVisibility(View.GONE);
	}

	private void updateList() {
		ListView listView = (ListView) view.findViewById(R.id.listViewSports);

		listView.setAdapter(new CustomArrayAdapter(getActivity(), toStringArray()));
		
		TextView tv = (TextView) view.findViewById(R.id.textViewSportsFragmentEmptyText);
		
		//helptext
		if (events.isEmpty())
			tv.setVisibility(View.VISIBLE);
		else tv.setVisibility(View.GONE);
		
		//controll buttonText
		Button button = (Button) view.findViewById(R.id.buttonAllSports);
		if (searchActive)
			button.setText("Cancel Search");
		else button.setText("All Sports");
	}

	private void setupButtons(View view) {
		final SportsFragment thisFragment = this;
		ImageButton searchButton = (ImageButton) view.findViewById(R.id.buttonSearchSports);
		Button sportsButton = (Button) view.findViewById(R.id.buttonAllSports);
		
		sportsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setEventsToAllSports();
				searchActive = false;
				updateList();
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SearchDialog searchDialog = new SearchDialog();
				searchDialog.setTargetFragment(thisFragment, 0);
				searchDialog.show(getFragmentManager(), "search");
			}
		});
	}

	private void setEventsToAllSports() {
		DBMethodes dbMethods = new DBMethodes(getActivity());

		events = new ArrayList<IEvent>();

		for (Sport s : dbMethods.getAllSport())
			events.add(s);
	}

	private void setEventsToSearchResults() {
		events = searchHandler.getSearchResult(getActivity());
	}

	public String[] toStringArray() {
		String[] stringArray = new String[events.size()];

		for (int i = 0; i < events.size(); i++) {
			stringArray[i] = events.get(i).getName();
		}

		return stringArray;
	}
	
	public void setSearchActive(boolean isit){
		searchActive = isit;
	}
}
