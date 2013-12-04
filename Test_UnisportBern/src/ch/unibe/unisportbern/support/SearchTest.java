package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import android.test.AndroidTestCase;

public class SearchTest extends AndroidTestCase{
	
	DBMethodes db;
	ArrayList<IEvent> searchResult;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		this.tearDown();
		this.searchSportTest();
		this.searchCourseTest();
	}
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	protected void searchSportTest(){
		this.searchResult = db.searchSport("orientierungslauf");
		assertEquals("Orientierungslauf",searchResult.get(0).getName().toString());
		assertNotNull(searchResult);
	}
	
	protected void searchCourseTest(){
		this.searchResult = db.searchCourse(1, 2);
		assertNotNull(searchResult);
	}

}
