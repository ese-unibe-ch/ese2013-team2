package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.support.Course;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class detailsView extends View {

	Course course;
	
	public detailsView(Context cont){
		super(cont);
	}
	
	public detailsView(Context cont, Course c){
		super(cont);
		course = c;

		RelativeLayout layout = new RelativeLayout(cont);
	
		TextView textV = new TextView(cont);
		textV.setText("here goes the whole details of a course");
		// TODO: add further info
		
		
		
		layout.addView(textV);
	}


	
}
