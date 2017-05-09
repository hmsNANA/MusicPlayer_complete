package com.example.administrator.musicplayer3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<MusicBean> list;

    public MyAdapter(SongListFrag SongListfrag, List<MusicBean> list) {
        this.context = SongListfrag.getActivity();
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.music_item, null);
            //实例化对象
            holder.song = (TextView) view.findViewById(R.id.item_Song);
            holder.singer = (TextView) view.findViewById(R.id.item_Singer);
            holder.duration = (TextView) view.findViewById(R.id.item_Duration);
            holder.position = (TextView) view.findViewById(R.id.item_Position);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //给控件赋值
        holder.song.setText(list.get(i).Song.toString());
        holder.singer.setText(list.get(i).Singer.toString());
        //时间需要转换一下
        int duration = list.get(i).Duration;
        String time = FindSongs.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(i+1+"");

        return view;
    }
    class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号

    }

}