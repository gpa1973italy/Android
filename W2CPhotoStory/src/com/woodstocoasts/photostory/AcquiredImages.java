package com.woodstocoasts.photostory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class AcquiredImages extends Activity {

	SimpleCursorAdapter adapter;
	ListView lvAcquiredImages;
	
	public class SingleImageReference {
		private File _FileName;
		private View _ViewID;

		public SingleImageReference(File f, View v) {
			// TODO Auto-generated constructor stub
			_FileName = f;
			_ViewID = v;
		}
		
		public void setFileName(File filename) {
			this._FileName = filename;
		}

		public File getFileName() {
			return _FileName;
		}

		public void setViewName(View v) {
			this._ViewID = v;
		}

		public View getViewName() {
			return _ViewID;
		}
	}
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acquired_images);
		
		lvAcquiredImages = (ListView)findViewById(R.id.lvAcquireImages);
		
		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();
		
		final ArrayList<SingleImageReference> imageToRender = new ArrayList<SingleImageReference>();
		
		final Cursor c = databaseHelper.fetchAllPhotoStreamRecords(DBHelper.COLUMN_PHOTOSTREAM_ID + " DESC");

        if (c.moveToFirst()) {
            adapter = new SimpleCursorAdapter(this, R.layout.acquired_images_details, c,
                            new String[] { 	DBHelper.COLUMN_PHOTOSTREAM_ID,
            								DBHelper.COLUMN_PHOTOSTREAM_Title,
            								DBHelper.COLUMN_PHOTOSTREAM_Description,
            								DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude,
            								DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude,
            								DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName
            								,
            								DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName
            								}
                                            , new int[] {
                                            R.id.detailsID,
                                            R.id.detailsTitle,
                                            R.id.detailsDescription,
                                            R.id.detailsLatitude,
                                            R.id.detailsLongitude,
                                            R.id.detailsPathFileName
                                            ,
                                            R.id.detailsImage
                                            }, 0);
            
   
            
            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
				
				@Override
				public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
					
					// TODO Auto-generated method stub
				      if(view.getId() == R.id.detailsImage){
				           //...
				    	  Log.v("FileToDecode", "ViewID>>> " + view.getId() );
				    	  File f = new File(c.getString(c.getColumnIndex(DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName)));
				    	  Log.v("FileToDecode", ">>> " + f.toString() );
				         
				    	  ((ImageView)view).setImageBitmap(( Utility.decodeFile(f)));
				    	  
				    	  //imageToRender.add(new SingleImageReference(f, view));
							
				    	  // ((ImageView)view).setImageBitmap(null);
				    	  // LdImage task = new LdImage();
				    	  // task.execute(f);
							
							
				           return true; //true because the data was bound to the view
				       }
					
					return false;
				}
			});
            
            
            lvAcquiredImages.setAdapter(adapter);
        }
        else
        {
        	lvAcquiredImages.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{"Nessun elemento da visualizzare"}));
        }	
        
        lvAcquiredImages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
				Log.v("CLICCHETE", "AdapterView: " + arg0.toString());
				Log.v("CLICCHETE", "View: " + view.toString());
				Log.v("CLICCHETE", "Position: " + position);
				Log.v("CLICCHETE", "ID: " + id);
				
				 
				try{
				
// questo codice manda in "bambola" il click lungo se fatto sull'inizio lista :/
//				 TextView tv = (TextView)lvAcquiredImages.getChildAt(position).findViewById(R.id.detailsPathFileName);
//				 TextView tvID = (TextView)lvAcquiredImages.getChildAt(position).findViewById(R.id.detailsID);
//				 Toast.makeText(getApplicationContext(), "detailsID: " + tvID.getText() + "\n" + "detailsFilePath: " + tv.getText(), Toast.LENGTH_LONG).show();
				

				 Log.v("CLICCHETE", "lvAcquiredImages.getChildAt(position).findViewById(R.id.detailsID): " + id);
				 Intent intent = new Intent(getApplicationContext(), EditDetails.class);
				 Bundle b = new Bundle();
				 b.putLong("ID", id); //Your id
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
        
        
        
        ImageButton btnBack = (ImageButton)findViewById(R.id.tbBtnBack);
        btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acquired_images, menu);
		return true;
	}
	

//-------------------------------------
//	http://stackoverflow.com/questions/7729133/using-asynctask-to-load-images-in-listview
	
	private class LdImage extends AsyncTask<File, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(File... params) {
			// TODO Auto-generated method stub
			Bitmap response = null;
			for (File f : params) {
				response = Utility.decodeFile(f);
			}
			return response;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			ImageView myPhoto = (ImageView)findViewById(R.id.detailsImage);
			myPhoto.setImageBitmap(result);
		}
	}
//--------------------------------------------------------------
	
}
