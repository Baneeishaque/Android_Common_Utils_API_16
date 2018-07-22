package ndk.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created on 18-07-2018 23:11 under DStock.
 */
public class Custom_WebViewClient extends WebViewClient {

    private Activity activity;
    private ProgressBar progressBar;
    private String ShowOrHideWebViewInitialUse = "show";
    private String application_url;
    private ImageView imageView_logo;

    public Custom_WebViewClient(Activity activity, ProgressBar progressBar, String application_url, ImageView imageView_logo) {
        this.activity = activity;
        this.progressBar = progressBar;
        this.application_url = application_url;
        this.imageView_logo = imageView_logo;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (url.contains(application_url)) return false;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }

    //TODO : Implement Prefetch of Pages

    // This allows for a splash screen
    // (and hide elements once the page loads)
    @Override
    public void onPageStarted(WebView webview, String url, Bitmap favicon) {

        // only make it invisible the FIRST time the app is run
        if (ShowOrHideWebViewInitialUse.equals("show")) {
            webview.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageFinished(WebView webView, String url) {

        ShowOrHideWebViewInitialUse = "hide";
        progressBar.setVisibility(View.GONE);

        //hide loading image
        imageView_logo.setVisibility(View.GONE);

        webView.setVisibility(View.VISIBLE);
        super.onPageFinished(webView, url);

    }
}
