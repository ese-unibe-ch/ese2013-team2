package ch.unibe.unisportbern.support;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.friends.CustomAdapter;
import android.content.Context;
import android.widget.ListView;

/**
 * is responsible for managing all the users. Users can be friends or the user itself.
 * 
 * @author Karan Sethi
 */

public class UserManager implements Observer{
	
	private CustomAdapter adapter;
	private List <User> user;
	private ParseMethodes parse;
	private Context context;
	
	public UserManager(Context context){
		this.context = context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	
	public void addFriend(String friend){
		parse.addFriend(friend);	
	}
	
	public void deleteFriend(String friendName, int position){
		parse.deleteFriend(friendName, position);
	}
	
	/**
	 * this method fills the list of friends of the user of this application.
	 * 
	 * 
	 */
	
	public void fillUserList(){
		parse.fillFriendsLists();
	}
	
	/**
	 * this method searches for a user.
	 * 
	 * @author Karan Sethi
	 */
	
	public void orderSearchOtherUser(String otherUser){
		parse.orderSearch(otherUser);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		user = parse.getUsers();
		adapter.setValues(userToString(user));
		adapter.notifyDataSetChanged();
		
	}
	
	/**
	 * sets the adapter into the listview
	 * @param customadapter given by the friendsfragment class 
	 * @param list which is there that the adapter can be set into it.
	 */
	public void createView(CustomAdapter customAdapter, ListView list) {
		adapter = customAdapter;
		list.setAdapter(adapter);	

		
	}
	
	private String [] userToString (List<User> user){
		String friends [] = new String [user.size()];
		for (int i=0; i<user.size();i++){
			friends[i]= user.get(i).toString();
		}
		return friends;
	}
	
}
