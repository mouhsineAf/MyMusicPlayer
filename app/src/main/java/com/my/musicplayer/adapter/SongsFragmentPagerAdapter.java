package com.my.musicplayer.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.my.musicplayer.fragment.AlbumFragment;
import com.my.musicplayer.fragment.AllSongsFragment;
import com.my.musicplayer.fragment.ArtistFragment;
import com.my.musicplayer.fragment.FolderFragment;
import com.my.musicplayer.fragment.GenresFragment;
import com.my.musicplayer.fragment.PlaylistFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SongsFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private String mPageTile[];
    private ArrayList<Fragment> fragments;

    public SongsFragmentPagerAdapter(FragmentManager fm, String pageTiles[]) {
        super(fm);
        mPageTile=pageTiles;
        fragments = new ArrayList<>();

        mFragmentManager = fm;
        mFragmentTags = new HashMap<>();

        for (String tab : mPageTile) {
            if (tab.equalsIgnoreCase("All Songs")) {
                fragments.add(new AllSongsFragment());
            } else if (tab.equalsIgnoreCase("Playlists")) {
                fragments.add(new PlaylistFragment());
            } else if (tab.equalsIgnoreCase("Albums")) {
                fragments.add(new AlbumFragment());
            } else if (tab.equalsIgnoreCase("Artists")) {
                fragments.add(new ArtistFragment());
            } else if (tab.equalsIgnoreCase("Genres")) {
                fragments.add(new GenresFragment());
            } else if (tab.equalsIgnoreCase("Folders")) {
                fragments.add(new FolderFragment());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTile[position];
    }

    @Override
    public int getCount() {
        return 6;
    }

}
