package com.my.musicplayer.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.musicplayer.R;
import com.my.musicplayer.activity.MainActivity;
import com.my.musicplayer.adapter.AlbumAdapter;
import com.my.musicplayer.adapter.ArtistAdapter;
import com.my.musicplayer.model.Album;
import com.my.musicplayer.model.Artist;
import com.my.musicplayer.utils.DataBaseHelper;
import com.my.musicplayer.utils.DataHelper;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistFragment extends Fragment {
    private static final String TAG = "ArtistFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;
    ArrayList<Artist> artistArrayList;
    RecyclerView recyclerViewArtist;
    ArtistAdapter artistAdapter;
    RecyclerView.LayoutManager layoutManagerArtist;
    CompositeDisposable mCompositeDisposable;




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.context = context;
        }
    }

    public ArtistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArtistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistFragment newInstance(String param1, String param2) {
        ArtistFragment fragment = new ArtistFragment();
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
        View view = inflater.inflate(R.layout.fragment_artist, container, false);

        recyclerViewArtist = view.findViewById(R.id.artist_recyclerview);


        mCompositeDisposable = new CompositeDisposable();

        buildAlbumRecycleView();
        loadArtists();

        return view;
    }

    private void buildAlbumRecycleView(){
        recyclerViewArtist.setHasFixedSize(true);
        recyclerViewArtist.setLayoutManager(new LinearLayoutManager(context));
        artistAdapter = new ArtistAdapter(context);
        recyclerViewArtist.setAdapter(artistAdapter);

    }


    private void loadArtists() {
        artistArrayList = new ArrayList<>();
        mCompositeDisposable.add(Observable.fromCallable(() -> getDBAccessHelper().getAllArtist())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ArrayList<Artist>>() {
                    @Override
                    public void onNext(ArrayList<Artist> hashMaps) {
                        artistArrayList = hashMaps;
                        artistAdapter.addArtist(artistArrayList);
                        Log.e(TAG, "onNextSize: " + hashMaps.size());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    public DataBaseHelper getDBAccessHelper() {
        return DataBaseHelper.getDatabaseHelper(context);
    }

}