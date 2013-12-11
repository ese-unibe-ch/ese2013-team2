package ch.unibe.unisportbern.views.search;

import java.util.ArrayList;

import ch.unibe.unisportbern.support.IEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class SearchResultsActivity extends Activity {

	private Intent intent;
	private SearchHandler parameter;
	private ArrayList<IEvent> result;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setup(savedInstanceState);
	}

	private void setup(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		parameter = (SearchHandler) getIntent().getParcelableExtra("parameter");

		result = parameter.getSearchResult(this);
		
		listView = new ListView(this);
	}
}
