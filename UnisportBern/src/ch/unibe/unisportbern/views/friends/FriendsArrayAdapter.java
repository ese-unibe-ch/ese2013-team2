package ch.unibe.unisportbern.views.friends;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendsArrayAdapter extends BaseAdapter{

	private Context context;
	private List<User> values = new ArrayList<User>();

	public FriendsArrayAdapter(Context context) {
		super();
		this.context = context;
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
				intent.putExtra("friendName", values.get(position).getUsername());
				context.startActivity(intent);
			}
		});*/
		
		return view;
	}
	@Override
	public int getCount(){
		return values.size();
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
	
	public void setValues(List<User> user){
		values = user;
	}

}
