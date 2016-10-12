package com.leedabin.android.layoutwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.webView);
        //webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());


        WebSettings webSettings = webView.getSettings();

        //줌사용
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //javascript
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://www.google.com");
    }
}
