package ch.unibe.unisportbern.support;

import java.util.ArrayList;

import android.test.AndroidTestCase;

public class DBMethodesTest extends AndroidTestCase{
	
	private Course course;
	private DBMethodes db;
	
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
	
	/*public void testIsFavorite(){
		db.deleteAllFavorites();
		db.addFavorite(this.course);
		assertEquals(db.countFavorites(),1);
	}*/
	
	public void init(){
		course = new Course(1, new Sport(1,"Fechten"), "Fechten", "monday", "12.00-13.00", "-2-45", "Hauptgebaeude", "", false, "3");
		db = new DBMethodes(getContext());
		db.setUpDatabase();
		assertNotNull(db);
	}
	
	public void testSearchByName(){
		ArrayList<IEvent> list = db.searchSport("Ai");
		assertFalse(list.isEmpty());
	}
}
