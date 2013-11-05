package ch.unibe.unisportbern.test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.JsonSport;
import ch.unibe.unisportbern.support.Sport;
import ch.unibe.unisportbern.views.DetailsActivity;
import android.test.AndroidTestCase;
import android.os.AsyncTask;

public class JsonTestCase extends AndroidTestCase {
	JsonSport json;
	DBMethodes db;
	CountDownLatch signal = new CountDownLatch(1);
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = new DBMethodes(getContext());
		json = new JsonSport();
		assertNotNull(db);
		assertNotNull(json);   
     }
	
	@Override
    protected void tearDown() throws Exception {
            super.tearDown();
            
    }
	
	
	
    public void testJson() throws InterruptedException, JSONException, ExecutionException, TimeoutException {
        
        ArrayList<Sport> jsonsports = new ArrayList<Sport>();
        /*jsonsports = json.getAllSports();
        signal.await(300, TimeUnit.MILLISECONDS);
        assertTrue("AsyncTask has finished", json.getStatus() ==  AsyncTask.Status.FINISHED);*/
        ArrayList<Sport> dbsports = new ArrayList<Sport>();
        dbsports = db.getAllSport();
        assertNotNull(jsonsports);
        assertNotNull(db);
        assertEquals("Json loaded sports should be the same as database sports", jsonsports, dbsports);
	}

	
}
