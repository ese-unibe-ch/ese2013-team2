package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBMethodes {
	
	private Network network;
	private DBHelper dbHelper;
	
	public DBMethodes(Context context){
		this.network = new Network(context);
		this.dbHelper = new DBHelper(context);
	}
	
	public void sportUpdate() throws JSONException, InterruptedException, ExecutionException, TimeoutException{
        
        if(network.isOnline()){
        	
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            
            ContentValues values = new ContentValues();
        	
        	JsonSport json = new JsonSport();
            
            ArrayList<Sport> list = json.getAllSports();
            
    	        for(int a=0;a<list.size();a++){
    	
    		        //Setup new Content Values and assign some dummy content
    	        	int sid = a+1;
    		
    		        values.put("SID", a+1);
    		        values.put("NAME", list.get(a).getName());
    		
    		        //Perform the insert/update
    		        if(db.update(DBHelper.SPORTS,values,"SID = "+sid,null) == 0)
    		        {
    		        	db.insert(DBHelper.SPORTS,null, values);
    		        }
    	        } 
    	      //Close the Database and the Helper
    	      dbHelper.close();
    	      db.close();
        }
	}
	
public void courseUpdate(Sport sport) throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        JsonCourse json = new JsonCourse();

        ArrayList<Course> allCourses = json.getAllCourses(sport);
        Course course;
        
        for(int a=0;a<allCourses.size();a++){
        	course = allCourses.get(a);
        		
        	values.put("SID", sport.getId());
        	values.put("COURSENAME", course.getName());
        	values.put("DAY", course.getDay());
    	    values.put("TIME", course.getTime());
    	    values.put("PHASES", course.getPhases());
    	    values.put("LOCATION", course.getLocation());
    	    values.put("INFORMATION", course.getInformation());
    	    values.put("SUB", course.getSub());
    	    values.put("KEW", course.getKew());

    	        //Perform the insert/update
    	        if(db.update(DBHelper.COURSES, values, "COURSENAME = \""+course.getName()+"\"",null) == 0)
    	        {
    	        	db.insert(DBHelper.COURSES, null, values);
    	        }
        }
        //Close the Database and the Helper
        dbHelper.close();
        db.close();
	}
	
	/**
	 * returns a list with all sport names.
	 * 
	 * @param context
	 * @return ArrayList<Sport>
	 */
	public ArrayList<Sport> getAllSport(){

		SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        Cursor cursor = dbHelper.query(db, "SELECT * FROM sports");

        ArrayList<Sport> sportNames = new ArrayList<Sport>();
        
        cursor.moveToFirst();
        
        for(int a=0; a<cursor.getCount(); a++){
        	
        	sportNames.add(new Sport(cursor.getInt(0), cursor.getString(1)));
        	cursor.moveToNext();
        }
        
        //Close the Database and the Helper
        db.close();
        dbHelper.close();
		
		return sportNames;
	}
	

	/**
	 * return a list of all Courses from a certain Sport 
	 * @param context
	 * @param sid
	 * @return ArryList<Course>
	 * @throws TimeoutException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws JSONException 
	 */
	public ArrayList<Course> getAllCourses (Sport sport) throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		
		//If Network is connected to internet, Updating the database
		if(network.isOnline()){
			this.courseUpdate(sport);
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int sid = sport.getId();
        
        Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE sid="+sid);
        
        //some definitions
        ArrayList<Course> courses = new ArrayList<Course>();
        
        cursor.moveToFirst();
        
        for(int a=0;a<cursor.getCount();a++){
        	courses.add(new Course(cursor.getInt(0), sport, cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),sub(cursor.getInt(8)), cursor.getString(9)));
        	cursor.moveToNext();
        }
        
        return courses;
	}
	
	public void addFavorite(Course course){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("cid", course.getId());
		
        db.insert(DBHelper.FAVORITES,null, values);
	}
	
	public void deleteFavorite(Course course){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(DBHelper.FAVORITES, "cid = "+Integer.toString(course.getId()), null);
	}
	
	public boolean isFavorite(Course course) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE coursename=\""+course.getName()+"\"");
		
		if(cursor.getCount() == 0) return false;
		
		else return true;
	}
	
	public ArrayList<Course> getAllFavorites(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
        
		Cursor cursorFavorites = dbHelper.query(db, "SELECT * FROM favorites");
        cursorFavorites.moveToFirst();
        
        ArrayList<Course> allFavorites = new ArrayList<Course>();
        
        for(int a=0;a<cursorFavorites.getCount();a++){
		
			Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE cid="+cursorFavorites.getInt(1));
	        cursor.moveToFirst();
	        
	        Cursor cursorSport = dbHelper.query(db, "SELECT sid, name FROM sports WHERE sid="+cursor.getInt(1));
	        cursorSport.moveToFirst();
	       
	        allFavorites.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0),cursorSport.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),sub(cursor.getInt(8)), cursor.getString(9)));
	        
	        cursorFavorites.moveToNext();
        }
        
        return allFavorites;
	}
	
	private boolean sub(int i){
		boolean sub;
		if(i==1) sub = true;
		else sub = false;
		return sub;
	}

	public boolean isFavourite(Course course) {
		// TODO Auto-generated method stub
		return false;
	}
}