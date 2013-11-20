package com.woodstocoasts.w2ccolorpicker;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter.ViewBinder;

@SuppressLint("NewApi")
public class ColorBrowser extends Activity {

	SimpleCursorAdapter adapter;
	ListView lvColors;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_browser);
		
		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();
		Cursor c = databaseHelper.fetchColorsByGroup(1);
		
		lvColors = (ListView) findViewById(R.id.lvColors);
		
		if (c.moveToFirst()) {
			adapter = new SimpleCursorAdapter(this, R.layout.lv_color_child, c, 
					new String[] {DBHelper.COLUMN_COLORS_ID, DBHelper.COLUMN_COLORS_ColorName, DBHelper.COLUMN_COLORS_Red, DBHelper.COLUMN_COLORS_Green, DBHelper.COLUMN_COLORS_Blue}, 
					new int[] {R.id.tvItemRGBSampleColor, R.id.tvItemColorName, R.id.tvItemColorR, R.id.tvItemColorG, R.id.tvItemColorB}, 0);
			
			/*
			 	"#" + String.format("%02X", r) + String.format("%02X", g) + String.format("%02X", b)
			 */
			
			/*
			adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
				
				@Override
				public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
					// TODO Auto-generated method stub
					
					String name = cursor.getColumnName(columnIndex);
					 if ("_id".equals(name)) {
				            int color = cursor.getInt(columnIndex);
				            view.setBackgroundColor(Color.rgb(color, color, color));
				            //view.findViewById(R.id.tvItemRGBSampleColor).setBackgroundColor(Color.rgb(color, color, color));
				            return true;
				        }
					
					return false;
				}
			});
			*/
			
		adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
				
				@Override
				public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
					// TODO Auto-generated method stub
					  
					/*
					if(view.getId() == R.id.tvItemRGBSampleColor)
					    {             
						  view.setBackgroundColor(Color.rgb(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)), cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green)), cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						  //Log.v("LVGPA", "#" + String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+ String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green))) + String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						  return true;
					    }
					  else if(view.getId() == R.id.tvItemColorHex)
					    {             
						  // "#" + String.format("%02X", r)
						  TextView tv = (TextView) view;
						  Log.v("LVGPA", "#" + String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+ String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green))) + String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						  tv.setText( "#" + String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+ String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green))) + String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
					    return true;
					    }
					  
					  
					  //Log.v("LVGPA", view.toString());
*/	
						boolean ii = false;
					switch (view.getId()) {
					case R.id.tvItemRGBSampleColor:
						view.setBackgroundColor(Color.rgb(
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Red)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Green)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
					//	return true;
						ii = true;
					case R.id.tvItemColorHex:
						TextView tv = (TextView) view;
						tv.setText("#" + String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+ String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green))) + String.format("%02X", cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
					//	return true;

					return true && ii;
					}

					//return false;
					return false;
				}
			});

			
			
			lvColors.setAdapter(adapter);
		}
		else
		{
			// siccome il movetofirst presuppone ci sia almeno un elemento...
			lvColors.setAdapter(null);
		}
		//c.close();
		databaseHelper.close();

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.color_browser, menu);
		return true;
	}

}
