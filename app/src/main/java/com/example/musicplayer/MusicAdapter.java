package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private ArrayList<Music> musicArrayList;
    private LayoutInflater mInflater;
    private Context context;

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MusicViewHolder svh=new MusicViewHolder(view);
        return svh;
    }

    public MusicAdapter(ArrayList<Music> musicArrayList, Context context) {
        mInflater = LayoutInflater.from(context);
        this.musicArrayList = musicArrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Music music= musicArrayList.get(position);
        holder.txtItemTitle.setText(music.getTitle());
        holder.txtItemSinger.setText(music.getSinger());
        holder.imgItemPicture.setImageResource(music.getPicture());
        holder.music = music;
        holder.position = position;

    }

    @Override
    public int getItemCount() {
        return musicArrayList.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView txtItemTitle;
        public TextView txtItemSinger;
        public ImageView imgItemPicture;
        public Music music;
        public int position;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemTitle=itemView.findViewById(R.id.txtItemTitle);
            txtItemSinger=itemView.findViewById(R.id.txtItemSinger);
            imgItemPicture=itemView.findViewById(R.id.imgItemPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int mPosition=getLayoutPosition();
                    Intent i=new Intent(context, PlayActivity.class);
                    i.putExtra("title",music.getTitle());
                    i.putExtra("photo",music.getPicture());
                    i.putExtra("singer",music.getSinger());
                    i.putExtra("musicId",music.getData());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context, imgItemPicture, imgItemPicture.getTransitionName());
                    context.startActivity(i, options.toBundle());
                }
            });
        }

    }


}
