package com.example.mscarealpha.ui.home;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mscarealpha.R;
import com.example.mscarealpha.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    TextView greetings;

    TextView date;

    final String APP_ID = "dab3af44de7d24ae7ff86549334e45bd";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String Location_Provider = LocationManager.GPS_PROVIDER;

    TextView NameofCity, weatherState, Temperature;
    ImageView mweatherIcon;

    ConstraintLayout mCityFinder;


    LocationManager mLocationManager;
    LocationListener mLocationListner;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        date = view.findViewById(R.id.textView5);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        date.setText(dateTime);

        greetings = view.findViewById(R.id.txtGreetings);

        Calendar time = Calendar.getInstance();

        int jam = time.get(Calendar.HOUR_OF_DAY);

        if (jam >= 0 && jam < 4) {
            greetings.setText("Good Evening");
        } else if (jam >= 4 && jam < 12) {
            greetings.setText("Good Morning");
        } else if (jam >= 12 && jam < 19) {
            greetings.setText("Good Afternoon");
        } else if (jam >= 19 && jam < 24) {
            greetings.setText("Good Evening");
        } else {
            greetings.setText("Good Day");
        }


        WebView weatherView = view.findViewById(R.id.weatherCard);
        String weather = "<iframe title=\"Environment Canada Weather\" width=\"287px\" height=\"191px\" " +
                "src=\"https://weather.gc.ca/wxlink/wxlink.html?cityCode=sk-24&amp;lang=e\" allowtransparency=\"true\" frameborder=\"0\"></iframe>";
        weatherView.loadData(weather, "text/html", "utf-8");
        weatherView.getSettings().setJavaScriptEnabled(true);
        weatherView.setWebViewClient(new WebViewClient());

        weatherView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String url = "https://weather.gc.ca/city/pages/sk-24_metric_e.html";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                return true;
            }
        });


        weatherState = view.findViewById(R.id.weatherCondition);
        Temperature = view.findViewById(R.id.temperature);
        mweatherIcon = view.findViewById(R.id.weatherIcon);
        NameofCity = view.findViewById(R.id.cityName);


        TextView da = view.findViewById(R.id.textView7);
        InputStream is = getResources().openRawResource(R.raw.affirmations);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        List<String[]> affirmationsList = new ArrayList<>(); // Store all affirmations
        try {
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                affirmationsList.add(tokens);
            }
            // Generate a random index
            Random random = new Random();
            int randomIndex = random.nextInt(affirmationsList.size());

            // Get the affirmation at the random index
            String[] randomAffirmation = affirmationsList.get(randomIndex);
            String randomAffirmationText = randomAffirmation[1]; // Assuming affirmation text is at index 1

            // Set the random affirmation text to the TextView
            da.setText(randomAffirmationText);
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }


        return view;


    }

    private List<AffirmationsAdapter> affirmationsAdapter = new ArrayList<>();


    @Override
    public void onResume() {
        super.onResume();
        getWeatherForCurrentLocation();
        getLastKnownLocation();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Location get Successfully", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
                getLastKnownLocation();
            } else {
                //user denied the permission
            }
        }


    }

    private void getWeatherForCurrentLocation() {

        mLocationManager = (LocationManager) requireActivity().getSystemService(LOCATION_SERVICE);
        mLocationListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());

                RequestParams params = new RequestParams();
                params.put("lat", Latitude);
                params.put("lon", Longitude);
                params.put("appid", APP_ID);
                letsdoSomeNetworking(params);
                getLastKnownLocation();


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                //not able to get location
            }
        };


        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListner);

    }

    private Location getLastKnownLocation() {
        // Use requireContext() to get the Context of the fragment
        mLocationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;

        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Handle permission request or return null/error condition as needed
                return null; // Or handle the permission request scenario appropriately
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }

        return bestLocation;
    }


    private void letsdoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Toast.makeText(requireContext(), "Data Get Success", Toast.LENGTH_SHORT).show();

                weatherData weatherD=weatherData.fromJson(response);
                updateUI(weatherD);


                // super.onSuccess(statusCode, headers, response);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });



    }

    private void updateUI(weatherData weather){


        Temperature.setText(weather.getmTemperature());
        NameofCity.setText(weather.getMcity());
        weatherState.setText(weather.getmWeatherType());
        int resourceID = getResources().getIdentifier(weather.getMicon(), "drawable", requireActivity().getPackageName());
        mweatherIcon.setImageResource(resourceID);


    }
    @Override
    public void onPause() {
        super.onPause();
        if(mLocationManager!=null)
        {
            mLocationManager.removeUpdates(mLocationListner);
        }
    }

}