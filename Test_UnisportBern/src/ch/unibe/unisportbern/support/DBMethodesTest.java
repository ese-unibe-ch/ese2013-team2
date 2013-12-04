package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import android.test.AndroidTestCase;

public class DBMethodesTest extends AndroidTestCase{
	
	DBMethodes db;
	ArrayList<Sport> sports;
	ArrayList<Course> courses;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		this.sports = new ArrayList<Sport>();
		this.courses = new ArrayList<Course>();
		this.tearDown();
		this.dbNotEmptyTest();
		this.getAllSportsTest();
		this.getAllCoursesTest();
		this.isTableEmptyTest();
	}
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();      
    }
	
	protected void dbNotEmptyTest(){
		assertNotNull(db); 
	}
	
	protected void getAllSportsTest(){
		this.sports = db.getAllSport();
		assertNotNull(sports);
	}
	
	protected void getAllCoursesTest(){
		this.courses = db.getAllCourses(this.sports.get(1));
		assertNotNull(courses);
	}
	
	protected void isTableEmptyTest(){
		assertFalse(db.isTableEmpty("sports"));
		assertFalse(db.isTableEmpty("courses"));
	}
}
