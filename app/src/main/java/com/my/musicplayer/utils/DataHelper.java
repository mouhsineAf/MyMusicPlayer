package com.my.musicplayer.utils;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.my.musicplayer.model.Song;

import java.util.ArrayList;

public class DataHelper {

    public static ArrayList<Song> getTracksForSelection(String from, String selectionCondition) {
      //  Common mApp = (Common) Common.getInstance();

        String[] columns = {
                BaseColumns._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.ARTIST_ID,

        };

        String selection = null;
        Uri uri = null;
        String selectionArgs[] = null;
        String sortBy = null;

        if (from.equalsIgnoreCase("ALBUMS")) {
            selection = "is_music=1 AND title != '' AND " + MediaStore.Audio.Media.ALBUM_ID + "=?";
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            selectionArgs = new String[]{selectionCondition};
            sortBy = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        } else if (from.equalsIgnoreCase("ARTIST")) {
            selection = "is_music=1 AND title != '' AND " + MediaStore.Audio.Media.ARTIST_ID + "=?";
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            selectionArgs = new String[]{selectionCondition};
            sortBy = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        } else if (from.equalsIgnoreCase("GENRES")) {
            uri = MediaStore.Audio.Genres.Members.getContentUri("external", Long.parseLong(selectionCondition));
            sortBy = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        } else if (from.equalsIgnoreCase("PLAYLISTS")) {
          //  if (selectionCondition.equalsIgnoreCase("-1")) {
          //      int x = PreferencesHelper.getInstance().getInt(PreferencesHelper.Key.RECENTLY_ADDED_WEEKS, 1) * (3600 * 24 * 7);
          //      selection = MediaStore.MediaColumns.DATE_ADDED + ">" + (System.currentTimeMillis() / 1000 - x) + " AND is_music=1";
          //      sortBy = MediaStore.Audio.Media.DATE_ADDED + " DESC";
          //      uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          //  } else if (selectionCondition.equalsIgnoreCase("-2")) {
          //      return mApp.getDBAccessHelper().getFavorites();
          //  } else if (selectionCondition.equalsIgnoreCase("-3")) {
          //      return mApp.getDBAccessHelper().getTopTracks();
          //  } else if (selectionCondition.equalsIgnoreCase("-4")) {
          //      return mApp.getDBAccessHelper().getRecentlyPlayed();
          //  } else {
          //      columns[0] = MediaStore.Audio.Playlists.Members.AUDIO_ID;
          //      uri = MediaStore.Audio.Playlists.Members.getContentUri("external", Long.parseLong(selectionCondition));
          //  }
        } else if (from.equalsIgnoreCase("SONGS")) {
            selection = "is_music=1 AND title != ''";
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          //  sortBy = PreferencesHelper.getInstance().getString(PreferencesHelper.Key.SONG_SORT_ORDER, MediaStore.Audio.Media.DEFAULT_SORT_ORDER) +
          //          PreferencesHelper.getInstance().getString(PreferencesHelper.Key.SONG_SORT_TYPE, " ASC");
        }


        ArrayList<Song> songs = new ArrayList<>();

        Cursor cursor = Common.getInstance().getContentResolver().query(
                uri,
                columns,
                selection,
                selectionArgs,
                sortBy);

        int audioIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

        if (!selectionCondition.equalsIgnoreCase("-1") && from.equalsIgnoreCase("PLAYLISTS")) {
            audioIndex = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID);
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Song song = new Song(
                        cursor.getLong(audioIndex),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK)),
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                );
                songs.add(song);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return songs;
    }

}
