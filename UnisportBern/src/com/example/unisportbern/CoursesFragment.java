package com.example.unisportbern;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CoursesFragment extends ListFragment {

	Sport[] sports;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);

	}

	static final String[] SPORTS = new String[] { "Fussball", "Tennis", "Golf", "Tisch Tennis", "KungFu", "Badminton",
			"Karate", "Basketball", "Volleyball", "Handball" };

}
