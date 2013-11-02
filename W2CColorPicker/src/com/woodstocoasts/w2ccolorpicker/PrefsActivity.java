package com.woodstocoasts.w2ccolorpicker;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity{
	SharedPreferences.Editor prefsEdit ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_prefs);
		 addPreferencesFromResource(R.xml.preferences);
		 
		 
	}

//	@Override
//-	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.prefs, menu);
//		return true;
//	}

	@Override
    protected void onDestroy() 
    {
		//prefsEdit = (Editor) getSharedPreferences("MY_SHARED_PREFS", Context.MODE_PRIVATE);
		//prefsEdit.commit();	
     super.onDestroy();
     Toast.makeText(this, "Saving preferences", Toast.LENGTH_LONG).show();
     
//     EditTextPreference etpR = (EditTextPreference) getPreferenceScreen().findPreference("SavedR");
//     EditTextPreference etpG = (EditTextPreference) getPreferenceScreen().findPreference("SavedG");
//     EditTextPreference etpB = (EditTextPreference) getPreferenceScreen().findPreference("SavedB");
     

     
     //SharedPreferences.Editor prefsEdit = (Editor) getSharedPreferences("MY_SHARED_PREFS", Context.MODE_PRIVATE);

//     SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE); 
//     SharedPreferences.Editor editor = sharedPref.edit();
//     editor.putInt("SavedR", Integer.parseInt(etpR.toString()));
//     editor.commit();

//     prefsEdit.putInt("SavedR", Integer.parseInt(etpR.toString()));
//     prefsEdit.putInt("SavedG", Integer.parseInt(etpG.toString()));
//     prefsEdit.putInt("SavedB", Integer.parseInt(etpB.toString()));
//     
//     prefsEdit.commit();
     
    }

	
}
