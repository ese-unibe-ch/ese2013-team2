package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import android.test.AndroidTestCase;

public class RatingTest extends AndroidTestCase {
	
	DBMethodes db;
	ArrayList<Sport> sports;
	ArrayList<Course> courses;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		this.sports = db.getAllSport();
		this.courses = db.getAllCourses(sports.get(1));
		this.tearDown();
		this.setRatingTest();
	}
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();      
    }
	
	protected void setRatingTest(){
		db.setRating(courses.get(1), 5);
		int rating = db.getRating(courses.get(1));
		assertEquals(5, rating);
	}
}
