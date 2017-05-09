package com.example.administrator.musicplayer3;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;


/**
 * 音乐工具类,
 */
public class FindSongs {
    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public static List<MusicBean> getMusicData(Context context) {
        List<MusicBean> list = new ArrayList<MusicBean>();
        // 媒体库查询语句（写一个工具类MusicUtils）
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MusicBean song = new MusicBean();
                song.Song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                song.Singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                song.Path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                song.Duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.Size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                if (song.Size > 1000 * 800) {
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (song.Song.contains("-")) {
                        String[] str = song.Song.split("-");
                        song.Singer = str[0];
                        song.Song = str[1];
                    }
                    list.add(song);
                }
            }
            // 释放资源
            cursor.close();
        }

        return list;
    }
    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }
}
