package com.example.unisportbern;

import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CoursesFragment extends ListFragment {

	Sport[] sports;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);

	}

	//public static Sport[] sportList = DBMethodes.getSports();	
	
	static final String[] SPORTS = new String[] {"a"};
}
