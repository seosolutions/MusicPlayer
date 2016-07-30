package com.rudisdev.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.textView);
        //text.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
        text.animate().translationX(1000f).setDuration(10000);
        // colocar musica no onCreate para poder continuar de onde parou;

        mediaPlayer = MediaPlayer.create(this, R.raw.music);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

        });


        final SeekBar seekBarProgress = (SeekBar) findViewById(R.id.seekBarProgress);
        seekBarProgress.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                seekBarProgress.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 100);

        seekBarProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //  mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playAudio(View view) {


        mediaPlayer.start();
        ((Chronometer) findViewById(R.id.chronometer2)).start();


    }

    public void pauseAudio(View view) {

        mediaPlayer.pause();
        ((Chronometer) findViewById(R.id.chronometer2)).stop();


    }

    public void stopAudio(View view) {

        mediaPlayer.stop();
        ((Chronometer) findViewById(R.id.chronometer2)).setBase((SystemClock.elapsedRealtime()));
        ((Chronometer) findViewById(R.id.chronometer2)).stop();

    }
}
