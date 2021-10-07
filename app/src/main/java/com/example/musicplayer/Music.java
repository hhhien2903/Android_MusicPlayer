package com.example.musicplayer;

public class Music {
    private String title;
    private int picture;
    private int data;
    private String singer;

    public Music(String title, String singer, int data, int picture) {
        this.title = title;
        this.picture = picture;
        this.data = data;
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
