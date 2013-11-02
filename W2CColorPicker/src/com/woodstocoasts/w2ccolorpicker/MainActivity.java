package com.woodstocoasts.w2ccolorpicker;

import java.util.ArrayList;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



/**
 * @author G
 *
 */
public class MainActivity extends Activity {

	SeekBar sbRed;
	SeekBar sbGreen;
	SeekBar sbBlue;

	private int r = 0;
	private int g = 0;
	private int b = 0;

	private int lockRGB = 0;

	private boolean bSeekBarSliding = false;

	TextView tvColorSample;
	TextView tvHEX;

	EditText etRedValue;
	EditText etGreenValue;
	EditText etBlueValue;

	CheckBox cbLockRGB;

	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		SharedPreferences prefs = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
		
	    
		tvColorSample = (TextView) findViewById(R.id.textViewColorSample);
		tvHEX = (TextView) findViewById(R.id.textViewRGBHValues);

		etRedValue = (EditText) findViewById(R.id.editTextRValue);
		etGreenValue = (EditText) findViewById(R.id.editTextGValue);
		etBlueValue = (EditText) findViewById(R.id.editTextBValue);

		cbLockRGB = (CheckBox) findViewById(R.id.checkBoxLockRGB);




		sbRed = (SeekBar) findViewById(R.id.seekBarRed);
		sbGreen = (SeekBar) findViewById(R.id.seekBarGreen);
		sbBlue = (SeekBar) findViewById(R.id.seekBarBlue);

//		etRedValue.setText(prefs.getInt("SavedR", 0)) ;
//		etGreenValue.setText(prefs.getInt("SavedG", 0)) ;
//		etBlueValue.setText(prefs.getInt("SavedB", 0)) ;
//		
		updateColor();

		final ArrayList<String> arrlist = new ArrayList<String>();

		arrlist.add("Nessun colore selezionato");


