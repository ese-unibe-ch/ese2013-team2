package ch.unibe.unisportbern.views.friends;

import com.parse.ParseUser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.UserManager;


/**
 * The Adapter that fits with the ListView in the friens_fragment_layout
 * 
 * @author Thomas
 *
 */
public class CustomFriendListAdapter extends CustomAdapter {
	
	

	UserManager manager;
	
	public CustomFriendListAdapter(Context context, String[] values, UserManager manager) {
		super(context, R.layout.listview_friends_layout, values);
		this.manager = manager;
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

		ImageButton delete = (ImageButton) view.findViewById(R.id.buttonDeleteFriend);

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				manager.deleteFriend(values[position], position);
			}
		});

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ProfileWrapperActivity.class);
				intent.putExtra("friendName", values[position]);
				context.startActivity(intent);
			}
		});

		return view;
	}
	
	
}
