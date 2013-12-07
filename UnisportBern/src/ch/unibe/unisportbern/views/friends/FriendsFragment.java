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

public class FriendsFragment extends Fragment{

	private View view;
	private FriendsArrayAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.friends_fragment_layout, container, false);
		ListView list = (ListView) view.findViewById(R.id.listViewFriends);
		adapter = new FriendsArrayAdapter (getActivity());
		UserManager manager = new UserManager(adapter, getActivity());
		//manager.orderSearchOtherUser("karan");
		manager.fillUserList();
		list.setAdapter(adapter);	
		
		Button button = (Button) view.findViewById(R.id.buttonAddFriend);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SearchFriendDialog dialog = new SearchFriendDialog();
				dialog.show(getFragmentManager(), "searchFriend");				
			}
		});
		
		return view;
	}
	
}
