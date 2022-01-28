package com.my.musicplayer.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.musicplayer.R;
import com.my.musicplayer.adapter.PlaylistAdapter;
import com.my.musicplayer.async.AsyncFetchPlaylist;
import com.my.musicplayer.model.Playlist;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Playlist> playlistArrayList;
    RecyclerView recyclerViewPlaylist;
    PlaylistAdapter playlistAdapter;
    LinearLayoutManager horizontalLayoutManagerPlaylist;






    public HomeFragment() {
        // Required empty public constructor
    }

    Activity activity;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        activity = a;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewPlaylist = view.findViewById(R.id.playlist_recyclerview);


        buildTipsRecycleView();
        loadPlaylist();

        return view;
    }


    private void buildTipsRecycleView(){
        recyclerViewPlaylist.setHasFixedSize(true);
        horizontalLayoutManagerPlaylist = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPlaylist.setLayoutManager(horizontalLayoutManagerPlaylist);
        playlistAdapter = new PlaylistAdapter(activity);
        recyclerViewPlaylist.setAdapter(playlistAdapter);
        playlistAdapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Playlist> playlists) {

            }
        });

    }

    private void loadPlaylist(){
        AsyncFetchPlaylist asyncFetchPlaylist = new AsyncFetchPlaylist(getContext(), new AsyncFetchPlaylist.CallbackPlaylist() {
            @Override
            public void processData(ArrayList<Playlist> hashMaps) {
                playlistArrayList = new ArrayList<>();
                Log.e(TAG, "playlistArrayList: " + hashMaps.toString());
                playlistArrayList.addAll(hashMaps);
                playlistAdapter.addPlaylist(playlistArrayList);
            }
        });
        asyncFetchPlaylist.execute();
    }
}