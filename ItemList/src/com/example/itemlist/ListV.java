package com.example.itemlist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListV extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.allcourseslist, SPORTS));
		ListView list = getListView();
		list.setTextFilterEnabled(true);
		
	}
	
	static final String[] SPORTS= new String[]{"Fussball", "Tennis", "Golf", "Tisch Tennis", "KungFu", "Badminton", "Karate", "Basketball", "Volleyball", "Handball"
	};
	

}
