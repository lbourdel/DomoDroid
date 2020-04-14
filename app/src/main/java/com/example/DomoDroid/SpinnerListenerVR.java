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
public class SpinnerListenerVR extends Activity implements OnItemSelectedListener {
	public Activity activity;

	// Constructor
	public SpinnerListenerVR(Activity _activity){
		this.activity = _activity;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		// An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		Toast.makeText(parent.getContext(),
				"SelectedVR : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();

		TextView helloTextView = (TextView)  this.activity.findViewById(R.id.Log_lbr);
		helloTextView.setText(parent.getItemAtPosition(pos).toString());

		MainActivity.ActionId_VR = parent.getSelectedItemPosition();
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
		Toast.makeText(parent.getContext(),
				"onNothingSelectedVR : " + "LBR",
				Toast.LENGTH_SHORT).show();
	}
}
