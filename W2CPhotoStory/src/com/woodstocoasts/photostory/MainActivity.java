package com.woodstocoasts.photostory;

import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.woodstocoasts.photostory.Utility;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity {
	int IMAGE_CAPTURE;
	static final String TAG = "W2CPhotoStory";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			Utility.BackupDatabase(getApplicationContext());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ImageButton mTbBtnBack = (ImageButton) findViewById(R.id.tbBtnBack);
		ImageButton mTbBtnMainApp = (ImageButton) findViewById(R.id.tbBtnMainApp);
		ImageButton mTbBtnDial = (ImageButton) findViewById(R.id.tbBtnDial);
		ImageButton mTbBtnPhoto = (ImageButton) findViewById(R.id.tbBtnPhoto);
		ImageButton mTbBtnCalendar = (ImageButton) findViewById(R.id.tbBtnCalendar);
		ImageButton mTbBtnMaps = (ImageButton) findViewById(R.id.tbBtnMaps);

		// mTbBtnPhoto.setImageResource(R.drawable.tb_photo_pressed);

		Button mTakePic = (Button) findViewById(R.id.button1);
		mTakePic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, IMAGE_CAPTURE);

			}
		});

		Button mSeeAcquireImages = (Button) findViewById(R.id.button2);

		mSeeAcquireImages.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
				// Creo l'Intent
				Intent i = new Intent(getApplicationContext(),
						AcquiredImages.class);

				// Lancio l'Intent
				startActivity(i);
			}
		});

		mTbBtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
				finish();
			}
		});

		mTbBtnMainApp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
				try {
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Avvio Home Screen non disponibile",
							Toast.LENGTH_LONG).show();
					Log.v(TAG,
							"Starting Intent HomeScreen Error: \n"
									+ e.getMessage());
				}
			}
		});

		mTbBtnDial.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
				try {

					Intent dial = new Intent();
					dial.setAction("android.intent.action.DIAL");
					dial.setData(Uri.parse("tel:"));
					startActivity(dial);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Chiamata non disponibile", Toast.LENGTH_LONG)
							.show();
					Log.v(TAG,
							"Starting Intent CallNumber Error: \n"
									+ e.getMessage());

				}
			}
		});

		mTbBtnPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();
			}
		});

		mTbBtnCalendar.setOnClickListener(new OnClickListener() {

			@Override
			@SuppressLint("NewApi")
			public void onClick(View v) {
				// TODO Auto-generated method stub
				VibrateAlert();

				try {
					Calendar today = Calendar.getInstance();

					Uri uriCalendar = Uri
							.parse("content://com.android.calendar/time/"
									+ String.valueOf(today.getTimeInMillis()));
					Intent intentCalendar = new Intent(Intent.ACTION_VIEW,
							uriCalendar);

					// Use the native calendar app to view the date
					startActivity(intentCalendar);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Calendario non disponibile", Toast.LENGTH_LONG)
							.show();
					Log.v(TAG,
							"Starting Intent Calendar Error: \n"
									+ e.getMessage());
				}

			}
		});

		mTbBtnMaps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// String uri = "geo:"+ latitude + "," + longitude;
				// startActivity(new Intent(android.content.Intent.ACTION_VIEW,
				// Uri.parse(uri)));

				// You can also choose to place a point like so:
				// String uri = "geo:"+ latitude + "," + longitude +
				// "?q=my+street+address";
				try {
					String uri2 = "geo:?q=piazza puccini 26, firenze, italia";
					startActivity(new Intent(
							android.content.Intent.ACTION_VIEW, Uri.parse(uri2)));
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Mappe non disponibili", Toast.LENGTH_LONG).show();
					Log.v(TAG,
							"Starting Intent Maps Error: \n" + e.getMessage());

				}
				/*
				 * 
				 * The Possible Query params options are the following:
				 * 
				 * Show map at location: geo:latitude,longitude Show zoomed map
				 * at location: geo:latitude,longitude?z=zoom Show map at
				 * locaiton with point: geo:0,0?q=my+street+address Show map of
				 * businesses in area: geo:0,0?q=business+near+city
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

	// http://stackoverflow.com/questions/7282426/how-to-get-image-name-using-camera-intent-in-android
	// http://developer.android.com/reference/android/app/Activity.html#startActivityForResult(android.content.Intent,
	// int)
	// http://developer.android.com/training/camera/photobasics.html
	// http://developer.android.com/reference/android/util/SparseBooleanArray.html
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IMAGE_CAPTURE) {
			if (resultCode == RESULT_OK) {
				Log.d(TAG, "Picture taken!!!");
				// imageView.setImageURI(imageUri);
				int k = getLastImageId();
				Toast.makeText(getApplicationContext(), "" + String.valueOf(k),
						Toast.LENGTH_LONG).show();

			}
		}
	}

	// http://stackoverflow.com/questions/7636697/get-path-and-filename-from-camera-intent-result

	@SuppressLint("NewApi")
	private int getLastImageId() {
		Cursor c;
		final String[] imageColumns = { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA,
				MediaStore.Images.Media.MIME_TYPE,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.LATITUDE,
				MediaStore.Images.Media.LONGITUDE,
				MediaStore.Images.Media.MINI_THUMB_MAGIC,
				MediaStore.Images.Media.DATE_TAKEN };
		final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";

		if (android.os.Build.VERSION.SDK_INT >= 11) {
			CursorLoader imageCursor = new CursorLoader(
					getApplicationContext(),
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns,
					null, null, imageOrderBy);
			c = imageCursor.loadInBackground();

		} else {
			// Get the base URI for the People table in the Contacts content
			// provider.
			Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

			// Make the query.
			ContentResolver cr = getContentResolver();
			c = cr.query(images, imageColumns, // Which columns to return
					"", // Which rows to return (all rows)
					null, // Selection arguments (none)
					imageOrderBy // Ordering
			);

		}

		if (c.moveToFirst()) {
			int id = c.getInt(c.getColumnIndex(MediaStore.Images.Media._ID));
			String fullPath = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.DATA));
			String mimeType = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.MIME_TYPE));
			String displayName = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
			String latitude = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.LATITUDE));
			String longitude = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.LONGITUDE));
			long takedate = c.getLong(c
					.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
			String tbmagig = c.getString(c
					.getColumnIndex(MediaStore.Images.Media.MINI_THUMB_MAGIC));
			Log.d(TAG, "getLastImageId::id " + id);
			Log.d(TAG, "getLastImageId::path " + fullPath);
			Log.d(TAG, "getLastImageId::MIME " + mimeType);
			Log.d(TAG, "getLastImageId::NAME " + displayName);
			Log.d(TAG, "getLastImageID::Latitude " + latitude);
			Log.d(TAG, "getLastImageID::Longitude " + longitude);
			Log.d(TAG, "getLastImageID::THUMB MAGIC " + tbmagig);
			Log.d(TAG,
					"getLastImageID::DateTake "
							+ Utility.getDateTimeUTCFromMillis(takedate,
									"yyyy-MM-dd HH:mm:ss.SSSZ"));

			// MediaStore.Images.Thumbnails.MICRO_KIND

			if (latitude == null) {
				latitude = "0";
			}

			if (longitude == null) {
				longitude = "0";
			}

			DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
			databaseHelper.open();

			databaseHelper.createPhotoStreamRecord("GPA", 0, 0, Utility
					.getDateTimeUTCFromMillis(takedate,
							"yyyy-MM-dd HH:mm:ss.SSSZ"), Utility
					.getDateTimeUTCFromMillis(takedate,
							"yyyy-MM-dd HH:mm:ss.SSSZ"), null, null, 0, false,
					false, null, fullPath, Float.parseFloat(latitude), Float
							.parseFloat(longitude), 0f);

			databaseHelper.close();

			c.close();
			return id;
		} else {
			return 0;
		}
	}

	public void VibrateAlert() {
		try {
			Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

			long[] vibpat = new long[] { 0, 1000, 0, 500 };

			vib.vibrate(vibpat, 2);
			// vib.vibrate(1000);

			vib.cancel();
		} catch (Exception e) {
			Log.v(TAG, "Cannot vibrate! \n" + e.getMessage().toString());
		}

	}
}