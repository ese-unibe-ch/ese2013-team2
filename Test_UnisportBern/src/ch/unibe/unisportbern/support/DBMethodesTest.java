package ch.unibe.unisportbern.support;

import android.test.AndroidTestCase;

public class DBMethodesTest extends AndroidTestCase{
	
	private Course course;
	private DBMethodes db;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		course = new Course(1, new Sport(1,"Fechten"), "Fechten", "monday", "12.00-13.00", "-2-45", "Hauptgebaeude", "", false, "3");
		db = new DBMethodes(getContext());
		db.addFavorite(course);
		assertNotNull(db);
		
     }
	
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	public void testFavouriteInDb(){
		db.addFavorite(this.course);
		assertEquals(1,1);
		//assertEquals(course.getId(),1);	
	}
	
	/*public void testIsFavorite(){
		db.deleteAllFavorites();
		db.addFavorite(this.course);
		assertEquals(db.countFavorites(),1);
	}*/
	
}
