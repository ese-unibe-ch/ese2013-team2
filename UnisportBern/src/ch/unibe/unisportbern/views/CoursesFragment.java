package ch.unibe.unisportbern.views;

import java.util.ArrayList;

import org.json.JSONException;

import ch.unibe.unisportbern.support.*;
import ch.unibe.unisportbern.views.details.DetailsActivity;

import com.example.unisportbern.R;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesFragment extends ListFragment {
	public final static String EXTRA = "ch.unibe.unisportbern.views";
	ArrayList<Sport> sportList = new ArrayList<Sport>();

	Sport[] sports;
	
	//public Sport[] SPORTS = new Sport[4];
	public String[] SPORTS;			
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		DBMethodes.sportUpdate(getActivity());
		DBMethodes.courseUpdate(getActivity());

		
		
		/*try {
			sportList = new JSONNameID().getAllSports();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		sportList = DBMethodes.getAllSport(getActivity());
		
		SPORTS = new String[sportList.size()];
		
		for(int a=0;a<sportList.size();a++){
			SPORTS[a] = sportList.get(a).toString(); 
		}

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent (getActivity(), DetailsActivity.class);
				intent.putExtra(EXTRA, sportList.get(arg2).getId());
				startActivity(intent);
			}
			
		});

	}
	
	public int findId(ArrayList <Sport> sports, View view){
		TextView text =  (TextView)view;
		int i=0;
		while (sports.get(i).getName()!=(text.getText().toString()))
			i++;
		return sports.get(i).getId();
	}


}
