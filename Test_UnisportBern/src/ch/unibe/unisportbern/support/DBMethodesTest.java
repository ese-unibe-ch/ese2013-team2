package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import android.test.mock.MockContext;

public class DBMethodesTest extends AndroidTestCase{
	
	private Course course;
	MockContext context = new MockContext();
	private DBMethodes db = new DBMethodes(context);
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		init();
     }
	
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	public void testDBSports() throws Exception{
		init();
		
		assertNotNull(db);
				
		ArrayList<Sport> sports = db.getAllSport();
		
		assertNotNull(sports);
		//assertEquals(0, sports.get(0).getId());
	}
	
	public void init(){
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		course = db.getCourse(1);
		
	}
	
	public void testIsFavorite(){
		db.addFavorite(this.course);
		assertTrue(db.isFavourite(this.course));
	}
	
	
	public void testSearchByName(){
		ArrayList<IEvent> list = db.searchSport("Ai");
		assertFalse(list.isEmpty());
	}
}
