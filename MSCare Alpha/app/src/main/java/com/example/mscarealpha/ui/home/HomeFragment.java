package com.example.mscarealpha.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mscarealpha.R;
import com.example.mscarealpha.databinding.FragmentHomeBinding;
import com.example.mscarealpha.ui.symptoms.Symptom;
import com.example.mscarealpha.ui.symptoms.SymptomDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView greetings;

    TextView date;
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

        SymptomDbHelper dm = new SymptomDbHelper(getActivity());

        TextView textResults = view.findViewById(R.id.results_home_textview); // Get the TextView

        List<Symptom> symptomList = dm.selectAll(); // Fetch the symptom data
        String list = ""; // Prepare a string to hold the formatted results

        // Loop through the symptom list (not the Cursor)
        for (Symptom symptom : symptomList) {
            list += (symptom.getBodyPart() + " - " + symptom.getSymptomName() + " (Pain: " + symptom.getPainLevel() + ")\n"); // Format the symptom data
        }

        textResults.setText(list); // Display the formatted data in the TextView

        return view;


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}