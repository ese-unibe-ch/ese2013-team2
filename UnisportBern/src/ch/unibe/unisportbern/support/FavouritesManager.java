package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.views.details.SportsAdapter;
import ch.unibe.unisportbern.views.friends.CustomFriendListAdapter;
import android.content.Context;
import android.widget.ExpandableListView;

/**
 * is responsible for managing the favourites of friends and your own as a user.
 * 
 * @author Karan Sethi
 */

public class FavouritesManager implements Observer {
	
	private SportsAdapter adapter;
	private ArrayList <Course> favourites;
	private ParseMethodes parse;
	private Context context;
	private CustomFriendListAdapter testAdapter;
	
	public FavouritesManager(Context context){
		this.context = context;
		parse = new ParseMethodes(context);
		parse.addObserver(this);
	}
	
	/**
	 * gets the list of favourite of a specific friend.
	 * 
	 *
	 */
	
	public ArrayList<Course> getFriendsFavourites(){
		return parse.getFriendsFavorites();
	}
	
	/**
	 * fills the favourites of a specific friend into a list.
	 * 
	 * @param name of the friend
	 */
	
	public void fillFriendsFavouritesList(String friendsUsername){
		parse.fillFriendsFavorites(friendsUsername);
	}
	
	public void addFavourites(Course favourite){
		parse.addFavourites(favourite);
	}
	
	public void deleteMyFavouriteCourse(int cid ){
		parse.deleteFavourite(cid);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		favourites = parse.getFriendsFavorites();
		adapter.setCourseList(favourites);
		adapter.notifyDataSetChanged();
		
	}
	/**
	 * initalizes an adapter and sets it into a view
	 * @param view it is given by the friendsprofilefragment class to set the adapter.
	 * 
	 */
	
	public void createView(ExpandableListView view){
		adapter = new SportsAdapter(context, new ArrayList<Course>());
		view.setAdapter(adapter);
	}
}
