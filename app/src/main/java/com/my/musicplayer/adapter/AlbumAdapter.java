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
import com.my.musicplayer.model.Album;
import com.my.musicplayer.model.Playlist;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {
    private static final String TAG = "AlbumAdapter";

    Context context;
    ArrayList<Album> albumArrayList = new ArrayList<>();

    public AlbumAdapter(Context context) {
        this.context = context;
    }

    public void addAlbums(ArrayList<Album> albums){
        albumArrayList.addAll(albums);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_album, parent, false);
        return new AlbumHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        Album album = albumArrayList.get(position);

        Glide.with(context)
                .load(album.getAlbumImage())
                //  .placeholder(R.drawable.progress_animation)
                .centerInside()
                .into(holder.albumImage);

        holder.albumName.setText(album.getAlbumName());
        holder.albumArtist.setText(album.getArtistName());

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }



    public static class AlbumHolder extends RecyclerView.ViewHolder {
        public ImageView albumImage;
        public TextView albumName;
        public TextView albumArtist;
        public ImageView albumShowMoreSettings;

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);

            albumImage = itemView.findViewById(R.id.album_image);
            albumName = itemView.findViewById(R.id.album_name);
            albumArtist = itemView.findViewById(R.id.album_artist_name);
            albumShowMoreSettings = itemView.findViewById(R.id.album_image_show_settings);

        }
    }
}
