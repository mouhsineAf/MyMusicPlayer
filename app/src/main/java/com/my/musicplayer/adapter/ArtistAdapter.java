package com.my.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.musicplayer.R;
import com.my.musicplayer.model.Album;
import com.my.musicplayer.model.Artist;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {
    private static final String TAG = "ArtistAdapter";

    Context context;
    ArrayList<Artist> artistArrayList = new ArrayList<>();

    public ArtistAdapter(Context context) {
        this.context = context;
    }

    public void addArtist(ArrayList<Artist> artistArrayList) {
        this.artistArrayList = artistArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_album, parent, false);
        return new ArtistHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        Artist artist = artistArrayList.get(position);

        Glide.with(context)
                .load(artist.getArtistAlbumImage())
                //  .placeholder(R.drawable.progress_animation)
                .centerInside()
                .into(holder.artistImage);

        holder.artistName.setText(artist.getArtistName());
        holder.artistNumOfAlbums.setText(artist.getNumberOfAlbumsByArtist());
        holder.artistNumOfSongs.setText(artist.getNumberOfTracksByArtist());

    }

    @Override
    public int getItemCount() {
        return artistArrayList.size();
    }

    public static class ArtistHolder extends RecyclerView.ViewHolder {
        public ImageView artistImage;
        public TextView artistName;
        public TextView artistNumOfAlbums;
        public TextView artistNumOfSongs;
        public ImageView artistShowSettings;

        public ArtistHolder(@NonNull View itemView) {
            super(itemView);
            artistImage = itemView.findViewById(R.id.artist_image);
            artistName = itemView.findViewById(R.id.artist_name);
            artistNumOfAlbums = itemView.findViewById(R.id.text_number_of_albums);
            artistNumOfSongs = itemView.findViewById(R.id.text_number_of_songs);
            artistShowSettings = itemView.findViewById(R.id.artist_image_show_settings);

        }
    }
}
