package com.my.musicplayer.adapter;

import android.annotation.SuppressLint;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlayListHolder> {
    private static final String TAG = "PlaylistAdapter";

    Context context;
    ArrayList<Playlist> playlistArrayList = new ArrayList<>();
    OnItemClickListener mListener;

    public PlaylistAdapter(Context context) {
        this.context = context;
    }



    public void addPlaylist(ArrayList<Playlist> playlists){
        playlistArrayList.addAll(playlists);
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
    public PlayListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_playlist, parent, false);
        return new PlayListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListHolder holder, @SuppressLint("RecyclerView") int position) {
        Playlist playlist = playlistArrayList.get(position);

        Glide.with(context)
                .load(R.drawable.image_play_list1)
                //  .placeholder(R.drawable.progress_animation)
                .centerInside()
                .into(holder.playlistImage);

        holder.playlistSongName.setText(playlist.getPlaylistName());
        holder.playlistArtistName.setText(playlist.getPlaylistName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position, playlistArrayList);

            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public static class PlayListHolder extends RecyclerView.ViewHolder{
        public ImageView playlistImage;
        public TextView playlistSongName;
        public TextView playlistArtistName;

        public PlayListHolder(@NonNull View itemView) {
            super(itemView);

            playlistImage = itemView.findViewById(R.id.play_list_image);
            playlistSongName = itemView.findViewById(R.id.play_list_song_name);
            playlistArtistName = itemView.findViewById(R.id.play_list_artist_name);
        }
    }
}
