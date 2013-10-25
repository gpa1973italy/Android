// http://developer.android.com/guide/topics/ui/dialogs.html

package com.woodstocoasts.w2cdialog;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;

//import org.json.JSONArray;
//import org.json.JSONObject;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showDialog(View v) {
		
		final String[] a = {"a", "b", "c"};
		
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage("Vuoi vedere che accade adesso?")
		       .setTitle("Attenzione!")
		       .setIcon(R.drawable.ic_launcher)
		      
		       .setPositiveButton("SI", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "CIAO" + which, Toast.LENGTH_LONG).show();
				}
			})
			.setNeutralButton("NO!", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "NO!\nNon funziona!!!" + which, Toast.LENGTH_LONG).show();
				}
			})
			
			; 

		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		
		dialog.show();	
	}
	
	
	
	public void showDialogList(View v) {
		final String[] a = {"a", "b", "c"};
		
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Scegli dalla lista")
		.setItems(a, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hai scelto: " + a[which], Toast.LENGTH_LONG).show();
			}
		});
		
		AlertDialog dialog = builder.create();
		
		dialog.show();
	}
	
	
	public void showCustomDialog(View v){
		AlertDialog.Builder builder_custom = new AlertDialog.Builder(this);
		
		// Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();
	
	    
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder_custom.setView(inflater.inflate(R.layout.dialog_custom, null));
		

	    
		builder_custom.setTitle("Custom Dialog")
		.setMessage("Compila il form seguente")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				Dialog f = (Dialog) dialog;
				EditText ed = (EditText) f.findViewById(R.id.editTextCognome);
				EditText ee = (EditText) f.findViewById(R.id.editTextNome);
				EditText ef = (EditText) f.findViewById(R.id.editTextDataNascita);

				String result = "I valori inseriti sono:";
				
				result += "\nCOGNOME:\t" + ed.getText().toString().toUpperCase();
				result += "\nNome:\t" + ee.getText().toString();
				result += "\nData Nascita:\t" + ef.getText().toString();
			
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
		})
		;
		
		AlertDialog dialog = builder_custom.create();
		dialog.show();
	}
//-----------------------------------------------------------------------------
	public void showDTPicker(View v)   {

		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog g = new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub

				Button b = (Button) findViewById(R.id.button4);
				b.setText(String.format("%04d", year) + "-"  + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth));
			}
		} , year, month, day);
		g.show();

	}

//-----------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------
		public void showTPicker(View v)   {

			final Calendar c = Calendar.getInstance();
			c.getTime();
			int hh = c.get(Calendar.HOUR);
			int mi = c.get(Calendar.MINUTE);
			
			TimePickerDialog g = new TimePickerDialog(this, new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					
					Button b = (Button) findViewById(R.id.button5);
					b.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
					
				}
			}, hh, mi, true);
			g.show();

		}

	//-----------------------------------------------------------------------------	
}


