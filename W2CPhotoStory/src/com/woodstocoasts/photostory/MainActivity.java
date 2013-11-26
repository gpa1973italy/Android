package com.woodstocoasts.photostory;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton mTbBtnBack = (ImageButton) findViewById(R.id.tbBtnBack);
		ImageButton mTbBtnMainApp = (ImageButton) findViewById(R.id.tbBtnMainApp);
		ImageButton mTbBtnDial = (ImageButton) findViewById(R.id.tbBtnDial);
		ImageButton mTbBtnPhoto = (ImageButton) findViewById(R.id.tbBtnPhoto);
		ImageButton mTbBtnCalendar = (ImageButton) findViewById(R.id.tbBtnCalendar);
		ImageButton mTbBtnMaps = (ImageButton) findViewById(R.id.tbBtnMaps);

		
		mTbBtnDial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent dial = new Intent();
				dial.setAction("android.intent.action.DIAL");
				dial.setData(Uri.parse("tel:"));
				startActivity(dial); 
				Toast.makeText(getApplicationContext(), "CIAO", Toast.LENGTH_LONG).show();
			}
		});
		
		mTbBtnCalendar.setOnClickListener(new OnClickListener() {
			
			@Override
			@SuppressLint("NewApi")
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/*
				//all version of android
			     Intent i = new Intent();

			     // mimeType will popup the chooser any  for any implementing application (e.g. the built in calendar or applications such as "Business calendar"
			     i.setType("vnd.android.cursor.item/event");
			     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 

			     // the time the event should start in millis. This example uses now as the start time and ends in 1 hour
			     //i.putExtra("beginTime", item.getBegin()); 
			     //i.putExtra("endTime", item.getEnd());
			     //i.putExtra("_id", item.getId());


			     // the action
			     //i.setAction(Intent.ACTION_PICK);
			     getApplicationContext().startActivity(i);
			     */
				
				/*
				// A date-time specified in milliseconds since the epoch.
				long startMillis = 0;
		
				Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
				builder.appendPath("time");
				ContentUris.appendId(builder, startMillis);
				Intent intent = new Intent(Intent.ACTION_VIEW)
				    .setData(builder.build());
				startActivity(intent);
				*/
				
				Calendar today = Calendar.getInstance();

	            Uri uriCalendar = Uri.parse("content://com.android.calendar/time/" + String.valueOf(today.getTimeInMillis()));
	            Intent intentCalendar = new Intent(Intent.ACTION_VIEW,uriCalendar);

	            //Use the native calendar app to view the date
	            startActivity(intentCalendar);
				
			}
		});
		
		
		mTbBtnMaps.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//String uri = "geo:"+ latitude + "," + longitude;
				//startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));

				// You can also choose to place a point like so:
				// String uri = "geo:"+ latitude + "," + longitude + "?q=my+street+address";
				String uri2 = "geo:?q=piazza puccini 26, firenze, italia";
				startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri2)));

/*
				*
				* The Possible Query params options are the following:
				*
				* Show map at location: geo:latitude,longitude
				* Show zoomed map at location: geo:latitude,longitude?z=zoom
				* Show map at locaiton with point: geo:0,0?q=my+street+address
				* Show map of businesses in area: geo:0,0?q=business+near+city
*/
				
				
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(this, PrefsActivity.class);
			startActivityForResult(i, 0);
			return true;
			
		}
		return false;

	}
	

}
