package com.example.administrator.musicplayer3;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import android.view.View.OnClickListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private Button btnPlaying;
    private Button btnMusicList;
    DrawerLayout dl;
    Button Personal;
    RelativeLayout rlRight;
    private ListView mListView;
    private List<MusicBean> list;
    private MyAdapter adapter;

    public int isPlaying=0;
    SongListFrag songlistfrag=new SongListFrag();
    PlayFragment playFragment=new PlayFragment();
    FragmentManager fragmentManager=getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMusicList=(Button)findViewById(R.id.mysong);
        btnMusicList.setOnClickListener(new GoPlayFragment());
        btnPlaying=(Button)findViewById(R.id.btnToplay);
        btnPlaying.setOnClickListener(new GoMusiclistFragment());
    }
/*
    private void initView() {
        Personal = (Button) findViewById(R.id.personal);
        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        rlRight = (RelativeLayout) findViewById(R.id.left);
        // 关闭手势滑动
        dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


       // mListView = (ListView) findViewById(R.id.music_list);
        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = FindSongs.getMusicData(this);

       // mListView.setAdapter(adapter);
    }
    private void initData() {
        Personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 点击按钮打开侧滑菜单
                if (!dl.isDrawerOpen(rlRight)) {
                    dl.openDrawer(rlRight);
                }
            }
        });

    }

*/
    class GoPlayFragment implements OnClickListener{
        public void onClick(View view){
            SongListFrag musicListFragment=new SongListFrag();
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.mainlayout,musicListFragment);
            transaction.commit();
        }
    }

    class GoMusiclistFragment implements OnClickListener{
        public void onClick(View view){
            //MusicListFragment musicListFragment=new MusicListFragment();
            //FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.mainlayout,songlistfrag);
            transaction.commit();
        }
    }

}
