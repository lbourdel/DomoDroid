package com.example.DomoDroid;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
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
	public String url_to_request="";
	public String url_to_stop="";
	public boolean VR_Felix_InAction = false;
	public boolean VR_Sdb2_InAction = false;
	public String split_str[];
	public String splitafter;
	public void displayMsg(String str) {
		Toast.makeText(this,  str, Toast.LENGTH_SHORT).show();
	}
	public Handler handler_VR =  null;
	public Runnable myRunnable_VR_Felix = null;
	public Runnable myRunnable_VR_Sdb2 = null;

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
			split_str = url.split("[?]");
			splitafter = split_str[split_str.length - 1];
			displayMsg(splitafter);

			// Request a string response from the provided URL.
			StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							// Display the first 500 characters of the response string.
							String split_str[] = response.split("LBR");
							String splitafter = split_str[split_str.length-1];
							displayMsg("Response is: " + splitafter);
						}
					},

					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							displayMsg("That didn't work!");
						}
					}
			);
			url = "";
			// Add the request to the RequestQueue.
			volley_queue.add(stringRequest);
		}
	}

	public void ListenerButtonClicked(View view) {
		int Index_Spinner_VR = 0xFFFF;
		int Index_Spinner_Light = 0xFFFF;
		String url_to_request;


		if (view.getId() == R.id.buttonUP) {
			// buttonUP action
			Index_Spinner_VR = ActionId_VR * 2;
		} else if (view.getId() == R.id.buttonDOWN) {
			//buttonDOWN action
			Index_Spinner_VR = ActionId_VR * 2 + 1;
		} else if (view.getId() == R.id.button_ON) {
			//buttonUp action
			Index_Spinner_Light = ActionId_light * 2;
		} else if (view.getId() == R.id.button_OFF) {
			//buttonDOWN action
			Index_Spinner_Light = ActionId_light * 2 + 1;
		}

		Resources res = this.getResources();
		if (Index_Spinner_VR != 0xFFFF) {
			String[] http_address = res.getStringArray(R.array.http_get_VR);

			url_to_request = res.getString(R.string.server_address) + res.getStringArray(R.array.http_get_VR)[Index_Spinner_VR];
			split_str = url_to_request.split("consigne=");
			if(split_str.length==2) { // Is it a gradateur command
				url_to_stop = split_str[0] + "consigne=0";
				switch ( res.getStringArray(R.array.VR_array)[ActionId_VR] )
				{
					case "VR Ch Felix":
						if (VR_Felix_InAction==false ) {
							VR_Felix_InAction = true;
							handler_VR =  new Handler();
							myRunnable_VR_Felix = new Runnable(){
								@Override
								public void run() {
									displayMsg("We can stop all VRs now !!");
									VR_Felix_InAction = false;
									send_http_socket(url_to_stop);
								}
							};
							handler_VR.postDelayed( myRunnable_VR_Felix , 20000);
						}
						else
						{
							// Stop Shutter immediately
							displayMsg("We can stop all VRs immediately !!");
							url_to_request = "";
							myRunnable_VR_Felix.run();
							handler_VR.removeCallbacks(myRunnable_VR_Felix);
							/*VR_Felix_InAction = false;
							send_http_socket(url_to_stop);*/
						}
						;
					case "VR Sdb2":
						if (VR_Sdb2_InAction==false ) {
							VR_Sdb2_InAction = true;
							handler_VR =  new Handler();
							myRunnable_VR_Sdb2 = new Runnable(){
								@Override
								public void run() {
									displayMsg("We can stop all VRs now !!");
									VR_Sdb2_InAction = false;
									send_http_socket(url_to_stop);
								}
							};
							handler_VR.postDelayed( myRunnable_VR_Sdb2 , 10000);
						}
						else
						{
							// Stop Shutter immediately
							displayMsg("We can stop all VRs immediately !!");
							url_to_request = "";
							myRunnable_VR_Sdb2.run();
							handler_VR.removeCallbacks(myRunnable_VR_Sdb2);
							/*VR_Sdb2_InAction = false;
							send_http_socket(url_to_stop);*/
						}
						;
					default:
						;
				}

			}
			send_http_socket(url_to_request);

		}

		if (Index_Spinner_Light != 0xFFFF) {
			if (false) { // For TEST
				TypedArray ta = res.obtainTypedArray(R.array.test_laurent);
				int n = ta.length();
				String[][] array_lbr = new String[n][];
				for (int i = 0; i < n; ++i) {
					int id = ta.getResourceId(i, 0);
					if (id > 0) {
						array_lbr[i] = res.getStringArray(id);
					}
				}
				ta.recycle(); // Important!
			}


			String[] http_address = res.getStringArray(R.array.http_get_Light);

			url_to_request = res.getString(R.string.server_address) + res.getStringArray(R.array.http_get_Light)[Index_Spinner_Light];
			send_http_socket(url_to_request);
		}
	}

	String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
}