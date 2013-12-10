package ch.unibe.unisportbern.views.search;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.IEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class SearchResultsActivity extends Activity {

	Intent intent;
	SearchHandler parameter;

	ArrayList<IEvent> result;
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setup(savedInstanceState);
	
		//TODO: listView.setAdapter();
	}

	private void setup(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		parameter = (SearchHandler) getIntent().getParcelableExtra("parameter");

		result = parameter.getSearchResult(this);
		
		listView = new ListView(this);
	}
}
