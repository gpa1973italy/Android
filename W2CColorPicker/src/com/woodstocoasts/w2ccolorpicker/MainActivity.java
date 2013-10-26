package com.woodstocoasts.w2ccolorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	SeekBar sbRed;
	SeekBar sbGreen;
	SeekBar sbBlue;

	private int r = 0;
	private int g = 0;
	private int b = 0;



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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}





	public void updateColor(){
		r = sbRed.getProgress();
		g = sbGreen.getProgress();
		b = sbBlue.getProgress();

		tvColorSample.setBackgroundColor(Color.rgb(r, g, b));



		etRedValue.setText(String.valueOf(r));
		etGreenValue.setText(String.valueOf(g));
		etBlueValue.setText(String.valueOf(b));

	}
	
	/**
	 * updateProgressBarFromValues
	 * @param e EditText
	 * @param s SeekBar
	 */
	public void updateProgressBarFromValues(EditText e, SeekBar s){
		if (e.getText().toString().matches("\\d{1,3}")){
			if (Integer.parseInt(e.getText().toString())>= 0 && Integer.parseInt(e.getText().toString()) <= 255)
			{
				s.setProgress(Integer.parseInt(e.getText().toString()));
			}
		}
		else
		{
			e.setText("0");
		}	
	}

}
