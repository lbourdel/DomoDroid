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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CustomOnItemSelectedListener extends Activity implements OnItemSelectedListener {
	public Activity activity;
	public RequestQueue volley_queue;

	public CustomOnItemSelectedListener(Activity _activity, int _ActionId){

		this.activity = _activity;
		this.volley_queue = Volley.newRequestQueue( _activity.getApplicationContext() );

	}
	public TextView helloTextView;

	public void onItemSelected(AdapterView<?> parent, View view,
							   int pos, long id) {

		// An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		Toast.makeText(parent.getContext(),
				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();

		helloTextView = (TextView)  this.activity.findViewById(R.id.Log_lbr);
		helloTextView.setText(parent.getItemAtPosition(pos).toString());

		com.example.DomoDroid.MainActivity.ActionId = parent.getSelectedItemPosition();
/*
		Resources res = this.activity.getResources();
		String[] shoppingItems = res.getStringArray(R.array.http_get_VR);

		String url = shoppingItems[parent.getSelectedItemPosition()];

		if (url.length()!=0) {
			// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							// Display the first 500 characters of the response string.
							helloTextView.setText("Response is: " + response.substring(0, 10));
						}
					},

					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							helloTextView.setText("That didn't work!");
						}
					}
			);
// Add the request to the RequestQueue.
			volley_queue.add(stringRequest);
		}
 */
/*
		if (parent.getItemAtPosition(pos).toString().equals("VR Ch Jules")) {
// Instantiate the RequestQueue.

			String url_down ="http://192.168.1.14/domocan/www/php/CmdVR.php?carte=0x06&entree=0x08&data0=0x52";

// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.GET, url_down,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							// Display the first 500 characters of the response string.
							helloTextView.setText("Response is: "+ + response.substring(0,10));
						}
					},

					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							helloTextView.setText("That didn't work!");
						}
					}
			);

// Add the request to the RequestQueue.
			this.volley_queue.add(stringRequest);

		}
		if (parent.getItemAtPosition(pos).toString().equals("VR Ch Victor")) {
// Instantiate the RequestQueue.

			String url_up ="http://192.168.1.14/domocan/www/php/CmdVR.php?carte=0x06&entree=0x09&data0=0x52";

			//		showMessage("Volet BAS", Color.BLUE, true);

// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.GET, url_up,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							// Display the first 500 characters of the response string.
							helloTextView.setText("Response is: "+ response.substring(0,10));
						}
					},

					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							helloTextView.setText("That didn't work!");
						}
					}
			);

// Add the request to the RequestQueue.
			this.volley_queue.add(stringRequest);

		}

 */
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
		Toast.makeText(parent.getContext(),
				"OnItemSelectedListener : " + "LBR",
				Toast.LENGTH_SHORT).show();	}
}

/*
public class CustomOnItemSelectedListener implements OnItemSelectedListener {

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
							   long id) {
		Toast.makeText(parent.getContext(),
				"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
*/