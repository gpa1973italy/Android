package com.woodstocoasts.w2ccolorpicker;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ColorBrowser extends Activity {

	SimpleCursorAdapter adapter;
	ListView lvColors;
	Spinner spColorGroups;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_browser);

		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();

		Cursor c = databaseHelper.fetchAllColorsGroups();

		Spinner spColorGroups = (Spinner) findViewById(R.id.spinnerColorGroups);

		if (c.moveToFirst()) {
			adapter = new SimpleCursorAdapter(this,
					R.layout.lv_color_group_child, c, new String[] {
							DBHelper.COLUMN_COLORSGROUP_ColorGroup,
							DBHelper.COLUMN_COLORSGROUP_ID }, new int[] {
							R.id.tvItemColorGroupName,R.id.tvItemColorGroupID }, 0);
		}

		spColorGroups.setAdapter(adapter);

		/*
		c = null;

		c = databaseHelper.fetchColorsByGroup(1);
		lvColors = (ListView) findViewById(R.id.lvColors);

		if (c.moveToFirst()) {
			adapter = new SimpleCursorAdapter(this, R.layout.lv_color_child, c,
					new String[] { DBHelper.COLUMN_COLORS_ID,
							DBHelper.COLUMN_COLORS_ColorName,
							DBHelper.COLUMN_COLORS_Red,
							DBHelper.COLUMN_COLORS_Green,
							DBHelper.COLUMN_COLORS_Blue,
							DBHelper.COLUMN_COLORS_Hex
							}, new int[] {
							R.id.tvMessage, R.id.tvItemColorName,
							R.id.tvItemColorR, R.id.tvItemColorG,
							R.id.tvItemColorB,
							R.id.tvItemColorHex
							}, 0);

			adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

				@Override
				public boolean setViewValue(View view, Cursor cursor,
						int columnIndex) {
					// TODO Auto-generated method stub
					Log.v("GPA", "" + columnIndex);
					switch (columnIndex) {
					case 0:
						view.setBackgroundColor(Color.rgb(
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Red)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Green)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						TextView t1 = (TextView) view;
						t1.setText(Integer.toString(cursor.getInt(cursor
								.getColumnIndex(DBHelper.COLUMN_COLORS_ID))));
						return true;

						 case 6:
						 TextView tv3 = (TextView) view;
						tv3.setText("#" +
						 String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+
					 String.format("%02X",
					 cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green)))
					 + String.format("%02X",
					 cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						return true;
					
					}

					// return false;
					return false;
				}
			});

			
			lvColors.setAdapter(adapter);
		} else {
			// siccome il movetofirst presuppone ci sia almeno un elemento...
			lvColors.setAdapter(null);
		}
		// c.close();
		databaseHelper.close();
		*/
		
		spColorGroups.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
					TextView s = (TextView)view.findViewById(R.id.tvItemColorGroupID);
				
				Toast.makeText(getApplicationContext(), "Ecco\n\n"+s.getText().toString(), Toast.LENGTH_LONG).show();
				
				FillList(Integer.parseInt(s.getText().toString()));
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.color_browser, menu);
		return true;
	}
	
	
	public void FillList (int i){

		DBAdapter databaseHelper = new DBAdapter(getApplicationContext());
		databaseHelper.open();
		
		Cursor c = databaseHelper.fetchColorsByGroup(i);
		
		lvColors = (ListView) findViewById(R.id.lvColors);

		if (c.moveToFirst()) {
			adapter = new SimpleCursorAdapter(this, R.layout.lv_color_child, c,
					new String[] { DBHelper.COLUMN_COLORS_ID,
							DBHelper.COLUMN_COLORS_ColorName,
							DBHelper.COLUMN_COLORS_Red,
							DBHelper.COLUMN_COLORS_Green,
							DBHelper.COLUMN_COLORS_Blue,
							DBHelper.COLUMN_COLORS_Hex
							}, new int[] {
							R.id.tvMessage, R.id.tvItemColorName,
							R.id.tvItemColorR, R.id.tvItemColorG,
							R.id.tvItemColorB,
							R.id.tvItemColorHex
							}, 0);

			adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

				@Override
				public boolean setViewValue(View view, Cursor cursor,
						int columnIndex) {
					// TODO Auto-generated method stub
					Log.v("GPA", "" + columnIndex);
					switch (columnIndex) {
					case 0:
						view.setBackgroundColor(Color.rgb(
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Red)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Green)),
								cursor.getInt(cursor
										.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						TextView t1 = (TextView) view;
						t1.setText(Integer.toString(cursor.getInt(cursor
								.getColumnIndex(DBHelper.COLUMN_COLORS_ID))));
						return true;

						 case 6:
						 TextView tv3 = (TextView) view;
						tv3.setText("#" +
						 String.format("%02X",cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Red)))+
					 String.format("%02X",
					 cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Green)))
					 + String.format("%02X",
					 cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COLORS_Blue))));
						return true;
					
					}

					// return false;
					return false;
				}
			});

			
			lvColors.setAdapter(adapter);
		} else {
			// siccome il movetofirst presuppone ci sia almeno un elemento...
			lvColors.setAdapter(null);
		}
		// c.close();
		databaseHelper.close();
		
		
	}
	

}
