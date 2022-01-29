package com.my.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.musicplayer.R;
import com.my.musicplayer.model.Playlist;
import com.my.musicplayer.model.Song;

import java.util.ArrayList;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.AllSongsHolder> {
    private static final String TAG = "AllSongsAdapter";

    ArrayList<Song> songArrayList = new ArrayList<>();
    Context context;
    OnItemClickListener mListener;

    public AllSongsAdapter(Context context) {
        this.context = context;
    }

    public void addPlaylist(ArrayList<Song> songs){
        songArrayList.addAll(songs);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ArrayList<Playlist> playlists);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AllSongsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_songs_played, parent, false);
        return new AllSongsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllSongsHolder holder, int position) {
        Song song = songArrayList.get(position);

        Glide.with(context)
                .load(R.drawable.image_play_list1)
                //  .placeholder(R.drawable.progress_animation)
                .centerInside()
                .into(holder.songIcon);

        holder.songName.setText(song.getSongTitle());
        holder.songArtist.setText(song.getSongArtist());
        holder.songTime.setText(String.valueOf(song.getSongDuration()));
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public static class AllSongsHolder extends RecyclerView.ViewHolder{
        public ImageView songIcon;
        public TextView songName;
        public TextView songArtist;
        public ImageView imagePlay;
        public TextView songTime;

        public AllSongsHolder(@NonNull View itemView) {
            super(itemView);
            songIcon = itemView.findViewById(R.id.song_icon);
            songName = itemView.findViewById(R.id.song_name);
            songArtist = itemView.findViewById(R.id.artist_name);
            imagePlay = itemView.findViewById(R.id.image_played);
            songTime = itemView.findViewById(R.id.song_time);
        }
    }
}
