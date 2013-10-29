package ch.unibe.unisportbern.views;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
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
	
	public String[] SPORTS;			
	public final static String EXTRA = "ch.unibe.unisportbern.views";
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//Activate the database
		try {
			DBMethodes.sportUpdate(getActivity());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*Json json = new Json();
		try {
			json.getAllCourses(3);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
			ArrayList<Sport> sports = DBMethodes.getAllSport(getActivity());
			
			
			//DBMethodes.courseUpdate(getActivity());
			SPORTS = new String[sports.size()];
			
			for(int a=0;a<sports.size();a++){
				SPORTS[a] = sports.get(a).toString(); 
			}


		//ArrayList<Sport> sportList = DBMethodes.getAllSport(getActivity());

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int groupPos, long arg3) {
					Intent intent = new Intent(getActivity(), DetailsActivity.class);
					intent.putExtra(EXTRA, SPORTS[groupPos]);
					startActivity(intent);
				}
			});

	}
}
