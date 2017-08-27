package co.siempo.phone.inbox;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import co.siempo.phone.R;
import minium.co.core.ui.CoreActivity;

import static android.view.View.GONE;

@EActivity(R.layout.activity_google_inbox)
public class GoogleInboxActivity extends CoreActivity {

    String HOME_PAGE = "https://mail.google.com/mail";

    @ViewById
    WebView webView;

    @ViewById
    ProgressBar pBar;

    @SuppressLint("SetJavaScriptEnabled")
    @AfterViews
    void afterViews() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pBar.setVisibility(GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(HOME_PAGE);
    }
}
