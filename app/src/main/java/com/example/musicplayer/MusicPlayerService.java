package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MusicPlayerService extends Service {

    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        int musicId = extras.getInt("musicId");
        mPlayer = MediaPlayer.create(getApplicationContext(), musicId);
        return new MyServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayer.pause();
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public boolean play(){
        mPlayer.start();
        return mPlayer.isPlaying();
    }

    public boolean pause(){
        mPlayer.pause();
        return !mPlayer.isPlaying();
    }

    public class MyServiceBinder extends Binder {
        public MusicPlayerService getService(){
            return MusicPlayerService.this;
        };
    }
}
