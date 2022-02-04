package com.my.musicplayer.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.util.Log;

import com.my.musicplayer.App;
import com.my.musicplayer.model.Album;
import com.my.musicplayer.model.Song;

import java.io.File;
import java.util.ArrayList;

public class DataHelper {

    public static ArrayList<Song> getTracksForSelection(Context context, String from, String selectionCondition) {
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
               // MediaStore.Audio.Media.MED

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
            sortBy = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
            Log.e("DataHelper", "Uri: " + uri.toString());

            //  sortBy = PreferencesHelper.getInstance().getString(PreferencesHelper.Key.SONG_SORT_ORDER, MediaStore.Audio.Media.DEFAULT_SORT_ORDER) +
          //          PreferencesHelper.getInstance().getString(PreferencesHelper.Key.SONG_SORT_TYPE, " ASC");
        }


        ArrayList<Song> songs = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(
                uri,
                columns,
                selection,
                selectionArgs,
                sortBy);

        int audioIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

        if (!selectionCondition.equalsIgnoreCase("-1") && from.equalsIgnoreCase("PLAYLISTS")) {
            audioIndex = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID);
        }

        Log.e("DataHelper", "Cursor: " + cursor.toString());

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
                Log.e("DataHelper", "songs cursor: " + cursor.toString());

                songs.add(song);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        Log.e("DataHelper", "Songs: " + songs.toString());
        return songs;
    }

    public static ArrayList<Album> getAlbumsList(Context context) {

      //  String sort = PreferencesHelper.getInstance().getString(PreferencesHelper.Key.ALBUM_SORT_ORDER, SortOrder.AlbumSortOrder.ALBUM_DEFAULT)
      //          + PreferencesHelper.getInstance().getString(PreferencesHelper.Key.ALBUM_SORT_TYPE, Constants.ASCENDING);
        String sort = SortMediaStore.AlbumSortOrder.ALBUM_DEFAULT;


        String[] columns = {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART
        };
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                sort);

        ArrayList<Album> albums = new ArrayList<>();


        if (cursor != null && cursor.moveToFirst()) {
            do {
                Album album = new Album(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                albums.add(album);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        return albums;

    }


/*
    public static Boolean buildMusicLibrary() {

        int progress = 0;
        Common mApp = (Common) Common.getInstance().getApplicationContext();
        if (shouldBeScanned()) {
        } else {
            return false;
        }

        try {
            //Query to filter out the genre with no songs in them.
            saveEQPresets(mApp);
            saveTabTitles(new String[]{"ALBUMS", "ARTISTS", "SONGS", "GENRES", "PLAYLISTS", "DIRECTORY"});
            String query = "_id in (select genre_id from audio_genres_map where audio_id in (select _id from audio_meta where is_music != 0))";
            //Initialize the database transaction manually (improves performance).
            mApp.getDBAccessHelper().getWritableDatabase().beginTransaction();


            //Genre Projection.
            String[] columns = {
                    MediaStore.Audio.Genres._ID,
                    MediaStore.Audio.Genres.NAME,
            };


            Cursor cursor = Common.getInstance()
                    .getContentResolver()
                    .query(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI,
                            columns,
                            query,
                            null,
                            MediaStore.Audio.Genres.NAME);


            try {
                mApp.getDBAccessHelper().getWritableDatabase().delete(DataBaseHelper.GENRES_TABLE, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        ContentValues genre = new ContentValues();
                        genre.put(DataBaseHelper.GENRE_ID, cursor.getString(0));
                        genre.put(DataBaseHelper.GENRE_NAME, cursor.getString(1));

                        ArrayList<Album> albums = getAlbumsForSelection("GENRES", cursor.getString(0));
                        if (albums != null && albums.size() > 0) {
                            genre.put(DataBaseHelper.GENRE_ALBUM_ART, MusicUtils.getAlbumArtUri(albums.get(0)._Id).toString());
                            genre.put(DataBaseHelper.NO_OF_ALBUMS_IN_GENRE, "" + albums.size());
                        }
                        mApp.getDBAccessHelper().getWritableDatabase().insert(DataBaseHelper.GENRES_TABLE, null, genre);
                    } while (cursor.moveToNext());
                }


                String[] artistCols = {
                        MediaStore.Audio.Artists._ID,
                        MediaStore.Audio.Artists.ARTIST,
                        MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
                        MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                };

                Cursor artistCursor = Common.getInstance()
                        .getContentResolver()
                        .query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                                artistCols,
                                null,
                                null,
                                MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);

                mApp.getDBAccessHelper().getWritableDatabase().delete(DataBaseHelper.ARTIST_TABLE, null, null);

                if (artistCursor != null && artistCursor.moveToFirst()) {
                    if (onProgressUpdate != null)
                        onProgressUpdate.maxProgress(artistCursor.getCount());
                    String path = new File(Common.getInstance().getCacheDir(), "artistThumbnails").getAbsolutePath() + "/";
                    do {
                        ContentValues artist = new ContentValues();

                        artist.put(DataBaseHelper.ARTIST_ID, artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists._ID)));
                        artist.put(DataBaseHelper.ARTIST_NAME, artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
                        artist.put(DataBaseHelper.NO_OF_TRACKS_BY_ARTIST, artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)));
                        artist.put(DataBaseHelper.NO_OF_ALBUMS_BY_ARTIST, artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)));

                        ArrayList<Album> albums = getAlbumsForSelection("ARTIST", artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists._ID)));

                        if (albums != null && albums.size() > 0) {
                            File cacheFile = new File(path + artistCursor.getString(artistCursor.getColumnIndex(MediaStore.Audio.Artists._ID)));
                            if (cacheFile.exists()) {
                                artist.put(DataBaseHelper.ARTIST_ALBUM_ART, "file://" + cacheFile.getAbsolutePath());
                            } else {
                                artist.put(DataBaseHelper.ARTIST_ALBUM_ART, MusicUtils.getAlbumArtUri(albums.get(0)._Id).toString());
                            }
                        }

                        mApp.getDBAccessHelper().getWritableDatabase().insert(DataBaseHelper.ARTIST_TABLE, null, artist);
                        if (onProgressUpdate != null)
                            onProgressUpdate.onProgressed(progress++);
                    } while (artistCursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.log("CAUSE " + e.getCause());
            } finally {
                mApp.getDBAccessHelper().getWritableDatabase().setTransactionSuccessful();
                mApp.getDBAccessHelper().getWritableDatabase().endTransaction();
                PreferencesHelper.getInstance().put(PreferencesHelper.Key.FIRST_LAUNCH, false);
            }

            if (cursor != null) {
                cursor.close();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

*/
}
