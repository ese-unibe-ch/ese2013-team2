package ch.unibe.unisportbern.views.details;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import com.androidquery.AQuery;
import com.parse.ParseUser;

import ch.unibe.unisportbern.R;

import ch.unibe.unisportbern.support.Course;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.FavouritesManager;
import ch.unibe.unisportbern.support.JsonCoordinate;
import ch.unibe.unisportbern.views.dialogs.ReminderDialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * is responsible for filling up the ExpandableList in DActivity
 * 
 * @author Thomas Steinmann
 */
public class SportsAdapter extends BaseExpandableListAdapter  {
	private Context context;
	private ArrayList<Course> courseList;
	public SportsAdapter(Context context, ArrayList<Course> courseList) {
		this.context = context;
		this.courseList = courseList;
	}

	
	@Override
	public Object getChild(int index, int stub) {
		return courseList.get(index);
	}

	@Override
	public long getChildId(int index, int stub) {
		//TODO: changed here, returned stub before..
		return courseList.get(index).getId();
	}

	/**
	 * precondition: courses list has to contain data or else the list cannot be filled
	 * 
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		final Course course = courseList.get(groupPosition);
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inflater.inflate(R.layout.child_row, null);
		}
		
		setUpButtons(convertView, course);
		setUpRatingBar(convertView, course);
		setupImage(convertView, course);
		
		return convertView;
	}


	private void setupImage(View convertView, Course course) {
		String url = course.getImageLink();
		ImageView iv = (ImageView) convertView.findViewById(R.id.ImageViewDetails);
		
		AQuery aq = new AQuery(iv);
		aq.id(iv).image(url).visible();
	}


	private void setUpRatingBar(View convertView, final Course course) {
		
		final DBMethodes db = new DBMethodes(context);
		
		RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
		ratingBar.setRating(db.getRating(course));
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				db.setRating(course, rating);
				
			}
		});
	}


	private void setUpButtons(View convertView, final Course course) {
		ImageButton buttonMap = (ImageButton) convertView.findViewById(R.id.button_map);
		ImageButton buttonReminder = (ImageButton) convertView.findViewById(R.id.button_reminder);
		
		buttonMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JsonCoordinate jsoncoordinate = new JsonCoordinate(context);
				
				try {
					String str = jsoncoordinate.getCoordinate(course);
					String uri = "http://maps.google.com/maps?daddr="+str;//"46.949134"+","+"7.4425";
				    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
				    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
				    context.startActivity(intent);
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
            buttonReminder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String time = course.getStartTime();
				String date = course.getNextDay();
				Bundle b = new Bundle();
				String name =  course.getName();
				b.putString("date", date);
				b.putString("time", time);
				b.putString("sports", name);
				b.putInt("cid", course.getId());
				DialogFragment dialog = new ReminderDialog();
				dialog.setArguments(b);
				dialog.show(((Activity) context).getFragmentManager(), "dialog");

			}
		});
		
		setUpTextViews(convertView, course);
		
	}

	private void setUpTextViews(View convertView, Course course) {
		TextView address = (TextView) convertView.findViewById(R.id.textViewAddress);
		TextView reminder = (TextView) convertView.findViewById(R.id.textViewReminder);
		
		address.setText(course.getLocation());
		reminder.setText(course.getDay() + " " + course.getTime());
		
		TextView phases = (TextView) convertView.findViewById(R.id.phases);
		phases.setText("phases: " + course.getPhases());
		
		TextView info = (TextView) convertView.findViewById(R.id.info);
		info.setText(course.getInformation());
		
	}


	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return courseList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return courseList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return courseList.get(groupPosition).getId();
	}

	/**
	 * precondition: courses list has to contain data or else the list cannot be filled
	 * 
	 */
	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		Course course = courseList.get(groupPosition);
		DBMethodes db = new DBMethodes(context);
		
		if (convertView == null) {
			   LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   convertView = inf.inflate(R.layout.group_heading, null);
			  }
		
		TextView courseName = (TextView) convertView.findViewById(R.id.CourseName);
		TextView courseDate = (TextView) convertView.findViewById(R.id.CourseDate);
		
		courseName.setText(course.getName());
		courseDate.setText(course.getDay() + " " + course.getTime());
		
		final FavouritesManager manager = new FavouritesManager(context);
		
		CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkBox);
		checkbox.setChecked(db.isFavourite(course));
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				Course course = courseList.get(groupPosition);
				DBMethodes db = new DBMethodes(context);
				
				if (isChecked && !db.isFavourite(course)){
					db.addFavorite(course);
					
					manager.addFavourites(course);
					Toast.makeText(context, R.string.ToastCourseAdded, Toast.LENGTH_LONG).show();
				}
					
				else if (!isChecked && db.isFavourite(course)){
					db.deleteFavorite(course);
					manager.deleteMyFavouriteCourse(course.getId());
					Toast.makeText(context, R.string.ToastCourseDeleted, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		return convertView;
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public void setCourseList(ArrayList <Course> favourites){
		courseList = favourites;
	}

}
