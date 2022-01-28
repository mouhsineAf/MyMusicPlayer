package com.my.musicplayer.model;

public class Playlist {
    private long playlistId;
    private String playlistName;
    private String playlistArtistName;
    private String playlistImage;

    public Playlist(long playlistId, String playlistName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
    }

    public Playlist(long playlistId, String playlistName, String playlistArtistName, String playlistImage) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistArtistName = playlistArtistName;
        this.playlistImage = playlistImage;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistArtistName() {
        return playlistArtistName;
    }

    public void setPlaylistArtistName(String playlistArtistName) {
        this.playlistArtistName = playlistArtistName;
    }

    public String getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }
}
