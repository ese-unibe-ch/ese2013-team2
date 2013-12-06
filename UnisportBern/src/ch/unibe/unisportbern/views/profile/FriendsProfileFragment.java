package ch.unibe.unisportbern.views.profile;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.FavouritesManager;

public class FriendsProfileFragment extends ProfileFragment {
	
	@Override
	protected ArrayList<Course> getCourses(){
		this.setUsername();
		FavouritesManager manager = new FavouritesManager(adapter, getActivity());
		manager.fillFriendsFavouritesList(userName);
		return null;
		// TODO: Please return an empty list so that getFriendsFavorites is not called twice, because in FavManger 
		// it is called. Please look at how I did it in FriendsFragment.
	}
	
	@Override
	protected void setUsername(){
		userName = getArguments().getString("friendName");
	}

	@Override
	protected boolean isHelpTextDisplayed() {
		return false;
	}

}
