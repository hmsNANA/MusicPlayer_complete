package com.example.administrator.musicplayer3;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.AdapterView;
/**
 * Created by Administrator on 2017/5/6.
 */
public class SongListFrag extends Fragment {
    private ListView MusicList;
    private List<MusicBean> list;
    private MyAdapter myadapter;

    String MusicPath;
    public int listSize;
    public int playPosition;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.music_list,container,false);
        MusicList=(ListView)view.findViewById(R.id.music_List) ;
        initView();
        return view;
    }
    private void initView(){
        //MusicList=(ListView) getActivity().findViewById(R.id.MusicList);
        list=new ArrayList<>();
        list=MusicQuerry.getMusicData(this.getActivity());
        myadapter=new MyAdapter(this,list);
        MusicList.setAdapter(myadapter);

       /* MusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlayFragment playFragment=new PlayFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.left,playFragment);
                transaction.commit();
                playFragment.playMusic(list.get(i).Path);
            }
        });*/
        MusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //PlayFragment playFragment=new PlayFragment();
                MainActivity MA=(MainActivity)getActivity();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                playPosition=i;
                listSize=list.size();
                MusicPath=list.get(i).Path;
                Bundle bundle=new Bundle();
                bundle.putInt("MusicPosition",playPosition);
                MA.playFragment.setArguments(bundle);
                //Bundle bundle1=new Bundle();
                //bundle.putInt("MusicCount",listSize);
                //mp.musicListFragment.setArguments(bundle1);
                //Bundle bundle2=new Bundle();
                //bundle2.getString("MusicPath",MusicPath);
                //mp.musicListFragment.setArguments(bundle2);
                transaction.replace(R.id.mainlayout,MA.playFragment);
                transaction.commit();
                MA.playFragment.playMusic(list.get(i).Path);
                // playPosition=i;
                // Bundle bundle=new Bundle();
                // bundle.putInt("MusicPosition",playPosition);
                //mp.musicListFragment.setArguments(bundle);
            }
        });
    }
}