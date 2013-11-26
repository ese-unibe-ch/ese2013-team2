package com.example.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Android ProgressBar example.
 * @author Prabu Dhanapal
 * @version 1.0
 * @since Sep 01 2013
 *
 */
public class MainActivity extends Activity {
	private ProgressBar progressBar;
	private int progressStatus = 0;
	private TextView textView;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		textView = (TextView) findViewById(R.id.textView1);
		// Start long running operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (progressStatus < 100) {
					progressStatus += 1;
					// Update the progress bar and display the current value in the text view
					handler.post(new Runnable() {
						public void run() {
							progressBar.setProgress(progressStatus);
							textView.setText(progressStatus+"/"+progressBar.getMax());
						}
					});
					try {
						// Sleep for 200 milliseconds. Just to display the progress slowly
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
