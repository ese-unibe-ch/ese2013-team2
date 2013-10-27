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
	
	public DBMethodes(){
	}
	
	public static void sportUpdate(Context context) throws JSONException, InterruptedException, ExecutionException, TimeoutException{
        
        Network network = new Network(context);
        
        if(network.isOnline()){
        	DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            
            ContentValues values = new ContentValues();
        	
        	Json json = new Json();
            
            ArrayList<Sport> list = json.getAllSports();
            
    	        for(int a=0;a<list.size();a++){
    	
    		        //Setup new Content Values and assign some dummy content
    		
    		        values.put("SID", a);
    		        values.put("NAME", list.get(a).getName());
    		
    		        //Perform the insert
    		        db.insert(DBHelper.SPORTS,null, values);
    	        } 
    	      //Close the Database and the Helper
    	      dbHelper.close();
    	      db.close();
        }
	}
	
public static void courseUpdate(Context context, int sid) throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		
		DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        Json json = new Json();
        ArrayList<String[]> jsonResult = json.getAllCourses(sid);
        
        for(int a=0;a<jsonResult.size();a++){
        	//Setup new Content Values and assign some dummy content
	        values.put("SID", sid);
	        values.put("REALSTARTTIMESEC", 1382072400);
	        values.put("REALENDTIMESEC", 1382072900);
	        values.put("DAY", jsonResult.get(a)[1]);
	        values.put("P1", 1);
	        values.put("P2", 1);
	        values.put("P3", 0);
	        values.put("P4", 1);
	        values.put("P5", 0);
	        values.put("LOCATION", "ExWi");
	        values.put("INFORMATION", "Keine");
	        values.put("SUB", 0);
	        values.put("KEW", 44);

	        //Perform the insert
	        db.insert(DBHelper.COURSES,null, values);
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
	public static ArrayList<Sport> getAllSport(Context context){
		
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
        
        Cursor cursor = helper.query(db, "SELECT * FROM sports");

        ArrayList<Sport> sportNames = new ArrayList<Sport>();
        
        cursor.moveToFirst();
        
        for(int a=0; a<cursor.getCount(); a++){
        	
        	sportNames.add(new Sport(cursor.getInt(0), cursor.getString(1)));
        	cursor.moveToNext();
        }
        
        //Close the Database and the Helper
        db.close();
        helper.close();
		
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
	
	public static ArrayList<Course> getAllCourses (Context context, int sid) throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		//Update Database
		courseUpdate(context, sid);
		
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
        
        Cursor cursor = helper.query(db, "SELECT * FROM courses WHERE sid="+sid);
        Cursor cursorSport = helper.query(db, "SELECT sid, name FROM sports WHERE sid="+sid);
        
        //create Sport-Object
        cursorSport.moveToFirst();
        Sport sport = new Sport(cursorSport.getInt(0), cursorSport.getString(1));
        
        
        //some definitions
        ArrayList<Course> courses = new ArrayList<Course>();
        boolean[] phases = new boolean[5];
        Date date; 
        
        cursor.moveToFirst();
        
        for(int a=0;a<cursor.getCount();a++){
        	
        	//create DateObject
        	date = new Date(cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
        	
        	//create Phases-Array
        	for(int b=0;b<5;b++){
        		phases[b] = (cursor.getInt(5+b) == 1) ? true : false;
        	}
        	
        	//subscription integer to boolean
        	boolean subscriptionRequired = (cursor.getInt(12) == 1) ? true : false;
        	
        	courses.add(new Course(cursor.getInt(0), sport, date, phases, cursor.getString(10), cursor.getString(11), subscriptionRequired, cursor.getInt(13)));
        	cursor.moveToNext();
        }
        
        return courses;
	}
	
	public static void addFavorite(Context context){
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("cid", 2);

        //Perform the insert
        db.insert(DBHelper.FAVORITES,null, values);
	}
	
	public static Course[] getAllFavorites(Context context){
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
        
		Cursor cursorFavorites = helper.query(db, "SELECT * FROM favorites");
        cursorFavorites.moveToFirst();
		
		Cursor cursor = helper.query(db, "SELECT * FROM courses WHERE cid="+cursorFavorites.getInt(1));
        cursor.moveToFirst();
        
        Cursor cursorSport = helper.query(db, "SELECT sid, name FROM sports WHERE sid="+cursor.getInt(1));
        
        //create Sport-Object
        cursorSport.moveToFirst();
        Sport sport = new Sport(cursorSport.getInt(0), cursorSport.getString(1));
        
        
        //some definitions
        Course[] courses = new Course[cursor.getCount()];
        boolean[] phases = new boolean[5];
        Date date; 
        
        cursor.moveToFirst();
        
        for(int a=0;a<cursor.getCount();a++){
        	
        	//create DateObject
        	date = new Date(cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
        	
        	//create Phases-Array
        	for(int b=0;b<5;b++){
        		phases[b] = (cursor.getInt(5+b) == 1) ? true : false;
        	}
        	
        	//subscription integer to boolean
        	boolean subscriptionRequired = (cursor.getInt(12) == 1) ? true : false;
        	
        	courses[a] = new Course(cursor.getInt(0), sport, date, phases, cursor.getString(10), cursor.getString(11), subscriptionRequired, cursor.getInt(13));
        	cursor.moveToNext();
        }
        
        return courses;
	}
}