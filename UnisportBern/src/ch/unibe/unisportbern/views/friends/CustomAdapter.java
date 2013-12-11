package ch.unibe.unisportbern.views.friends;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * to be implemented when a adapter for a listview is needed.
 * @author Thomas
 *
 */
public class CustomAdapter extends ArrayAdapter<String>{

	protected String[] values;
	protected Context context;

	public CustomAdapter(Context context, int reference, String[] values) {
		super(context, reference, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public int getCount(){
		return values.length;
	}

	public void setValues(String[] array) {
		values = array;
		
	}
}
