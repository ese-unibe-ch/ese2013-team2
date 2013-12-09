package ch.unibe.unisportbern.views.friends;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.UserManager;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FriendsFragment extends Fragment {

	private View view;
	// private NotificationArrayAdapter adapter;
	private CustomFriendListAdapter adapter;

	UserManager manager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.friends_fragment_layout, container, false);
		ListView list = (ListView) view.findViewById(R.id.listViewFriends);
		// adapter = new NotificationArrayAdapter(getActivity(), new String[0]);
		adapter = new CustomFriendListAdapter(getActivity(), new String[0]);
		// NotificationManager manager = new NotificationManager(getActivity());
		// manager.checkNotification();
		// manager.createView(adapter, list);
		manager = new UserManager(getActivity());
		manager.fillUserList();
		// manager.orderSearchOtherUser("k");
		manager.createView(adapter, list);

		Button button = (Button) view.findViewById(R.id.buttonAddFriend);

		final FriendsFragment fragment = this;
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SearchFriendDialog dialog = new SearchFriendDialog();
				dialog.show(getFragmentManager(), "addFriend");
				dialog.setTargetFragment(fragment, 0);
			}
		});

		return view;
	}

}
