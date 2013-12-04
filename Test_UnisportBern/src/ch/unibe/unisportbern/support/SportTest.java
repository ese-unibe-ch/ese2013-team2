package ch.unibe.unisportbern.support;

import android.test.AndroidTestCase;

public class SportTest extends AndroidTestCase {

	Sport sport;
	Course course;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//course = new Course(2, sport, "Badminton", "Di", "20:30", "phases", "location", "information", true, "kew");

		sport = new Sport(0, "Sport");
	}

	public void testInit() {
		//course = new Course(2, sport, "Badminton", "Di", "20:30", "phases", "location", "information", true, "kew");

		//sport = new Sport(0, "Sport");

		assertEquals(0, sport.getId());
		assertEquals("Sport", sport.getName());

		//assertEquals(sport, course.getSport());
		assertEquals("Di", course.getDay());
		assertEquals("Badminton Di (20:30)", course.toString());
	}

}
