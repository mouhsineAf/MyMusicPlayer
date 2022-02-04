package com.my.musicplayer.utils;

import android.provider.MediaStore;

public class SortMediaStore {

    public SortMediaStore() {
    }

    public interface ArtistSortOrder {

        String ARTIST_NAME = DataBaseHelper.ARTIST_NAME;
        String ARTIST_NUMBER_OF_SONGS = DataBaseHelper.NO_OF_TRACKS_BY_ARTIST;
        String ARTIST_NUMBER_OF_ALBUMS = DataBaseHelper.NO_OF_ALBUMS_BY_ARTIST;
    }
    public interface GenreSortOrder {
        String GENRE_NAME = DataBaseHelper.GENRE_NAME;
        String GENRE_NUMBER_OF_ALBUMS = DataBaseHelper.NO_OF_ALBUMS_IN_GENRE;
    }
    public interface AlbumSortOrder {
        String ALBUM_DEFAULT = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;
        String ALBUM_NAME = MediaStore.Audio.Albums.ALBUM;
        String ALBUM_NUMBER_OF_SONGS = MediaStore.Audio.Albums.NUMBER_OF_SONGS;
        String ALBUM_ARTIST = MediaStore.Audio.Albums.ARTIST;
        String ALBUM_YEAR = MediaStore.Audio.Albums.FIRST_YEAR;
    }
    public interface SongSortOrder {
        String SONG_DEFAULT = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        String SONG_DISPLAY_NAME = MediaStore.Audio.Media.DISPLAY_NAME;
        String SONG_TRACK_NO = MediaStore.Audio.Media.TRACK;
        String SONG_DURATION = MediaStore.Audio.Media.DURATION;
        String SONG_YEAR = MediaStore.Audio.Media.YEAR;
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED;
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;
        String SONG_ARTIST = MediaStore.Audio.Media.ARTIST;
        String SONG_FILENAME = MediaStore.Audio.Media.DATA;
    }


}
