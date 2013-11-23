package ch.unibe.unisportbern.views.sportsTab;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.details.DActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_view_layout, null);
		}
		TextView textV = (TextView) view.findViewById(R.id.textViewListViewText);	
		
		textV.setText(values[position]);

		/*textV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DBMethodes dbmet = new DBMethodes(context);

				ArrayList<Sport> sports = dbmet.getAllSport();

				Intent intent = new Intent(context, DActivity.class);
				intent.putExtra("SportName", sports.get(position).getName());
				intent.putExtra("SportId", sports.get(position).getId());
				context.startActivity(intent);
			}
		});*/

		return view;
	}

}
