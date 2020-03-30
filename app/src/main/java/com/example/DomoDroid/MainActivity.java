package com.example.DomoDroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
	public static int ActionId_VR, ActionId_light;
	private RequestQueue volley_queue;

	public void displayMsg(String str) {
		Toast.makeText(this, "Bouton cliqué : " + str, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Laurent BOURDEL - DomoDroid");

		volley_queue = Volley.newRequestQueue(this.getApplicationContext());

		addListenerOnSpinnerVR();
		addListenerOnSpinnerLight();
	}

	public void addListenerOnSpinnerVR() {
		Spinner spinner_vr = (Spinner) findViewById(R.id.VR_spinner1);

		spinner_vr.setOnItemSelectedListener(new SpinnerListenerVR(this));
		spinner_vr.setSelection(2); // Select 3rd item
	}

	public void addListenerOnSpinnerLight() {
		Spinner spinner_light = (Spinner) findViewById(R.id.spinner_Light);

		spinner_light.setOnItemSelectedListener(new SpinnerListenerLight(this));
		spinner_light.setSelection(2); // Select 3rd item
	}

	public void send_http_socket(String url) {
		if (url.length() != 0) {
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

	public void ListenerButtonClicked(View view) {
		int Index_Spinner_VR = 0xFFFF;
		int Index_Spinner_Light = 0xFFFF;

		if (view.getId() == R.id.buttonUP) {
			// buttonUP action
			Index_Spinner_VR = ActionId_VR * 2;
			displayMsg("UP" + ActionId_VR);
		} else if (view.getId() == R.id.buttonDOWN) {
			//buttonDOWN action
			Index_Spinner_VR = ActionId_VR * 2 + 1;
			displayMsg("DOWN" + ActionId_VR);
		} else if (view.getId() == R.id.button_ON) {
			//buttonDOWN action
			Index_Spinner_Light = ActionId_light * 2 + 1;
			displayMsg("ON" + ActionId_light);
		} else if (view.getId() == R.id.button_OFF) {
			//buttonDOWN action
			Index_Spinner_Light = ActionId_light * 2 + 1;
			displayMsg("OFF" + ActionId_light);
		}

		Resources res = this.getResources();
		if (Index_Spinner_VR != 0xFFFF) {
			String[] http_address = res.getStringArray(R.array.http_get_VR);

			String url = res.getString(R.string.server_address) + res.getStringArray(R.array.http_get_VR)[Index_Spinner_VR];

			send_http_socket(url);
		}

		if (Index_Spinner_Light != 0xFFFF) {
			String[] http_address = res.getStringArray(R.array.http_get_Light);

			// TO DO String url = res.getString(R.string.server_address) + res.getStringArray(R.array.http_get_VR)[Index_Spinner_Light];

			// TO DO send_http_socket(url);
		}
	}

	String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
}