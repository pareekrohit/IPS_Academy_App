package com.ipsacademypune.user.ipsacademy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebSite_Fragment extends Fragment {
    //Toolbar toolbar;
    WebView webView;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_site, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        webView = view.findViewById(R.id.webView);
        progressBar = view.findViewById(R.id.progressBar);

//        String res = getActivity().getIntent().getStringExtra("response");

        //Use Bundle in fragment instead of putExtra
        Bundle b = getArguments();
        String res  = b.getString("response");
        System.out.println("response////"+res);

        //webview..............................................................................
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setWebViewClient(new MyBrowser());
        webView.setWebChromeClient(new WebChromeClient());


        switch (res) {
            case "facebook":
                webView.loadUrl("https://www.facebook.com/ipsacademypune/?ref=br_rs");
                break;

            case "linkedin":
                webView.loadUrl("https://www.linkedin.com/company/ips-academy-pune/");
                break;

            case "website":
                webView.loadUrl("http://www.ipsacademypune.com/");
                break;
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            progressBar.setVisibility(View.VISIBLE);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Toast.makeText(getActivity(), "Server Error-" + description, Toast.LENGTH_SHORT).show();
            System.out.println("server Error-" + description);
        }
    }

    //on back press of the toolbar.....................................................
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        getActivity().finish();
                    }
                    return true;
            }
        }
        return true;
        //super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        final Fragment fragment =(FragmentManager)getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
//
//        if (fragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
//            super.onBackPressed();
//        }
//    }

}
