package com.my.musicplayer.model;

public class Song implements Cloneable {
    private long SongId;
    private String SongTitle;
    private String SongAlbum;
    private long SongAlbumId;
    private String SongArtist;
    private long SongArtistId;
    private String SongPath;
    private int SongTrackNumber;
    private long SongDuration;

    public Song(long songId, String songTitle, String songAlbum, long songAlbumId, String songArtist, long songArtistId, String songPath, int songTrackNumber, long songDuration) {
        SongId = songId;
        SongTitle = songTitle;
        SongAlbum = songAlbum;
        SongAlbumId = songAlbumId;
        SongArtist = songArtist;
        SongArtistId = songArtistId;
        SongPath = songPath;
        SongTrackNumber = songTrackNumber;
        SongDuration = songDuration;
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public long getSongId() {
        return SongId;
    }

    public void setSongId(long songId) {
        SongId = songId;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getSongAlbum() {
        return SongAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        SongAlbum = songAlbum;
    }

    public long getSongAlbumId() {
        return SongAlbumId;
    }

    public void setSongAlbumId(long songAlbumId) {
        SongAlbumId = songAlbumId;
    }

    public String getSongArtist() {
        return SongArtist;
    }

    public void setSongArtist(String songArtist) {
        SongArtist = songArtist;
    }

    public long getSongArtistId() {
        return SongArtistId;
    }

    public void setSongArtistId(long songArtistId) {
        SongArtistId = songArtistId;
    }

    public String getSongPath() {
        return SongPath;
    }

    public void setSongPath(String songPath) {
        SongPath = songPath;
    }

    public int getSongTrackNumber() {
        return SongTrackNumber;
    }

    public void setSongTrackNumber(int songTrackNumber) {
        SongTrackNumber = songTrackNumber;
    }

    public long getSongDuration() {
        return SongDuration;
    }

    public void setSongDuration(long songDuration) {
        SongDuration = songDuration;
    }
}
