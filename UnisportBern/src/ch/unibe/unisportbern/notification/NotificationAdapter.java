package ch.unibe.unisportbern.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.friends.CustomAdapter;

public class NotificationAdapter extends CustomAdapter {

	NotificationManager manager;
	INotification[] values;
	Context context;

	public NotificationAdapter(Context context, int reference, INotification[] values) {
		super(context, reference, toString(values));
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_friends_layout, null);
		}
		
		TextView textV = (TextView) view.findViewById(R.id.textViewFriendsList);

		textV.setText(values[position].toString());

		ImageButton delete = (ImageButton) view.findViewById(R.id.buttonDeleteFriend);

		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				manager = new NotificationManager(getContext());
				
				manager.deleteNotification(values[position]);			}
		});
		
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				values[position].startActivity(context);
			}
		});
		
		return view;
	}

	private static String[] toString(INotification[] values) {

		String[] strings = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			strings[i] = values[i].toString();
		}
		
		return strings;
	}
}
