package ch.unibe.unisportbern.views.friends;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.profile.ProfileFragment;
import android.app.Activity;
import android.os.Bundle;

public class ProfileWrapperActivity extends Activity{

	ProfileFragment fragment;
	String friendName;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_wrapper_layout);
        
        getFriendName();
        
        // TODO: add when working
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }

	private void getFriendName() {
		friendName = getIntent().getStringExtra("friendName");
	}
	
}
