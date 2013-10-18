package ch.unibe.unisportbern.support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBMethodes {
	
	public DBMethodes(){
		
	}
	
	public static void sportUpdate(Context context){
		
		DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        String[] jSonResult = new String[]{"Orientierungslauf", "Fussball", "Badmington", "Schlegle!"};
        
        for(int a=0;a<jSonResult.length;a++){

	        //Setup new Content Values and assign some dummy content
	
	        values.put("SID", a);
	        values.put("NAME", jSonResult[a]);
	
	        //Perform the insert
	        db.insert(DBHelper.SPORTS,null, values);
        }
        
        //Close the Database and the Helper
        dbHelper.close();
        db.close();
	}
	
public static void courseUpdate(Context context){
		
		DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        String[] jSonResult = new String[]{"Orientierungslauf", "Fussball", "Badmington", "Schlegle!", "Houe!"};
        
        for(int a=0;a<jSonResult.length;a++){

	        //Setup new Content Values and assign some dummy content
	
	        values.put("SID", a);
	        values.put("NAME", jSonResult[a]);
	
	        //Perform the insert
	        db.insert(DBHelper.SPORTS,null, values);
        }
        
        //Close the Database and the Helper
        dbHelper.close();
        db.close();
	}
	
	
	public static Sport[] getAllSport(Context context){
		
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
        
        Cursor cursor = helper.query(db, "SELECT * FROM sports");

        Sport []sportNames = new Sport[cursor.getCount()];
        
        cursor.moveToFirst();
        
        for(int a=0; a<sportNames.length; a++){
        	
        	sportNames[a] = new Sport(cursor.getInt(0), cursor.getString(1));
        	cursor.moveToNext();
        }
        
        //Close the Database and the Helper
        db.close();
        helper.close();
		
		return sportNames;
	}

	public static Course[] getAllCourses (Context context, int sid){
		DBHelper helper = new DBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
        
        Cursor cursor = helper.query(db, "SELECT * FROM courses WHERE sid =\""+sid+"\"");
        Cursor cursorSport = helper.query(db, "SELECT sid, name FROM sports WHERE sid=\""+sid+"\"");
        
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
        
        
        /*for(int a=0; a<courses.length; a++){
        	courses[a] = new Course(cursor.getString(0), sport, );
        	cursor.moveToNext();
        }*/
        
        return courses;
	}
	
}
