package com.hughsapps.extremeweather;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


public class MainActivity extends ActionBarActivity {
	
	private LocationManager locationManager;
	private String provider;
	private List<Address> add;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Define the criteria how to select the location provider -> use default
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		provider = locationManager.getBestProvider(criteria,true);
		Location location = locationManager.getLastKnownLocation(provider);
		Geocoder address = new Geocoder(this);
		Log.e("Location", "Coordinates:" + location.getLatitude() + " "+ location.getLongitude());
		if(Geocoder.isPresent()) {	
			try {
				add = address.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			} catch (IOException e) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(this);
				dialog.setMessage("No address found");
				dialog.setPositiveButton("IDK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						
					}
				});
				dialog.show();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			Log.e("Location", "NO backend service");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
 
	public void implicitViewURL1(View v) {
		String webpage = "http://m.weather.com/weather/today/" + add.get(0).getPostalCode();
		
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(webpage));
		startActivity(intent);
		
		
	}
	public void implicitViewURL2(View v) {
		String webpage = "http://m.accuweather.com/en/us/columbus-oh/43215/weather-forecast/350128";
		
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(webpage));
		startActivity(intent);
		
		
	}
	public void implicitViewURL3(View v) {
		
		String webpage = "http://m.weatherbug.com/OH/Columbus-weather/local-weather/current-conditions/KOSU";
		
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(webpage));
		startActivity(intent);	
		
		
	}
    
}


