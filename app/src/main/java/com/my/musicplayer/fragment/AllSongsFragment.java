package com.my.musicplayer.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.musicplayer.R;
import com.my.musicplayer.adapter.AllSongsAdapter;
import com.my.musicplayer.adapter.PlaylistAdapter;
import com.my.musicplayer.model.Playlist;
import com.my.musicplayer.model.Song;
import com.my.musicplayer.utils.Common;
import com.my.musicplayer.utils.DataHelper;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllSongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllSongsFragment extends Fragment {
    private static final String TAG = "AllSongsFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private CompositeDisposable mCompositeDisposable;
    private Common common;

    AllSongsAdapter allSongsAdapter;
    RecyclerView recyclerViewAllSong;
    ArrayList<Song> songArrayList;


    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public AllSongsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllSongsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllSongsFragment newInstance(String param1, String param2) {
        AllSongsFragment fragment = new AllSongsFragment();
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
        View view =  inflater.inflate(R.layout.fragment_all_songs, container, false);

        recyclerViewAllSong = view.findViewById(R.id.all_songs_recyclerview);


        mCompositeDisposable = new CompositeDisposable();
  //      common = (Common) context.getApplicationContext();


        buildAllSongsRecycleView();
        loadSongs();

        return view;
    }


    private void buildAllSongsRecycleView(){
        recyclerViewAllSong.setHasFixedSize(true);
        recyclerViewAllSong.setLayoutManager(new LinearLayoutManager(context));
        allSongsAdapter = new AllSongsAdapter(context);
        recyclerViewAllSong.setAdapter(allSongsAdapter);


    }


    private void loadSongs() {
        songArrayList = new ArrayList<>();
        mCompositeDisposable.add(Observable.fromCallable(() -> DataHelper.getTracksForSelection(context, "SONGS", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<Song>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());

                    }

                    @Override
                    public void onNext(ArrayList<Song> data) {
                        Log.e(TAG, "data.size(): " + data.size());
                        songArrayList.clear();
                        songArrayList.addAll(data);
                        allSongsAdapter.addPlaylist(data);
                    }
                }));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCompositeDisposable.dispose();
    }

}