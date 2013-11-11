package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.views.profile.ProfileFragment;

import com.example.unisportbern.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setDisplayShowTitleEnabled(false);
		
		Tab tab = actionbar.newTab();
		tab.setText("Profile");
		tab.setTabListener(new TabListener<ProfileFragment>(this, "Profile", ProfileFragment.class));
		
		actionbar.addTab(tab);
		
		tab = actionbar.newTab();
		tab.setText("Courses");
		tab.setTabListener(new TabListener<CoursesFragment>(this, "Courses", CoursesFragment.class));
		
		actionbar.addTab(tab);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		return true;
	}
	
	@Override
	/**
	 * defines the action performed when a button is clicked
	 * 
	 * @item	the button that has been clicked
	 */
	public boolean onOptionsItemSelected(MenuItem item){
		
		if (item.getItemId() == R.id.action_search)
			//TODO:
			return true;
		
		return super.onOptionsItemSelected(item);
	}

}
