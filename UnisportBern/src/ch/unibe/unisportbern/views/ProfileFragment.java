package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.details.DActivity;

import com.example.unisportbern.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
	
	protected final static String NAME = "SportName";
	protected final static String ID = "SportID";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		DBMethodes db = new DBMethodes(getActivity());
		
		View view = inflater.inflate(R.layout.profile_layout, container, false);
		
		final ListView listView = (ListView) view.findViewById(R.id.listView_favourite);
		ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(getActivity(),R.layout.allcourseslist);
		listView.setAdapter(adapter);
		adapter.addAll(db.getAllFavorites());
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				DBMethodes db = new DBMethodes(getActivity());
				
				Sport sport = db.getAllFavorites().get(arg2).getSport();
				
				Intent intent = new Intent(getActivity(), DActivity.class);
				intent.putExtra(NAME, sport.getName());
				intent.putExtra(ID, sport.getId());
				
				startActivity(intent);				
			}
		});
		
		return view; 
	}
	
}
