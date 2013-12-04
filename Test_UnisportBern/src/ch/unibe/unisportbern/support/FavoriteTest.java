package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import android.test.AndroidTestCase;

public class FavoriteTest extends AndroidTestCase{
	
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
		this.addFavoriteTest();
		this.getAllFavoritesTest();
		this.deleteFavoriteTest();
	}
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	protected void addFavoriteTest(){
		db.addFavorite(courses.get(1));
		boolean isFavorite = db.isFavourite(courses.get(1));
		assertTrue(isFavorite);
	}
	
	protected void getAllFavoritesTest(){
		assertNotNull(db.getAllFavorites());
	}
	
	protected void deleteFavoriteTest(){
		db.deleteFavorite(courses.get(1));
		boolean isFavorite = db.isFavourite(courses.get(1));
		assertTrue(!isFavorite);
	}

}
