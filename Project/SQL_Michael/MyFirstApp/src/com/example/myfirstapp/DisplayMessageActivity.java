package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DisplayMessageActivity extends Activity {
	
	String[] searchObjects = {"Lea", "Michael","Karan","Masi"};
	
	@SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        
        
        //SQL Part
        DBHelper dbHelper = new DBHelper(DisplayMessageActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        
        ContentValues values = new ContentValues();
        
        int b = searchObjects.length;
        
        for(int a=0;a<b;a++){

	        //Setup new Content Values and assign some dummy content
	
	        values.put("AID", a);
	        values.put("TEST", searchObjects[a]);
	
	        //Perform the insert
	        db.update(DBHelper.TABLE, values,"AID="+a,null);
        }
        
        Cursor cursor = dbHelper.query(db, "SELECT aid, test FROM test_table WHERE test=\""+message+"\"");
        cursor.moveToFirst();
        
        String tester;
        tester = cursor.getString(1);

        //Close the Database and the Helper
        db.close();
        dbHelper.close();
        
     	// Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(tester);
    	
   	 	// Set the text view as the activity layout
        setContentView(textView);
        
        //iterate over whole table content
        //Cursor cursor = dbHelper.query(db, "SELECT aid, test FROM test_table");

        /*cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
        	 message = cursor.getString(0); //Column 0 (AID)
        	 textView.setText(message);
        	
        	 // Set the text view as the activity layout
             //setContentView(textView);
        	 cursor.moveToNext();
        }
        cursor.close(); */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}