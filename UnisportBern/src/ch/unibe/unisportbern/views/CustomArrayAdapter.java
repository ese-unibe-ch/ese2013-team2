package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {

	private String[] values;

	public CustomArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_view_layout, values);
		
		this.values = values;
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
