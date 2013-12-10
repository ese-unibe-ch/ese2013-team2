package ch.unibe.unisportbern.views.friends;

import com.parse.ParseUser;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.UserManager;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomFriendResultListAdapter extends CustomAdapter {

	Dialog dialog;
	protected FriendsFragment fragment;
	
	public CustomFriendResultListAdapter(Context context, String[] values, Dialog dialog, FriendsFragment friendsFragment) {
		super(context, R.layout.list_view_friends_result_layout , values);
		this.dialog = dialog;
		fragment = friendsFragment;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_view_friends_result_layout, null);
		}
		TextView textV = (TextView) view.findViewById(R.id.textViewSearchFriendName);

		textV.setText(values[position]);

		ImageButton addButton = (ImageButton) view.findViewById(R.id.imageButtonSearchFriendAdd);

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fragment.getManager().addFriend(values[position], ParseUser.getCurrentUser().getString("username"));
				dialog.cancel();
			}
		});
		
		return view;
	}
}
