package ch.unibe.unisportbern.views.profile;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.Course;

public class FriendsProfileFragment extends ProfileFragment {
	
	@Override
	protected ArrayList<Course> getList(){
		// TODO: retrieve list from parse.com
		return new ArrayList<Course>();
	}
	
	@Override
	protected String getUsername(){
		// TODO: get username from parse.com
		return "not yet implemented";
	}

}
