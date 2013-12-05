package ch.unibe.unisportbern.views.friends;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.parse.ParseUser;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendsArrayAdapter extends BaseAdapter implements Observer {

	Context context;
	private List<User> values = new ArrayList<User>();
	ParseMethodes pm;

	public FriendsArrayAdapter(Context context) {
		super();
		this.context = context;
		pm = new ParseMethodes(context);
		pm.addObserver(this);
		pm.fillFriendsList(ParseUser.getCurrentUser().getString("username"));
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_view_layout, null);
		}
		TextView textV = (TextView) view.findViewById(R.id.textViewListViewText);	
		
		textV.setText(values.get(position).getUsername());

		return view;
	}
	@Override
	public int getCount(){
		return values.size();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		values = pm.getFriends();
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
