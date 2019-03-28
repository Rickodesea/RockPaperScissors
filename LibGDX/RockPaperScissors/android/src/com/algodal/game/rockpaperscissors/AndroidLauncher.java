package com.algodal.game.rockpaperscissors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {

    protected Android android;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private boolean interLoad;

    @Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        android = new Android(this);
        View gameView = initializeForView(new Game(android), config);

        //Ads Content
        mAdView = new AdView(this);
        mInterstitialAd = new InterstitialAd(this);

        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        // Main Layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Set up Views
        setupAds(mAdView, layout);
        setupView(gameView, layout);

        //Set Main Layout
        setContentView(layout);

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        // Load Ads
        MobileAds.initialize(this, "@string/app_id");
        setAdViewListener(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);


        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
        interLoad = mInterstitialAd.isLoaded();
    }

    private void setupAds(AdView bannerAd, ViewGroup layout) {
        bannerAd.setAdSize(AdSize.FULL_BANNER);
        bannerAd.setBackgroundColor(0xff00000);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 0.20f;
        params.setMargins(0,0,0,0);
        layout.addView(bannerAd, params);
    }

    private void setupView(View view, ViewGroup layout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 0.80f;
        params.setMargins(0,0,0,0);
        layout.addView(view, params);
    }

    public void showInterstitialAd(){
        if (interLoad) {
            mInterstitialAd.show();
        } else {
            Log.d("Interstitial Ad", "Ad failed to load");
        }
    }

    private void setAdViewListener(AdView mAdView){
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("Banner Ad", "Ad Loaded Successfully");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("Banner Ad", "Ad Failed to Load");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void setIntertitialAdListener(InterstitialAd mInterstitialAd){
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("Interstitial Ad", "Ad Loaded Successfully");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("Interstitial Ad", "Ad Failed to Load");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }
}
