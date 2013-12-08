package ch.unibe.unisportbern.notification;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.User;
import ch.unibe.unisportbern.views.friends.ProfileWrapperActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class NotificationArrayAdapter extends ArrayAdapter<String>{

	private Context context;

	private String[] values;

	public NotificationArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_view_layout, values);
		
		this.values = values;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_friends_layout, null);
		}
		TextView textV = (TextView) view.findViewById(R.id.textViewFriendsList);

		textV.setText(values[position]);

		/*ImageButton delete = (ImageButton) view.findViewById(R.id.buttonDeleteFriend);

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: delete friend(friendName)
			}
		});

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ProfileWrapperActivity.class);
				intent.putExtra("friendName", values[position]);
				context.startActivity(intent);
			}
		});*/

		return view;
	}
	
	@Override
	public int getCount(){
		return values.length;
	}

	
	public void setValues(String array[]){
		values = array;
	}

}

