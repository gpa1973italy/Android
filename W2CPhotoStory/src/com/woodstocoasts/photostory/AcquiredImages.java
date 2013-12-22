package com.woodstocoasts.photostory;

import java.io.File;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
	
public	void populateAcquiredImages(){
		lvAcquiredImages = (ListView)findViewById(R.id.lvAcquireImages);
		
		/***
		 * Registro l'intercettazione del menu contestuale
		 */
		registerForContextMenu(lvAcquiredImages);
		
		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();
		
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
        
    
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		populateAcquiredImages();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acquired_images);
		
		populateAcquiredImages();
  
        
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		switch (v.getId()) {
		case R.id.lvAcquireImages:

			// Controllo che ci sia un elemento del ListAdapter > 0
			// per capire se c'è qualcosa sul DB e generare il menu!

			ListAdapter s = lvAcquiredImages.getAdapter();
			Log.v("ITEMONCREATE", "" + s.getItemId(0));

			if (s.getItemId(0) > 0) {

				Log.v("MENU", "btn1");
				menu.setHeaderTitle("Available Actions");
				menu.setHeaderIcon(R.drawable.ic_launcher);
				getMenuInflater().inflate(R.menu.acquired_images_context, menu);
			}
			break;

		}
	}

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
		lvAcquiredImages = (ListView)findViewById(R.id.lvAcquireImages);
        long id;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        
        switch (item.getItemId()){
        case R.id.ctxAcqImageEdit:
        	Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
        	//AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
             id = info.id;
             Log.v("ITEM:", "" + id);
            editAcquiredImage(id);
            break;
        	
        case R.id.ctxAcqImageDelete:
        	Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
        	
        	id = info.id;
        	Log.v("ITEM:", "" + id);
        	deleteAcquiredImage(id);
        	break;
        }
    return true;  
    }
	
	

	public void editAcquiredImage(long id) {

		
		Log.v("CLICCHETE", "ID: " + id);

		try {

			Log.v("CLICCHETE",
					"lvAcquiredImages.getChildAt(position).findViewById(R.id.detailsID): "
							+ id);
			Intent intent = new Intent(getApplicationContext(),
					EditDetails.class);
			Bundle b = new Bundle();
			b.putLong("ID", id); // Your id
			intent.putExtras(b); // Put your id to your next Intent
			startActivity(intent);
			// finish();

		} catch (Exception e) {
			Log.v("ERRORI", "Errore: " + e.toString());
		}

	}
	
	void deleteAcquiredImage(final long id){

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMessage("Do you want delete selected image?")
		.setTitle("Warning")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				evaluateDeleteResponse(which, id);
				
			}

		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				evaluateDeleteResponse(which, id);
				
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();	
		
	}
	
	/**
	 * Creare una UNDOBAR come Roman Nurik
	 * https://plus.google.com/+RomanNurik/posts/RA9WEEGWYp6
	 * https://code.google.com/p/romannurik-code/source/browse/misc/undobar
	 * http://theopentutorials.com/tutorials/android/android-sending-object-from-one-activity-to-another-using-parcelable/
	 * @param result
	 * @param ID
	 */
	
	private void evaluateDeleteResponse(int result, long ID){
		
		switch (result){
		case DialogInterface.BUTTON_POSITIVE:
			DBAdapter databaseHelper = new DBAdapter(
					getApplicationContext());
			databaseHelper.open();
			boolean b = databaseHelper.deletePhotoStreamRecord(ID);
			//boolean  b = false;
			Toast.makeText(getApplicationContext(), "Image deleted! {" + Boolean.toString(b) + "}", Toast.LENGTH_SHORT).show();
			databaseHelper.close();
			lvAcquiredImages = (ListView)findViewById(R.id.lvAcquireImages);
			lvAcquiredImages.invalidateViews();
			populateAcquiredImages();
			break;
			
		case DialogInterface.BUTTON_NEGATIVE:
			Toast.makeText(getApplicationContext(), "No change", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	
	
	
}
	
