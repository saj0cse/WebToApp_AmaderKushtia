package com.saj.amaderkushtia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    TextView textView;
    Button reloadBtn;


    private long presTime;
    private WebView homeWebView;
    RelativeLayout homeRelativeLayoutWebView, homeRelativeLayoutNoInternet;
    ProgressBar homeProgressBar;
    SwipeRefreshLayout homeSwipeRefreshLayout, homeSwipeRefreshId2;

    String USER_AGENT_ = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
        homeWebView = findViewById(R.id.homeWebViewId);
        homeRelativeLayoutWebView = findViewById(R.id.homeRelativeLayoutWebViewId);
        homeRelativeLayoutNoInternet = findViewById(R.id.homeRelativeLayoutNoInternetId);
        homeProgressBar = findViewById(R.id.homeProgressBarId);
        homeSwipeRefreshLayout = findViewById(R.id.homeSwipeRefreshId);
        homeSwipeRefreshId2 = findViewById(R.id.homeSwipeRefreshId2);
        textView = findViewById(R.id.textTitle);
        reloadBtn = findViewById(R.id.reloadBtnId);


        // action bar code
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // webView code
        homeWebView.getSettings().setUserAgentString(USER_AGENT_);
        homeWebView.getSettings().setLoadsImagesAutomatically(true);
        homeWebView.getSettings().setJavaScriptEnabled(true);
        homeWebView.getSettings().setAppCacheEnabled(true);
        homeWebView.getSettings().setAllowFileAccess(true);
        homeWebView.getSettings().setLoadWithOverviewMode(true);
        homeWebView.getSettings().setUseWideViewPort(true);
        homeWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        homeWebView.setWebViewClient(new HelloWebViewClient());
        homeWebView.getSettings().setDomStorageEnabled(true);
        homeWebView.loadUrl(getString(R.string.website_link));

        //--------------------------------------------->>
        //--------------------------------------------->>
        homeWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                // homeProgressBar.setVisibility(View.VISIBLE);
                homeProgressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    homeWebView.setVisibility(View.VISIBLE);
                    textView.setText("");
                    textView.setText(view.getTitle());

                } else {
                    textView.setText("Loading...");


                }


            }
        });


        //--------------------------------------------->>
        //--------------------------------------------->>
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noInternetConnectionCheck();
            }
        });


        //--------------------------------------------->>
        //--------------------------------------------->>
        homeSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noInternetConnectionCheck();
                homeWebView.reload();


            }
        });

        homeSwipeRefreshId2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noInternetConnectionCheck();
                homeWebView.reload();


            }
        });


        // navigation drawer code
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeId:
                        homeWebView.reload();
                      //  Intent intent = new Intent(MainActivity.this, MainActivity.class);
                      //  startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.aboutId:
                        Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.shareId:
                        Intent intent2 = new Intent(Intent.ACTION_SEND);
                        intent2.setType("text/plan");
                        String shareSub = "Amader Kushtia (আমাদের কুষ্টিয়া)";
                        String shareBody = "Amader Kushtia (আমাদের কুষ্টিয়া)\nDeveloped by: SAJ Developer\n\nDownload Apk\nhttps://drive.google.com/file/d/1xX6IPSoGtBNwk905798OE-4L-v-wV7Z-/view?usp=sharing";
                        intent2.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        intent2.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(intent2, "Share Using"));
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.fbIdM:
                        Intent intent3 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.facebook.com/saj0cse"));
                        startActivity(intent3);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.linkedinIdM:
                        Intent intent5 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.linkedin.com/in/saj0cse"));
                        startActivity(intent5);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.fbPageIdM:
                        Intent intent6 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.facebook.com/saj.developer"));
                        startActivity(intent6);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });


        //--------------------------------------------->>
        //--------------------------------------------->>
        imageMenu = findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    } // onCreate Method End


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            homeProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            homeProgressBar.setVisibility(View.GONE);
            homeSwipeRefreshLayout.setRefreshing(false);
            homeSwipeRefreshId2.setRefreshing(false);

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            homeRelativeLayoutWebView.setVisibility(View.GONE);
            homeRelativeLayoutNoInternet.setVisibility(View.VISIBLE);
            homeProgressBar.setVisibility(View.GONE);
            super.onReceivedError(view, request, error);
        }


    }


    // no internet method code
    public void noInternetConnectionCheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileData = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi != null && wifi.isConnected() || mobileData != null && mobileData.isConnected()) {
            homeRelativeLayoutWebView.setVisibility(View.VISIBLE);
            homeRelativeLayoutNoInternet.setVisibility(View.GONE);
            homeWebView.reload();

        } else {
            homeRelativeLayoutWebView.setVisibility(View.GONE);
            homeRelativeLayoutNoInternet.setVisibility(View.VISIBLE);
            homeProgressBar.setVisibility(View.GONE);


        }

    }


    // onBackPressed method code
    @Override
    public void onBackPressed() {
        // When landing in home screen
        if (!homeWebView.canGoBack()) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("WARNING")
                    .setMessage("Are you sure? You want to exit!")
                    .setIcon(R.drawable.alert_icon)
                    .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    }).show();

        } else {
            homeWebView.goBack();
        }

    }


} //finished