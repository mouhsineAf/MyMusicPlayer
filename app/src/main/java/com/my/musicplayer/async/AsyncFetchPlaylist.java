package com.my.musicplayer.async;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.my.musicplayer.R;
import com.my.musicplayer.model.Playlist;

import java.util.ArrayList;

public class AsyncFetchPlaylist extends AsyncTask<Void, Void, ArrayList<Playlist>> {
    private ArrayList<Playlist> mPlayList;
    private Cursor mCursor;
    private Context mContext;
    private CallbackPlaylist callbackPlaylist;

    public AsyncFetchPlaylist(Context cx, CallbackPlaylist callbackPlaylist) {
        mContext = cx;
        mPlayList = new ArrayList<>();
        this.callbackPlaylist = callbackPlaylist;
    }


    @Override
    protected ArrayList<Playlist> doInBackground(Void... params) {
        try {
            String[] columns = new String[]{
                    BaseColumns._ID,
                    MediaStore.Audio.Playlists._ID,
                    MediaStore.Audio.Playlists.NAME,
                    MediaStore.Audio.Playlists.DATA,

            };
            mCursor = mContext.getContentResolver().query(
                    MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    MediaStore.Audio.Playlists.DEFAULT_SORT_ORDER);


            addDefaultPlayLists();
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    Playlist song = new Playlist(mCursor.getLong(1),mCursor.getString(2));
                    mPlayList.add(song);
                } while (mCursor.moveToNext());
            }
            if (mCursor != null) {
                mCursor.close();
                mCursor = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mPlayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Playlist> hashMaps) {
        super.onPostExecute(hashMaps);
        callbackPlaylist.processData(hashMaps);
    }

    private void addDefaultPlayLists() {

       Playlist recently = new Playlist(-1, mContext.getString(R.string.recently_added));
       mPlayList.add(0, recently);

       Playlist favorites = new Playlist(-2, mContext.getString(R.string.favorites));
       mPlayList.add(1, favorites);

       Playlist topTracks = new Playlist(-3, mContext.getString(R.string.top_played));
       mPlayList.add(2, topTracks);

       Playlist recentlyPlayed = new Playlist(-4, mContext.getString(R.string.recently_played));
       mPlayList.add(3, recentlyPlayed);

    }

    public interface CallbackPlaylist {
        void processData(ArrayList<Playlist> hashMaps);
    }
}
