package com.example.podsstore.productdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.podsstore.R;


import java.io.File;
import java.io.IOException;

public class ShowPDFActivity extends AppCompatActivity {
    VideoView videoView;
WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_show_p_d_f);
  getSupportActionBar().hide();
        webview = findViewById(R.id.webview);
         videoView = findViewById(R.id.videoView);
        webview.loadUrl(getIntent().getStringExtra("video"));
        Uri video = Uri.parse(getIntent().getStringExtra("video"));
        Log.d( "onCreate: uri",String.valueOf(video));
        Log.e( "on",getIntent().getStringExtra("video"));
       // videoView.setVideoURI(video);
        videoView.setMediaController(new MediaController(this));
       // videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("video")));

        try {
            videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("video")));
        }catch (Exception e){
            e.printStackTrace();
        }
        videoView.requestFocus();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                videoView.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        MediaController mediaController = new MediaController(ShowPDFActivity.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
                videoView.stopPlayback();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.
        videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("video")));
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // In Android versions less than N (7.0, API 24), onPause() is the
        // end of the visual lifecycle of the app.  Pausing the video here
        // prevents the sound from continuing to play even after the app
        // disappears.
        //
        // This is not a problem for more recent versions of Android because
        // onStop() is now the end of the visual lifecycle, and that is where
        // most of the app teardown should take place.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
      videoView.stopPlayback();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//       // videoView.stopPlayback();
//    }
}