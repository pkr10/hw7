package com.example.rok.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rok on 2017. 4. 13..
 */

public class DataAdapter extends BaseAdapter{
    ArrayList<Data> datalist;
    Context c;
    ArrayList<Data> arrayList;

    public DataAdapter(ArrayList<Data> datalist,Context c,ArrayList<Data> arrayList){
        this.datalist = datalist;
        this.c = c;
        this.arrayList = arrayList;

    }


    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = LayoutInflater.from(c).inflate(R.layout.listviewlayout,null);
        }


        TextView t1 = (TextView)convertView.findViewById(R.id.textView);
        TextView t2 = (TextView)convertView.findViewById(R.id.textView2);
        ImageView i1 = (ImageView)convertView.findViewById(R.id.imageView);
        CheckBox c1 = (CheckBox)convertView.findViewById(R.id.checkBox1);


        Data one = datalist.get(position);

        Log.d("count", String.valueOf(one.count));


        Log.d("데이터",datalist.toString());

        Log.d("어레이체크1",arrayList.toString());

        if(one.category.matches("chicken")){
            i1.setImageResource(R.drawable.chicken);
        }
        else if(one.category.matches("hamburger")){
            i1.setImageResource(R.drawable.hamburger);
        }
        else{
            i1.setImageResource(R.drawable.pizza);
        }
        if(one.count ==1){
            c1.setVisibility(View.VISIBLE);
        }
        else {
            c1.setChecked(false);
            c1.setVisibility(View.INVISIBLE);
        }
        t1.setText(one.name);
        t2.setText(one.number);
        one.setV(convertView);

        return convertView;    }

    public void filter(String charText){
        Log.d("문자열",charText);
        Log.d("데이터 체크1",datalist.toString());

        datalist.clear();
        Log.d("데이터클리어",datalist.toString());
        Log.d("어레이",arrayList.toString());
        if(charText.length()==0){
            datalist.addAll(arrayList);
            Log.d("데이터 체크2",datalist.toString());

            Log.d("박경록",datalist.toString());
        }
        else{
            for(Data db : arrayList){
                if(db.name.contains(charText)){
                    datalist.add(db);
                    Log.d("데이터 체크3",datalist.toString());
                }
            }
        }
        notifyDataSetChanged();

    }
}
