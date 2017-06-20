package com.leisurekr.leisuresportskorea;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import static com.leisurekr.leisuresportskorea.common.NetworkDefineConstant.SERVER_URL_PAYPAL_CHECKOUT_INFO;

/**
 * Created by mobile on 2017. 6. 19..
 */

public class PaypalWebViewActivity extends Activity {
    WebView paypalWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_paypal);

        paypalWebView = (WebView) findViewById(R.id.paypal_webview);

        WebSettings webSettings = paypalWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        paypalWebView.loadUrl(SERVER_URL_PAYPAL_CHECKOUT_INFO);
    }
}
