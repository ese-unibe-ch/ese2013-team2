package ch.unibe.unisportbern.support;



import java.util.ArrayList;

import com.example.unisportbern.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SportsAdapter extends ArrayAdapter<Sport>  {
	private final Context context;
	private final ArrayList<Sport> sportsList;



	public SportsAdapter(Context context, ArrayList<Sport> sportsList) {
		  super(context, R.layout.allcourseslist, sportsList);
		  this.context = context;
		  this.sportsList = sportsList;
		 }
	
	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  LayoutInflater inflater = (LayoutInflater) context
	   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
	  View view = inflater.inflate(R.layout.allcourseslist, parent, false);
	  TextView textView = (TextView) view.findViewById(R.id.textView_sports);
	  textView.setText(sportsList.get(position).toString());
	  return view;
	}

}
