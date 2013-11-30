package com.woodstocoasts.photostory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class AcquiredImages extends Activity {

	SimpleCursorAdapter adapter;
	ListView lvAcquiredImages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acquired_images);
		
		lvAcquiredImages = (ListView)findViewById(R.id.lvAcquireImages);
		
		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();

		Cursor c = databaseHelper.fetchAllPhotoStreamRecords(DBHelper.COLUMN_PHOTOSTREAM_ID + " DESC");

        if (c.moveToFirst()) {
            adapter = new SimpleCursorAdapter(this, R.layout.acquired_images_details, c,
                            new String[] { 	DBHelper.COLUMN_PHOTOSTREAM_ID,
            								DBHelper.COLUMN_PHOTOSTREAM_Title,
            								DBHelper.COLUMN_PHOTOSTREAM_Description,
            								DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude,
            								DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude,
            								DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName
//            								,
//            								DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName
            								}
                                            , new int[] {
                                            R.id.detailsID,
                                            R.id.detailsTitle,
                                            R.id.detailsDescription,
                                            R.id.detailsLatitude,
                                            R.id.detailsLongitude,
                                            R.id.detailsPathFileName
//                                            ,
//                                            R.id.detailsImage
                                            }, 0);
            
   
            
            lvAcquiredImages.setAdapter(adapter);
        }
        else
        {
        	lvAcquiredImages.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{"Nessun elemento da visualizzare"}));
        }	
        
        lvAcquiredImages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int index, long arg3) {
				// TODO Auto-generated method stub
				try{
				
				 String str=lvAcquiredImages.getItemAtPosition(index).toString();
				 TextView tv = (TextView)lvAcquiredImages.getChildAt(index).findViewById(R.id.detailsPathFileName);
				 TextView tvID = (TextView)lvAcquiredImages.getChildAt(index).findViewById(R.id.detailsID);
				 Toast.makeText(getApplicationContext(), str + "\n" + tv.getText(), Toast.LENGTH_LONG).show();
				 
				 Intent intent = new Intent(AcquiredImages.this, EditDetails.class);
				 Bundle b = new Bundle();
				 b.putLong("ID", Long.parseLong(tvID.getText().toString())); //Your id
				 intent.putExtras(b); //Put your id to your next Intent
				 startActivity(intent);
				 //finish();
				 
				}
				catch (Exception e){
					Log.v("ERRORI", "Errore: " + e.toString());
				}
				return true;
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acquired_images, menu);
		return true;
	}

}
