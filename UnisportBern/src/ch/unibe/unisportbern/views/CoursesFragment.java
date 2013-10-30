package ch.unibe.unisportbern.views;

import java.util.ArrayList;
import ch.unibe.unisportbern.support.*;

import com.example.unisportbern.R;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CoursesFragment extends ListFragment {

	protected String[] SPORTS;
	protected final static String NAME = "SportName";
	protected final static String ID = "SportID";
	protected ArrayList<Sport> sports;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Activate the database
		try {
			DBMethodes.sportUpdate(getActivity());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		sports = DBMethodes.getAllSport(getActivity());

		SPORTS = getNames(sports);

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				ArrayList<Sport> sports = DBMethodes.getAllSport(getActivity());
				
				Intent intent = new Intent(getActivity(), DetailsActivity.class);
				intent.putExtra(NAME, sports.get(arg2).getName());
				intent.putExtra(ID, sports.get(arg2).getId());
				startActivity(intent);
			}
		});

	}

	private String[] getNames(ArrayList<Sport> list) {

		String[] result = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).getName();
		}

		return result;
	}

}
