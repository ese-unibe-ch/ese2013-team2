package ch.unibe.unisportbern.support;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String TAG = DBHelper.class.getSimpleName();
	public static final String DBUNISPORT = "unisport.db";
	public static final int DB_VERS = 1;
	public static final String COURSES = "courses";
	public static final String SPORTS = "sports";
	public static final String FAVORITES = "favorites";
	public static final String RATING = "rating";
	public static final String USER = "user";
	
	public static final boolean Debug = false;
	public DBHelper(Context context) {
		super(context, DBUNISPORT, null, DB_VERS);
	}
	
	public Cursor query(SQLiteDatabase db, String query) {
		Cursor cursor = db.rawQuery(query, null);
		if (Debug) {
			Log.d(TAG, "Executing Query: "+ query);
		}
		return cursor;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Create table Logic, once the Application has ran for the first time. */
		String sql = String.format("CREATE TABLE %s (sid INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)", SPORTS);
		db.execSQL(sql);
		
		sql = String.format("CREATE TABLE %s (cid INTEGER PRIMARY KEY AUTOINCREMENT, sid INTEGER, coursename TEXT, day TEXT, time TEXT, phases TEXT, location TEXT, information TEXT, sub INTEGER, kew STRING, imageLink STRING)", COURSES);
		db.execSQL(sql);
		
		sql = String.format("CREATE TABLE %s (fid INTEGER PRIMARY KEY AUTOINCREMENT, cid INTEGER)", FAVORITES);
		db.execSQL(sql);
		
		sql = String.format("CREATE TABLE %s (rid INTEGER PRIMARY KEY AUTOINCREMENT, cid INTEGER, rating INTEGER)", RATING);
		db.execSQL(sql);
		
		sql = String.format("CREATE TABLE %s (uid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)", USER);
		
		if (Debug) {
			Log.d(TAG, "onCreate Called.");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", SPORTS));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", COURSES));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", FAVORITES));
		if (Debug) {
			Log.d(TAG, "Upgrade: Dropping Table and Calling onCreate");
		}
		this.onCreate(db);
		
	}

}