package com.my.musicplayer.model;

public class Album {
    private long albumId;
    private String albumName;
    private String artistName;
    private String albumImage;

    public Album(long albumId, String albumName, String artistName, String albumImage) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumImage = albumImage;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }
}
