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

/**
 * This class is responsible showing the friends. It is actually the tab friends. 
 * @author Thomas Steinmann
 *
 */



public class FriendsFragment extends Fragment {

	private View view;
	private CustomFriendListAdapter adapter;
	private UserManager manager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.friends_fragment_layout, container, false);
		ListView list = (ListView) view.findViewById(R.id.listViewFriends);
		manager = new UserManager(getActivity());
		adapter = new CustomFriendListAdapter(getActivity(), new String[0], manager);
		manager.fillUserList();
		manager.createView(adapter, list);

		Button addButton = (Button) view.findViewById(R.id.buttonAddFriend);

		final FriendsFragment fragment = this;
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SearchFriendDialog dialog = new SearchFriendDialog();
								
				dialog.show(getFragmentManager(), "addFriend");
				dialog.setTargetFragment(fragment, 0);
			}
		});

		return view;
	}

	public UserManager getManager() {
		return manager;
	}

}
