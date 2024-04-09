package com.example.cardviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.ui.AppBarConfiguration;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private WebView webview;
    CardView cardView;

    TextView textView;
    TextView greetings;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView5);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);

        greetings = (TextView)findViewById(R.id.txtGreetings);

        Calendar time = Calendar.getInstance();

        int jam = time.get(Calendar.HOUR_OF_DAY);

        if(jam >= 0 && jam < 4){
            greetings.setText("Good Evening");
        }

        else if(jam >= 4 && jam < 12){
            greetings.setText("Good Morning");
        }

        else if(jam >= 12 && jam < 19){
            greetings.setText("Good Afternoon");
        }

        else if(jam >= 19 && jam < 24){
            greetings.setText("Good Evening");
        }

        else{
            greetings.setText("Good Day");
        }

        WebView weatherView = findViewById(R.id.weatherCard);
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

    }

    public void loadSymptoms(View v){

        setContentView(R.layout.fragment_symptoms);

    }

    public void loadJournaling(View v){

        setContentView(R.layout.fragment_journaling);

    }

    public void loadDailyReminders(View v){

        setContentView(R.layout.fragment_reminders);

    }

    public void loadMedTrack(View v){

        setContentView(R.layout.fragment_medtrack);

    }

    public void loadMSFAQ(View v){

        setContentView(R.layout.fragment_faqs);

        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://mscanada.ca/common-questions");

    }

    @Override
    public void onBackPressed(){
        if(webview.canGoBack()){
            webview.goBack();
        }
        else {
            super.onBackPressed();
        }
    }



    public void loadMain(View v){

        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView5);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);

        greetings = (TextView)findViewById(R.id.txtGreetings);

        Calendar time = Calendar.getInstance();

        int jam = time.get(Calendar.HOUR_OF_DAY);

        if(jam >= 0 && jam < 4){
            greetings.setText("Good Evening");
        }

        else if(jam >= 4 && jam < 12){
            greetings.setText("Good Morning");
        }

        else if(jam >= 12 && jam < 19){
            greetings.setText("Good Afternoon");
        }

        else if(jam >= 19 && jam < 24){
            greetings.setText("Good Evening");
        }

        else{
            greetings.setText("Good Day");
        }

        WebView weatherView = findViewById(R.id.weatherCard);
        String weather = "<iframe title=\"Environment Canada Weather\" width=\"287px\" height=\"191px\" " +
                "src=\"https://weather.gc.ca/wxlink/wxlink.html?cityCode=sk-24&amp;lang=e\" allowtransparency=\"true\" frameborder=\"0\"></iframe>";
        weatherView.loadData(weather, "text/html", "utf-8");
        weatherView.getSettings().setJavaScriptEnabled(true);
        weatherView.setWebViewClient(new WebViewClient());
        weatherView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

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

    }

//    public void loadWeather(View v){
//
//        String url = "https://weather.gc.ca/city/pages/sk-24_metric_e.html";
//
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
//
//    }



}