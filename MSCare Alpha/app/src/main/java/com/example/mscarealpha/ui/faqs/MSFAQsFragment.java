package com.example.mscarealpha.ui.faqs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mscarealpha.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MSFAQsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);


        WebView webview = view.findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://mscanada.ca/common-questions");

        FloatingActionButton fabAbout = view.findViewById(R.id.fabAbout);

        fabAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment dialog = new AboutFragment();
                dialog.show(getChildFragmentManager(), "");
            }
        });


        return view;


    }
}