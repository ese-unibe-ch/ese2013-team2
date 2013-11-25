package ch.unibe.unisportbern.views.sportsTab;


import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.IEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {

	Context context;
	String[] values;

	public CustomArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_view_layout, values);
		
		this.context = context;
		this.values = values;
	}
	
	private String[] convertValues(ArrayList<IEvent> values2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_view_layout, null);
		}
		TextView textV = (TextView) view.findViewById(R.id.textViewListViewText);	
		
		textV.setText(values[position]);

		return view;
	}

}
