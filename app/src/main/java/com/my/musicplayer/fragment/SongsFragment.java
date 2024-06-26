package com.my.musicplayer.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.my.musicplayer.Config;
import com.my.musicplayer.R;
import com.my.musicplayer.adapter.SongsFragmentPagerAdapter;
import com.my.musicplayer.utils.Shared;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Shared shared;
    TabLayout tabLayoutSongs;
    ViewPager viewPagerSongs;
    SongsFragmentPagerAdapter songsFragmentPagerAdapter;
    private ArrayList<Fragment> mFragments;


    public SongsFragment() {
        // Required empty public constructor
    }


    Context activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SongsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SongsFragment newInstance(String param1, String param2) {
        SongsFragment fragment = new SongsFragment();
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
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        tabLayoutSongs = view.findViewById(R.id.tabLayoutSongs);
        viewPagerSongs = view.findViewById(R.id.viewPageSongs);


        //
        shared = new Shared(activity);


        buildTabs();

        return view;
    }

    private void buildTabs(){
        String[] tabs = getTabs();
        songsFragmentPagerAdapter = new SongsFragmentPagerAdapter(getParentFragmentManager(), tabs);

        viewPagerSongs.setAdapter(songsFragmentPagerAdapter);

        setDefaultTab(tabs);

        viewPagerSongs.setOffscreenPageLimit(5);

        tabLayoutSongs.setupWithViewPager(viewPagerSongs);
    }


    private String[] getTabs() {
        String titles = shared.getString(Config.SharedSongsTab, "");
        if (titles.isEmpty()) {
            String[] tabTitles = new String[]{"All Songs", "Playlists", "Albums", "Artists", "Genres", "Folders"};
            saveTabs(tabTitles);
            return tabTitles;
        } else {
            Gson gson = new Gson();
            return gson.fromJson(titles, String[].class);
        }
    }


    public void saveTabs(String[] tabs) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(tabs);
        shared.putString(Config.SharedSongsTab, jsonText);
    }

    private void setDefaultTab(String[] tabs) {
        String defaultScreen = shared.getString(Config.SharedSongsDefaultTabSelected, "All Songs");
        for (int i = 0; i < tabs.length; i++) {
            if (tabs[i].equalsIgnoreCase(defaultScreen)) {
                viewPagerSongs.setCurrentItem(i);
                break;
            }
        }

    }


}