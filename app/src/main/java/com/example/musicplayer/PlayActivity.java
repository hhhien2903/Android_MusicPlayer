package com.example.musicplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
    private ImageView imgPlayPhoto;
    private TextView txtPlayTitle;
    private TextView txtPlaySinger;
    private ImageView btnPlay;
    private ImageView btnStop;
    private ServiceConnection serviceConnection;
    private boolean isConnected;
    private MusicPlayerService musicPlayerService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitity_play);

        Bundle extras = getIntent().getExtras();
        this.imgPlayPhoto =(ImageView) findViewById(R.id.imgPlayPhoto);
        this.txtPlayTitle = (TextView) findViewById(R.id.txtPlayTitle);
        this.txtPlaySinger = (TextView) findViewById(R.id.txtPlaySinger);
        this.btnPlay = (ImageView) findViewById(R.id.btnPlay);
        this.btnStop = (ImageView) findViewById(R.id.btnPause);

        imgPlayPhoto.setImageResource(extras.getInt("photo"));
        txtPlayTitle.setText(extras.getString("title"));
        txtPlaySinger.setText(extras.getString("singer"));
        connectService(extras.getInt("musicId"));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isPlaying = musicPlayerService.play();

                Toast.makeText(musicPlayerService, isPlaying ? "Playing music" : "Pause Music", Toast.LENGTH_SHORT).show();

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isPause = musicPlayerService.pause();

                Toast.makeText(musicPlayerService, isPause ? "Pause Music" : "Playing Music", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void connectService(int musicId) {

        Intent intent = new Intent(this, MusicPlayerService.class);
        intent.putExtra("musicId", musicId);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                MusicPlayerService.MyServiceBinder myBinder = (MusicPlayerService.MyServiceBinder) service;

                musicPlayerService = myBinder.getService();
                isConnected = true;
                musicPlayerService.play();
//                Toast.makeText(musicPlayerService, "Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isConnected = false;
                Toast.makeText(musicPlayerService, "Disconnected", Toast.LENGTH_SHORT).show();
                musicPlayerService = null;

            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}
