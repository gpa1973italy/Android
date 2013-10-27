package com.woodstocoasts.w2ccolorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
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

	private boolean bSeekBarSliding = false;

	TextView tvColorSample;

	EditText etRedValue;
	EditText etGreenValue;
	EditText etBlueValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvColorSample = (TextView) findViewById(R.id.textView2);

		etRedValue = (EditText) findViewById(R.id.editTextRValue);
		etGreenValue = (EditText) findViewById(R.id.editTextGValue);
		etBlueValue = (EditText) findViewById(R.id.editTextBValue);


		sbRed = (SeekBar) findViewById(R.id.seekBarRed);
		sbGreen = (SeekBar) findViewById(R.id.seekBarGreen);
		sbBlue = (SeekBar) findViewById(R.id.seekBarBlue);

		updateColor();

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




	/**
	 * Aggiorno il valore degli EditText dei valori RGB
	 * quando muovo le SeekBar
	 */
	public void updateColor(){
		bSeekBarSliding = true;

		r = sbRed.getProgress();
		g = sbGreen.getProgress();
		b = sbBlue.getProgress();

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

}
