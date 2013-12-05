package ch.unibe.unisportbern.views.friends;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.User;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FriendsFragment extends Fragment{

	View view;
	ArrayList<User> friends;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.friends_fragment_layout, container, false);
		
		ParseMethodes pm = new ParseMethodes(getActivity());
		//friends = pm.getFriends(new DBMethodes(getActivity()).getUser().getUsername());
		
		// -- //
		friends = new ArrayList<User>();
		friends.add(new User("masus04", getActivity()));
		// __ //
		
		ListView list = (ListView) view.findViewById(R.id.listViewFriends);
		
		list.setAdapter(new CustomFriendListAdapter(getActivity(), friendsToString()));	
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapterV, View view, int position, long arg3) {
				Intent intent = new Intent(getActivity(), ProfileWrapperActivity.class);
				intent.putExtra("friendName", friends.get(position).getUsername());
				startActivity(intent);
			}
		});
		
		Button button = (Button) view.findViewById(R.id.buttonAddFriend);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO addFriend(friendName)
				
			}
		});
		
		return view;
	}

	private String[] friendsToString() {

		String[] strings = new String[friends.size()];
		
		for (int i=0; i<friends.size(); i++)
			strings[i] = friends.get(i).getUsername();
		
		return strings;
	}
	
}
