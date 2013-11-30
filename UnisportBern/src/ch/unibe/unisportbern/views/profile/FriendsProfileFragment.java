package ch.unibe.unisportbern.views.profile;

import java.util.ArrayList;

import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.Course;

public class FriendsProfileFragment extends ProfileFragment {
	
	@Override
	protected ArrayList<Course> getCourses(){
		// TODO: retrieve list from parse.com
		ParseMethodes pm = new ParseMethodes(getActivity());
		return pm.getFriendsFavorites(userName);
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
