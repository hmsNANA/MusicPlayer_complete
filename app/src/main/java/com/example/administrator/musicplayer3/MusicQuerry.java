package com.example.administrator.musicplayer3;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */

public class MusicQuerry {
    public static List<MusicBean> getMusicData(Context context){
        List<MusicBean> list=new ArrayList<MusicBean>();
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.AudioColumns.IS_MUSIC);
        if(cursor!=null){
            while (cursor.moveToNext()){
                MusicBean musicBean=new MusicBean();
                musicBean.Song=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                musicBean.Singer=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                musicBean.Path=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                musicBean.Duration=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                musicBean.Size=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                if(musicBean.Size>1000*800){
                    if(musicBean.Song.contains("-")){
                        String[] str=musicBean.Song.split("-");
                        musicBean.Singer=str[0];
                        musicBean.Song=str[1];
                    }
                    list.add(musicBean);
                }
            }
            cursor.close();
        }
        return list;
    }
    public static String formatTime(int time){
        if (time/1000%60<10){
            return time/1000/60+":0"+time/1000%60;
        }else {
            return time/1000/60+":"+time/1000%60;
        }
    }
}