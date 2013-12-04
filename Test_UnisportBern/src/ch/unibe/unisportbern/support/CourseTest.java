package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import android.test.AndroidTestCase;

public class CourseTest extends AndroidTestCase{
	
	DBMethodes db;
	ArrayList<Sport> sports;
	ArrayList<Course> courses;
	Course course;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		this.sports = db.getAllSport();
		this.courses = db.getAllCourses(sports.get(1));
		this.course = courses.get(1);
		this.tearDown();
		this.getName();
	}
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	protected void getNameTest(){
		String name = this.course.getName();
		assertNotNull(name);
	}
	

}
