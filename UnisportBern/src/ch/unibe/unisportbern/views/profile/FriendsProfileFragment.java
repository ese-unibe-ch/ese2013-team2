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

public class FriendsProfileFragment extends ProfileFragment implements Observer {
	
	private ParseMethodes pm = new ParseMethodes(getActivity());
	@Override
	protected ArrayList<Course> getCourses(){
		this.setUsername();
		pm.fillFriendsFavorites(userName);
		return pm.getFriendsFavorites();
		// TODO: Please return an empty list so that getFriendsFavorites is not called twice
	}
	
	@Override
	protected void setUsername(){
		userName = getArguments().getString("friendName");
	}

	@Override
	protected boolean isHelpTextDisplayed() {
		return false;
	}

	@Override
	public void update(Observable observable, Object data) {
		adapter.setCourseList(pm.getFriendsFavorites());
		adapter.notifyDataSetChanged();
		
	}
}
