package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBMethodes {

	private Network network;
	private DBHelper dbHelper;
	
	public DBMethodes(Context context) {
		this.network = new Network(context);
		this.dbHelper = new DBHelper(context);
	}
	
	/**
	 * sets up the Database in the first start with all sports and courses from the API.
	 * Actualises the Database only, when internet is available and the database is not up to date
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void setUpDatabase() {

		if (this.network.isOnline()) {

			if (this.isTableEmpty("sports"))
				this.sportUpdate(true);
			else
				this.sportUpdate(false);

			if (this.isTableEmpty("courses")) {

				for (int i = 0; i < this.getAllSport().size(); i++) {
					this.courseUpdate(this.getAllSport().get(i), true);
				}
			}
		}
	}

	/**
	 * Helpermethode of setUpDatabase. Updates or insert the local database with all sports
	 * @param update
	 */
	private void sportUpdate(boolean update) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();

		JsonSport json = new JsonSport();

		ArrayList<Sport> list;
		try {
			list = json.getAllSports();
			for (int a = 0; a < list.size(); a++) {

				// Setup new Content Values and assign some dummy content
				int sid = a + 1;

				values.put("SID", a + 1);
				values.put("NAME", list.get(a).getName());

				// Perform the insert/update
				if (update)
					db.insert(DBHelper.SPORTS, null, values);
				else
					db.update(DBHelper.SPORTS, values, "SID = " + sid, null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close the Database and the Helper
		dbHelper.close();
		db.close();
	}

	/**
	 * Helpermethode of setUpDatabase. Updates or inserts all courses from a certain sport
	 * @param sport
	 * @param update
	 */
	private void courseUpdate(Sport sport, boolean update) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();

		JsonCourse json = new JsonCourse();

		try {
			ArrayList<Course> allCourses = json.getAllCourses(sport);
			Course course;

			for (int a = 0; a < allCourses.size(); a++) {
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
				values.put("imageLink", course.getImageLink());

				if (update)
					db.insert(DBHelper.COURSES, null, values);
				else
					db.update(DBHelper.COURSES, values, "COURSENAME = \"" + course.getName() + "\"", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close the Database and the Helper
		dbHelper.close();
		db.close();
	}

	/**
	 * returns a list with all sport names.
	 * @return
	 */
	public ArrayList<Sport> getAllSport() {

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = dbHelper.query(db, "SELECT * FROM sports");

		ArrayList<Sport> sportNames = new ArrayList<Sport>();

		cursor.moveToFirst();

		for (int a = 0; a < cursor.getCount(); a++) {

			sportNames.add(new Sport(cursor.getInt(0), cursor.getString(1)));
			cursor.moveToNext();
		}

		// Close the Database and the Helper
		db.close();
		dbHelper.close();

		return sportNames;
	}

	/**
	 * return a list of all Courses from a certain Sport
	 * 
	 * @param context
	 * @param sid
	 * @return ArryList<Course>
	 */
	public ArrayList<Course> getAllCourses(Sport sport) {

		// If Network is connected to internet, Updating the database

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int sid = sport.getId();

		Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE sid=" + sid);

		// some definitions
		ArrayList<Course> courses = new ArrayList<Course>();

		cursor.moveToFirst();

		for (int a = 0; a < cursor.getCount(); a++) {
			courses.add(new Course(cursor.getInt(0), sport, cursor.getString(2), cursor.getString(3), cursor
					.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
					sub(cursor.getInt(8)), cursor.getString(9), cursor.getString(10)));
			cursor.moveToNext();
		}

		return courses;
	}

	/**
	 * Returns a Course-Object for a certain course-id
	 * @param courseId
	 * @return
	 */
	public Course getCourse(int courseId) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE cid=" + courseId);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return null;

		else {
			Cursor cursorSport = dbHelper.query(db, "SELECT * FROM sports WHERE sid=" + cursor.getInt(1));
			cursorSport.moveToFirst();
			if(cursorSport.getCount() == 0) return null;
			else{
		
				return new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport.getString(1)),
						cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor.getString(9),
						cursor.getString(10));
			}
		}
	}

	/**
	 * Add a course to the favorite-table in the local database
	 * @param course
	 */
	public void addFavorite(Course course) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("cid", course.getId());

		if (!this.isFavourite(course))
			db.insert(DBHelper.FAVORITES, null, values);
	}

	/**
	 * Delete a course from the favorite-table in the local database
	 * @param course
	 */
	public void deleteFavorite(Course course) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(DBHelper.FAVORITES, "cid = " + Integer.toString(course.getId()), null);
	}

	/**
	 * checks if a certain course is a favorite course
	 * @param course
	 * @return
	 */
	public boolean isFavourite(Course course) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = dbHelper.query(
				db,
				"SELECT * FROM courses JOIN favorites ON courses.cid = favorites.cid  WHERE courses.cid=\""
						+ course.getId() + "\"");
		if (cursor.getCount() == 0)
			return false;

		else
			return true;
	}

	/**
	 * returns a list of all favorite courses
	 * @return
	 */
	public ArrayList<Course> getAllFavorites() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursorFavorites = dbHelper.query(db, "SELECT * FROM favorites");
		cursorFavorites.moveToFirst();

		ArrayList<Course> allFavorites = new ArrayList<Course>();

		for (int a = 0; a < cursorFavorites.getCount(); a++) {

			Cursor cursor = dbHelper.query(db, "SELECT * FROM courses WHERE cid=" + cursorFavorites.getInt(1));
			cursor.moveToFirst();

			Cursor cursorSport = dbHelper.query(db, "SELECT sid, name FROM sports WHERE sid=" + cursor.getInt(1));
			cursorSport.moveToFirst();

			allFavorites.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport.getString(1)),
					cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor
							.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor.getString(9), cursor
							.getString(10)));

			cursorFavorites.moveToNext();
		}

		return allFavorites;
	}

	/**
	 * saves the rating from a certain course in the local database
	 * @param course
	 * @param rating
	 */
	public void setRating(Course course, float rating) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("cid", course.getId());
		values.put("rating", rating);

		if (db.update(DBHelper.RATING, values, "CID = " + course.getId(), null) == 0) {
			db.insert(DBHelper.RATING, null, values);
		}
	}

	/**
	 * returns the rating of a certain course
	 * @param course
	 * @return
	 */
	public int getRating(Course course) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = dbHelper.query(db, "SELECT * FROM rating WHERE cid=" + course.getId());
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return 0;

		else
			return cursor.getInt(2);
	}

	/**
	 * Helper-Methode to check if a certain Table is empty
	 * @param table
	 * @return
	 */
	protected boolean isTableEmpty(String table) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + table, null);
		cursor.moveToFirst();

		if (cursor.getInt(0) == 0)
			return true;
		else
			return false;
	}

	/**
	 * Searching in all sports
	 * @param search
	 * @return
	 */
	public ArrayList<IEvent> searchSport(String search) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = dbHelper.query(db, "SELECT * FROM sports WHERE name LIKE \'%" + search + "%\'");

		ArrayList<IEvent> sportNames = new ArrayList<IEvent>();

		cursor.moveToFirst();

		for (int a = 0; a < cursor.getCount(); a++) {

			sportNames.add(new Sport(cursor.getInt(0), cursor.getString(1)));
			cursor.moveToNext();
		}

		// Close the Database and the Helper
		db.close();
		dbHelper.close();

		return sportNames;
	}

	/**
	 * Search in all courses
	 * @param day
	 *            from 0 to 7: any day, mo, tu, we, .. so
	 * @param time
	 *            from 0 to 3: whole day, 6:00 to 12:00, 10:00 to 14:00, 12:00
	 *            to 18:00, 16:00 to 6:00
	 * 
	 * @return an ArrayList<Sport> matching the criteria
	 */
	public ArrayList<IEvent> searchCourse(int day, int time) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = dbHelper.query(db, "SELECT * FROM courses");
		String[] dayNames = new String[] { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };

		ArrayList<IEvent> courseSearchResult = new ArrayList<IEvent>();

		cursor.moveToFirst();

		for (int a = 0; a < cursor.getCount(); a++) {
			Cursor cursorSport = dbHelper.query(db, "SELECT sid, name FROM sports WHERE sid=" + cursor.getInt(1));
			cursorSport.moveToFirst();

			int index;
			if (day == 0)
				index = 0;
			else
				index = day - 1;

			// if certain Day is equal or any time
			if (cursor.getString(3).equals(dayNames[index]) || day == 0) {
				switch (time) {
				case 0:
					courseSearchResult.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport
							.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor
							.getString(5), cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor
							.getString(9), cursor.getString(10)));
					break;
				case 1:
					if (getHour(cursor.getString(4)) >= 600 && getHour(cursor.getString(4)) <= 1215) {
						courseSearchResult.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport
								.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor
								.getString(5), cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor
								.getString(9), cursor.getString(10)));
					}
					break;

				case 2:
					if (getHour(cursor.getString(4)) >= 1000 && getHour(cursor.getString(4)) <= 1400) {
						courseSearchResult.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport
								.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor
								.getString(5), cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor
								.getString(9), cursor.getString(10)));
					}
					break;

				case 3:
					if (getHour(cursor.getString(4)) >= 1200 && getHour(cursor.getString(4)) <= 1800) {
						courseSearchResult.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport
								.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor
								.getString(5), cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor
								.getString(9), cursor.getString(10)));
					}
					break;

				case 4:
					if ((getHour(cursor.getString(4)) >= 1600 && getHour(cursor.getString(4)) <= 2400)
							|| (getHour(cursor.getString(4)) >= 0000 && getHour(cursor.getString(4)) <= 600)) {
						courseSearchResult.add(new Course(cursor.getInt(0), new Sport(cursorSport.getInt(0), cursorSport
								.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor
								.getString(5), cursor.getString(6), cursor.getString(7), sub(cursor.getInt(8)), cursor
								.getString(9), cursor.getString(10)));
					}
					break;
				}
				cursor.moveToNext();
			} else
				cursor.moveToNext();
		}

		// Close the Database and the Helper
		db.close();
		dbHelper.close();

		return courseSearchResult;
	}

	/**
	 * Helper-Methode to parse the time string from the API to an integer time value
	 * @param time
	 * @return
	 */
	private int getHour(String time) {

		try {
			if (time.length() < 6)
				return 100000;
			else {
				if (Integer.parseInt(time.substring(0, 1)) == 0)
					return 100 * Integer.parseInt(time.substring(1, 2)) + Integer.parseInt(time.substring(3, 5));
				else
					return 100 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3, 5));
			}
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	/**
	 * Helper-Methode to parse int to boolean
	 * @param i
	 * @return
	 */
	private boolean sub(int i) {
		boolean sub;
		if (i == 1)
			sub = true;
		else
			sub = false;
		return sub;
	}
}