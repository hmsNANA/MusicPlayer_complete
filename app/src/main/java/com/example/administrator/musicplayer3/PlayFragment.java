package com.example.administrator.musicplayer3;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import android.os.Handler;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;

/**
 * Created by Administrator on 2017/5/6.
 */

public class PlayFragment extends Fragment {
    private Button btnStart, btnLast, btnNext, btnStop, btnPause, OrderPlay,RandomPlay,btnPlayOrder;
    public MediaPlayer mediaPlayer = new MediaPlayer();

    private List<MusicBean> list;
    public int listSize;
    public int playPosition;
    //public String MusicPath=getArguments().getString("MusicPath");
    //public int listSize=getArguments().getInt("MusicCount");
    public ImageView imgSong;
    public int isPlaying = 0;

    private SeekBar sb;
    private Chronometer et_time;

    private int mPosition;
    private OnCompletionListener onCompletionListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play, container, false);
        View view2 = inflater.inflate(R.layout.my_menu, container, false);

        btnStart = (Button) view.findViewById(R.id.btnplay);
        btnStart.setOnClickListener(new PlayButton());

        OrderPlay = (Button) view2.findViewById(R.id.orderplay);
        OrderPlay.setOnClickListener(new Orderplay());
        RandomPlay = (Button) view2.findViewById(R.id.randomplay);
        RandomPlay.setOnClickListener(new Randomplay());

        btnPause = (Button) view.findViewById(R.id.btnpause);
        btnPause.setOnClickListener(new NotPlayButton());
        btnStop = (Button) view.findViewById(R.id.btnstop);
        btnStop.setOnClickListener(new NotPlayButton2());
        btnLast = (Button) view.findViewById(R.id.btnlaster);
        btnLast.setOnClickListener(new lastSong());
        btnNext = (Button) view.findViewById(R.id.btnnext);
        btnNext.setOnClickListener(new nextSong());
        list = new ArrayList<>();
        list = MusicQuerry.getMusicData(this.getActivity());
        listSize = list.size();
        playPosition = getArguments().getInt("MusicPosition");

        return view;
    }


    class lastSong implements View.OnClickListener {
        public void onClick(View view) {
            if (isPlaying == 1) {
                LastSong();
            }
        }
    }

    class nextSong implements View.OnClickListener {
        public void onClick(View view) {
            if (isPlaying == 1) {
                NextSong();
            }

        }
    }

    class PlayButton implements View.OnClickListener {
        public void onClick(View view) {
            if (isPlaying == 0) {
                play();
            }
        }
    }

    class NotPlayButton implements View.OnClickListener {
        public void onClick(View view) {
            if (isPlaying == 1) {
                pause();
            }
        }
    }

    class NotPlayButton2 implements View.OnClickListener {
        public void onClick(View view) {
            if (isPlaying == 1) {
                stop();
            }
        }
    }

    public void NextSong() {
        if (playPosition == listSize - 1) {
            playPosition = 0;
            playMusic(list.get(playPosition).Path);
        } else {
            playPosition++;
            playMusic(list.get(playPosition).Path);
        }
    }

    public void LastSong() {
        if (playPosition <= 0) {
            playPosition = listSize - 1;
            playMusic(list.get(playPosition).Path);
        } else {
            playPosition--;
            playMusic(list.get(playPosition).Path);
        }
    }

    public void pause() {
        mediaPlayer.pause();
        isPlaying = 0;
    }

    public void play() {
        mediaPlayer.start();
        isPlaying = 1;
    }

    public void stop() {
        mPosition = mediaPlayer.getCurrentPosition();
        mPosition = 0;
    }

    public void playMusic(String Path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();

                    isPlaying = 1;
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        public void run() {
            // 获得歌曲现在播放位置并设置成播放进度条的值
            if (mediaPlayer != null) {
                sb.setProgress(mediaPlayer.getCurrentPosition());
                // 每次延迟100毫秒再启动线程
                handler.postDelayed(updateThread, 100);
            }
        }
    };
    class Orderplay implements View.OnClickListener{
        public void onClick(View view){
            OrderPlayMode();
        }
    }
    class Randomplay implements View.OnClickListener{
        public void onClick(View view){
            RandomPlayMode();
        }
    }

    public void OrderPlayMode(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mediaPlayer){
                if(playPosition==(listSize-1)){
                    playPosition=0;
                    playMusic(list.get(playPosition).Song);
                }else{
                    //playPosition++;
                   // playMusic(list.get(playPosition).Path);
                    NextSong();
                }
            }
        });
    }
    public void RandomPlayMode(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mediaPlayer){
                if(playPosition==(listSize-1)){
                    playPosition=0;
                    playMusic(list.get(playPosition).Song);
                }else{
                    playPosition = (int)(Math.random() * (list.size() - 1));
                    playMusic(list.get(playPosition).Path);
                }
            }
        });
    }
}



