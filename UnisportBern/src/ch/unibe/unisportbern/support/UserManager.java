package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.parse.ParseUser;

import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.friends.CustomAdapter;

import android.content.Context;
import android.widget.ListView;

public class UserManager implements Observer{
	
	private CustomAdapter adapter;
	private List <User> user;
	private ParseMethodes parse;
	private Context context;
	private static final String username = ParseUser.getCurrentUser().getString("username");
	
	public UserManager(Context context){
		this.context = context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	
	public void addFriend(String friend, String username){
		parse.addFriend(friend, username);	
	}
	
	public void fillUserList(){
		parse.fillFriendsList(username);
	}
	
	public ArrayList<Course> getFriendsFavourites(String username){
		return parse.getFriendsFavorites();
	}
	
	public void addFavourites(Course favourite, String myUsername){
		parse.addFavourites(favourite, myUsername);
	}
	
	public void orderSearchOtherUser(String otherUser){
		parse.orderSearch(otherUser);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		user = parse.getUsers();
		adapter.setValues(userToString(user));
		adapter.notifyDataSetChanged();
		
	}
	public void createView(CustomAdapter customAdapter, ListView list) {
		adapter = customAdapter;
		list.setAdapter(adapter);	
		
		
		
	}
	
	public String [] userToString (List<User> user){
		String friends [] = new String [user.size()];
		for (int i=0; i<user.size();i++){
			friends[i]= user.get(i).toString();
		}
		return friends;
	}
}
