package ch.unibe.unisportbern.views.friends;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.profile.FriendsProfileFragment;
import ch.unibe.unisportbern.views.profile.ProfileFragment;
import android.app.Activity;
import android.os.Bundle;

/**
 * wrapps a friendsfragment in an activity so that it can be displayed above the mainactivity.
 * 
 * @author Thomas
 *
 */
public class ProfileWrapperActivity extends Activity{

	ProfileFragment fragment;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_wrapper_layout);
        
        FriendsProfileFragment fragment = new FriendsProfileFragment();
        fragment.setArguments(getIntent().getExtras());
        
        getFragmentManager().beginTransaction().add(R.id.profile_wrapper_layout, fragment, "friend").commit();
        
    }	
}
