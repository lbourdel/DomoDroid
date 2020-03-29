package com.example.DomoDroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
	private Spinner spinner_vr;
	private 	int Index=0;
	public static int ActionId;
	public RequestQueue volley_queue;

	public void displayMsg(String str){
		Toast.makeText(this, "Bouton cliqué : " + str, Toast.LENGTH_SHORT).show();
	}

	public void SetActionId(int _ActionId){
		ActionId = _ActionId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.volley_queue = Volley.newRequestQueue( this.getApplicationContext() );


		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection(){
		spinner_vr = (Spinner) findViewById(R.id.VR_spinner1);

		spinner_vr.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, ActionId));
		spinner_vr.setSelection(2); // Select 3rd item
	}

	public void ListenerButtonClicked(View view) {
		if (view.getId() == R.id.buttonUP) {
			// buttonUP action
			Index = ActionId*2;
			displayMsg("UP" + ActionId);
		} else if (view.getId() == R.id.buttonDOWN) {
			//buttonDOWN action
			Index = ActionId*2+1;
			displayMsg("DOWN" + ActionId);
		}

		Resources res = this.getResources();
		String[] shoppingItems = res.getStringArray(R.array.http_get_VR);

		String url = shoppingItems[ Index ];

		if (url.length()!=0) {
			// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							// Display the first 500 characters of the response string.
							displayMsg("Response is: " + response.substring(0, 10));
						}
					},

					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							displayMsg("That didn't work!");
						}
					}
			);
// Add the request to the RequestQueue.
			volley_queue.add(stringRequest);
		}
	}

}