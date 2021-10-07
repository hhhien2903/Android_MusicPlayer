package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.Fade;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public MusicAdapter musicAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Music> musicArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fade fade = new Fade();
//        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        Music bigcityboy = new Music("Big City Boy","Touliver x Binz", R.raw.bigcityboy, R.drawable.bigcityboy_photo);
        Music castleonthehill = new Music("Castle On The Hill","Ed Sheeren", R.raw.castleonthehill, R.drawable.castleonthehill_photo);
        Music krazy = new Music("Krazy","Binz x Andree Right Hand", R.raw.krazy, R.drawable.krazy_photo);
        Music muonroimasaocon = new Music("Muộn Rồi Mà Sao Còn","Sơn Tùng MTP", R.raw.muonroimasaocon, R.drawable.muonroimasaocon_photo);
        Music ok = new Music("OK","Binz", R.raw.ok, R.drawable.ok_photo);
        Music thinkingoutloud = new Music("Thinking Out Loud","Ed Sheeren", R.raw.thinkingoutloud, R.drawable.thinkingoutloud_photo);
        musicArrayList = new ArrayList<Music>(Arrays.asList(bigcityboy,castleonthehill, krazy, muonroimasaocon, ok,thinkingoutloud));


        recyclerView = findViewById(R.id.recyclerView);
        musicAdapter=new MusicAdapter(musicArrayList,this);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager layoutManager= new GridLayoutManager(MainActivity.this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

}