package com.example.DomoDroid;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// Spinner Listener : heritage from OnItemSelectedListener
public class SpinnerOnItemSelectedListener extends Activity implements OnItemSelectedListener {
	public Activity activity;

	// Constructor
	public SpinnerOnItemSelectedListener(Activity _activity){
		this.activity = _activity;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		// An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		Toast.makeText(parent.getContext(),
				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();

		TextView helloTextView = (TextView)  this.activity.findViewById(R.id.Log_lbr);
		helloTextView.setText(parent.getItemAtPosition(pos).toString());

		MainActivity.ActionId = parent.getSelectedItemPosition();
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
		Toast.makeText(parent.getContext(),
				"OnItemSelectedListener : " + "LBR",
				Toast.LENGTH_SHORT).show();
	}
}
