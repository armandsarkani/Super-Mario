package com.example.supermario;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
// Commit on Saturday, June 8

public class MainActivity extends AppCompatActivity {
    FloatingActionButton restart;
    MediaPlayer mySong;
    FloatingActionButton sound;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    boolean soundOn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = findViewById(R.id.sound);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mySong = MediaPlayer.create(this, R.raw.song);
        mySong.start();
        mySong.setLooping(true);
        if (sharedPref.getInt("Sound", 1) == 1)
        {
            mySong.start();
            mySong.setLooping(true);
            mySong.setVolume(1, 1);
            sound.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.soundon));
        }
        else
        {
            mySong.start();
            mySong.setLooping(true);
            mySong.setVolume(0, 0);
            sound.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable. soundoff));
        }
        View decorView = getWindow().getDecorView();
        int uiOptions =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        sound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (sharedPref.getInt("Sound", 1) == 1) {
                    editor.putInt("Sound", 0);
                    editor.apply();
                    sound.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.soundoff));
                    mySong.setVolume(0, 0);
                } else {
                    editor.putInt("Sound", 1);
                    editor.apply();
                    sound.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.soundon));
                    mySong.setVolume(1, 1);
                }
            }
        });

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mySong.pause();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mySong.start();

    }

}
