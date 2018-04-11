package com.example.moksleivis.knygalaboras.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.moksleivis.knygalaboras.R;

/**
 * Created by arvyd on 3/6/2018.
 */
public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    public MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.furelise);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(player.isPlaying()){
           }else{
            player.start();
        }
        return START_NOT_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        player.start();
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }
    public void onPause() {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.stop();
            player.release();
        }else {
        }
    }

    @Override
    public void onLowMemory() {

    }


}
