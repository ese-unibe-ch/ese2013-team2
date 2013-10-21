package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.support.SportsAdapter;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ListView;

public class CoursesFragment extends ListFragment {

	Sport[] sports;
	
	//public Sport[] SPORTS = new Sport[4];
	public String[] SPORTS;			
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		DBMethodes.sportUpdate(getActivity());
		DBMethodes.courseUpdate(getActivity());
		
		Sport[] sportList = DBMethodes.getAllSport(getActivity());
		
		SPORTS = new String[sportList.length];
		
		for(int a=0;a<sportList.length;a++){
			SPORTS[a] = sportList[a].toString(); 
		}

		setListAdapter(new SportsAdapter(getActivity(), sportList));
		ListView list = getListView();
		list.setTextFilterEnabled(true);

	}


}
