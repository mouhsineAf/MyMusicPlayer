package com.my.musicplayer.model;

public class Artist {
    private long  artistId;
    private String artistName;
    private String artistAlbumImage;
    private int numberOfTracksByArtist;
    private int numberOfAlbumsByArtist;

    public Artist(long artistId, String artistName, String artistAlbumImage, int numberOfTracksByArtist, int numberOfAlbumsByArtist) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistAlbumImage = artistAlbumImage;
        this.numberOfTracksByArtist = numberOfTracksByArtist;
        this.numberOfAlbumsByArtist = numberOfAlbumsByArtist;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistAlbumImage() {
        return artistAlbumImage;
    }

    public void setArtistAlbumImage(String artistAlbumImage) {
        this.artistAlbumImage = artistAlbumImage;
    }

    public int getNumberOfTracksByArtist() {
        return numberOfTracksByArtist;
    }

    public void setNumberOfTracksByArtist(int numberOfTracksByArtist) {
        this.numberOfTracksByArtist = numberOfTracksByArtist;
    }

    public int getNumberOfAlbumsByArtist() {
        return numberOfAlbumsByArtist;
    }

    public void setNumberOfAlbumsByArtist(int numberOfAlbumsByArtist) {
        this.numberOfAlbumsByArtist = numberOfAlbumsByArtist;
    }
}
