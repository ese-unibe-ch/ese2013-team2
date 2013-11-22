package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.views.dialogs.SignUpDialog;
import ch.unibe.unisportbern.views.profile.ProfileFragment;
import ch.unibe.unisportbern.views.search.SearchDialog;

import ch.unibe.unisportbern.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

	boolean FIRSSTART = true;
	
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
		
		// Activate the database
		DBMethodes dbMethodes = new DBMethodes(this);
		dbMethodes.setUpDatabase();
		
		if (FIRSSTART){
			DialogFragment signupFragment = new SignUpDialog();
			signupFragment.show(getFragmentManager(), "Welcome Dialog");
		}	
		
		int index = getIntent().getIntExtra("index", -1);
		
		// exception needed?
		if (index != -1){
			ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableListViewFavourites);
			list.expandGroup(index);
		}
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
		
		if (item.getItemId() == R.id.action_search){

			SearchDialog searchDialog = new SearchDialog();
			searchDialog.show(getFragmentManager(), "search");
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