		final ListView lv = (ListView) findViewById(R.id.listViewColors);
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrlist));

		

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//arg1.setBackgroundColor(Color.rgb(r, g, b));
				Toast.makeText(getApplicationContext(), arrlist.get(arg2), Toast.LENGTH_SHORT).show();
			}
		});
		
		
		tvColorSample.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String myElem =	"#"+ String.format("%02X", r)+ String.format("%02X", g)+ String.format("%02X", b);
				
				if (arrlist.contains("Nessun colore selezionato")) {
					arrlist.remove(0);
					arrlist.add(myElem);
				}
				else
				{
					arrlist.add(myElem);
				}



				ListAdapter adapter = lv.getAdapter();
				lv.setAdapter(null);
				lv.setAdapter(adapter);
				/*
				for (int i = 0; i < lv.getCount(); i++){
					Log.e("GPA", lv.getItemAtPosition(i).toString());
					Log.e("GPA:R", lv.getItemAtPosition(i).toString().substring(1, 3)) ;
					Log.e("GPA:G", lv.getItemAtPosition(i).toString().substring(3, 5)) ;
					Log.e("GPA:B", lv.getItemAtPosition(i).toString().substring(5, 7)) ;


					lv.setBackgroundColor(Color.rgb(Integer.parseInt(lv.getItemAtPosition(i).toString().substring(1, 3), 16), Integer.parseInt(lv.getItemAtPosition(i).toString().substring(3, 5), 16), Integer.parseInt(lv.getItemAtPosition(i).toString().substring(5, 7), 16)));
					//	lv.setBackgroundColor(Color.rgb(122, 10, 255));
				}
				*/
					
					
			}	 
		});


		cbLockRGB.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Drawable d = getResources().getDrawable(android.R.drawable.ic_secure);
				// TODO Auto-generated method stub
				if (cbLockRGB.isChecked()){
					etRedValue.setCompoundDrawablesWithIntrinsicBounds(null, null, d, null);
					etGreenValue.setCompoundDrawablesWithIntrinsicBounds(null, null, d, null);
					etBlueValue.setCompoundDrawablesWithIntrinsicBounds(null, null, d, null);

					lockRGB = (sbRed.getProgress() + sbGreen.getProgress() + sbBlue.getProgress()) / 3;

					sbRed.setProgress(lockRGB);
					sbGreen.setProgress(lockRGB);
					sbBlue.setProgress(lockRGB);

				}
				else
				{
					etRedValue.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
					etGreenValue.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
					etBlueValue.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
				}
				updateColor();
			}
		});

		sbRed.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (cbLockRGB.isChecked()){
					sbGreen.setProgress(progress);
					sbBlue.setProgress(progress);
					lockRGB = progress;
				}

				updateColor();
			}
		});




		sbBlue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub


			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (cbLockRGB.isChecked()){
					sbRed.setProgress(progress);
					sbBlue.setProgress(progress);
					lockRGB = progress;
				}

				updateColor();
			}
		});


		sbGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (cbLockRGB.isChecked()){
					sbRed.setProgress(progress);
					sbGreen.setProgress(progress);
					lockRGB = progress;
				}

				updateColor();
			}
		});

		/**
		 * EditText RED addTextChangedListener
		 */
		etRedValue.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub


				updateProgressBarFromValues(etRedValue, sbRed);
			}
		});
		/**
		 * EditText GREEN addTextChangedListener
		 */
		etGreenValue.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				updateProgressBarFromValues(etGreenValue, sbGreen);
			}
		});


		etBlueValue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//etBlueValue.setFocusable(true);
				//				etBlueValue.setFocusableInTouchMode(true);
				//				etBlueValue.selectAll();
				//				etBlueValue.setSelection(etBlueValue.getText().length());


			}
		});

		/**
		 * EditText BLUE addTextChangedListener
		 */
		etBlueValue.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				updateProgressBarFromValues(etBlueValue, sbBlue);

			}
		});

		tvHEX.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Gets a handle to the clipboard service.
				//			ClipboardManager clipboard = (ClipboardManager)
				//			        getSystemService(Context.CLIPBOARD_SERVICE);

				String clipbresult = "RGB("+ r +", " + g + ", " + b +")";
				clipbresult += "; RGB Hex: (" + tvHEX.getText().toString() + ")";
				//			// Creates a new text clip to put on the clipboard
				//			ClipData clip = ClipData.newPlainText("plain text", clipbresult);
				//			// Set the clipboard's primary clip.
				//			clipboard.setPrimaryClip(clip);

				int sdk = android.os.Build.VERSION.SDK_INT;
				if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					clipboard.setText(clipbresult);
				} else {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
					android.content.ClipData clip = android.content.ClipData.newPlainText("text label",clipbresult);
					clipboard.setPrimaryClip(clip);

				}
				Toast.makeText(getApplicationContext(), "Copiato nella clipboard:\n" + clipbresult, Toast.LENGTH_LONG).show();

			}
		})	;

	}
	/**
	 * MENU dell'Activity
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.action_settings:
			Intent i = new Intent(this, PrefsActivity.class);
			startActivityForResult(i, 0);
			return true;
		}
		return false;
		
		
	}




	/**
	 * Aggiorno il valore degli EditText dei valori RGB
	 * quando muovo le SeekBar
	 */
	public void updateColor(){
		bSeekBarSliding = true;


		if (cbLockRGB.isChecked())
		{
			r = lockRGB;
			g = lockRGB;
			b = lockRGB;
		}
		else
		{
			r = sbRed.getProgress();
			g = sbGreen.getProgress();
			b = sbBlue.getProgress();
		}
		tvColorSample.setBackgroundColor(Color.rgb(r, g, b));

		//etRedValue.setFocusable(false);
		etRedValue.setText(String.valueOf(r));
		
		//etRedValue.setFocusableInTouchMode(true);
		//etRedValue.clearFocus();

		//etGreenValue.setFocusable(false);
		etGreenValue.setText(String.valueOf(g));

		//etGreenValue.clearFocus();
		//etGreenValue.setFocusableInTouchMode(true);

		//etBlueValue.setFocusable(false);
		etBlueValue.setText(String.valueOf(b));
		//etBlueValue.setFocusableInTouchMode(true);
		//etBlueValue.clearFocus();

		
		int [] cmyk = rgbToCmyk(r, g, b);
		String cmykS;
		
		cmykS = "C: " + Integer.toString(cmyk[0]) + " M: " + Integer.toString(cmyk[1])+ " Y: " +Integer.toString(cmyk[2])+" K: " +Integer.toString(cmyk[3]);  
		
		
		tvHEX.setText("#"+ String.format("%02X", r)+ String.format("%02X", g)+ String.format("%02X", b) + "-" + cmykS);
		
		bSeekBarSliding = false;


	}

	/**
	 * updateProgressBarFromValues
	 * @param e EditText
	 * @param s SeekBar
	 */
	public void updateProgressBarFromValues(EditText e, SeekBar s){
		// \\d{1,3}
		// /(^[0-1]?[0-9]?[0-9]$)|(^[2][0-4][0-9]$)|(^25[0-5]$)/
		// /^([01]?\d{1,2}|2([0-4]\d|5[0-5]))$/
		// [0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]

		// Posiziono il cursore alla fine della stringa di testo
		e.setSelection(e.getText().length());

		// Controllo il mascheramento degli eventi per velocizzare lo slide delle SeekBar
		// in modo da non eseguire lo scatenarsi degli eventi come se fosse digitato a mano
		if (bSeekBarSliding == false){
			switch (e.getText().length()){
			case 1:
				if (!(e.getText().toString().matches("[0-9]"))){
					e.setText("0");
				}
				s.setProgress(Integer.parseInt(e.getText().toString()));
				break;
			case 2:
				if (!(e.getText().toString().matches("[1-9][0-9]|1[0-9][0-9]"))){
					e.setText( e.getText().toString().substring(0, e.getText().length()-1));
				}
				s.setProgress(Integer.parseInt(e.getText().toString()));
				break;

			case 3:
				if (!(e.getText().toString().matches("1[0-9][0-9]|2[0-4][0-9]|25[0-5]"))){
					e.setText( e.getText().toString().substring(0, e.getText().length()-1));
				}
				s.setProgress(Integer.parseInt(e.getText().toString()));
				break;	
			default:
				if (!e.getText().toString().matches("[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]")
						&& e.getText().toString().length()>3){
					e.setText( e.getText().toString().substring(0, 3));
				}
			}
		}

	}

	public static int[] rgbToCmyk(int red, int green, int blue)
    {
        int black = Math.min(Math.min(255 - red, 255 - green), 255 - blue);

        if (black!=255) {
            int cyan    = (255-red-black)/(255-black);
            int magenta = (255-green-black)/(255-black);
            int yellow  = (255-blue-black)/(255-black);
            return new int[] {cyan,magenta,yellow,black};
        } else {
            int cyan = 255 - red;
            int magenta = 255 - green;
            int yellow = 255 - blue;
            return new int[] {cyan,magenta,yellow,black};
        }
    }

    public static int[] cmykToRgb(int cyan, int magenta, int yellow, int black)
    {
        if (black!=255) {
            int R = ((255-cyan) * (255-black)) / 255; 
            int G = ((255-magenta) * (255-black)) / 255; 
            int B = ((255-yellow) * (255-black)) / 255;
            return new int[] {R,G,B};
        } else {
            int R = 255 - cyan;
            int G = 255 - magenta;
            int B = 255 - yellow;
            return new int[] {R,G,B};
        }
    }
}
