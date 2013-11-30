package com.woodstocoasts.photostory;

import java.util.Date;

import android.R.bool;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditDetails extends Activity {


	
	EditText myPhotoTitle;
	EditText myPhotoDescription;
	TextView myPhotoID;
	TextView myPhotoTakeDateTime;
	TextView myPhotoLastUpdateDateTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_details);
		
		
	
	
		Bundle b = getIntent().getExtras();
		final long myID = b.getLong("ID");
		FillData(myID);
		
		ImageButton bUpdate = (ImageButton) findViewById(R.id.tbBtnUpdate);
		bUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				myPhotoTitle = (EditText)findViewById(R.id.etPhotoTitle);
				myPhotoDescription = (EditText)findViewById(R.id.etPhotoDescription);
				myPhotoID = (TextView)findViewById(R.id.tvPhotoID);
				myPhotoTakeDateTime = (TextView)findViewById(R.id.tvPhototakeDateTime);
				myPhotoLastUpdateDateTime = (TextView)findViewById(R.id.tvPhotoLastUpdateDateTime);
				Date date=new Date();
				
				DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
				databaseHelper.open();
				boolean b = databaseHelper.updatePhotoStreamRecord(myID, 
						
						Utility.getDateTimeUTCFromMillis(date.getTime(), "yyyy-MM-dd HH:mm:ss.SSSZ"), 
							myPhotoTitle.getText().toString(), 
							myPhotoDescription.getText().toString());
				FillData (myID);
				
				
			}
		});

	}
	
	
//	@Override
//	public void onDestroy(){
//		
//		databaseHelper.close();
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_details, menu);
		return true;
	}
	
	
	public void FillData (long ID){
		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();
		myPhotoTitle = (EditText)findViewById(R.id.etPhotoTitle);
		myPhotoDescription = (EditText)findViewById(R.id.etPhotoDescription);
		myPhotoID = (TextView)findViewById(R.id.tvPhotoID);
		myPhotoTakeDateTime = (TextView)findViewById(R.id.tvPhototakeDateTime);
		myPhotoLastUpdateDateTime = (TextView)findViewById(R.id.tvPhotoLastUpdateDateTime);

		Log.v("Edit:ID:", "" + ID);
		Cursor c = databaseHelper.fetchPhotoStreamRecordByID(ID);
		Log.v("MAREMMA MAIALA", ""+ c.getCount());
		if (c.moveToFirst()){
			
			myPhotoTitle.setText(c.getString(c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_Title)));
			myPhotoDescription.setText(c.getString(c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_Description)));
			myPhotoID.setText(Long.toString(c.getLong((c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_ID)))));
			myPhotoTakeDateTime.setText(c.getString(c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_CreationDateTimeUTC)));
			myPhotoLastUpdateDateTime.setText(c.getString(c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_UpdateDateTimeUTC)));
		}
		//c.close();
		//databaseHelper.close();
		
	}

}
