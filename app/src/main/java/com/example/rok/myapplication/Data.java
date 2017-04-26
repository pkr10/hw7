package com.example.rok.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.StringBuilderPrinter;
import android.view.View;

/**
 * Created by rok on 2017. 4. 6..
 */

public class Data implements Parcelable{
    String name;
    String number;
    String adrress;
    String date;
    String category;
    String menu1;
    String menu2;
    String menu3;
    int count;
    View v;






    public Data(String name, String number, String adrress, String date, String category, String menu1, String menu2, String menu3,int count){
        this.name = name;
        this.number = number;
        this.adrress = adrress;
        this.date = date;
        this.category = category;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.count = count;
    }

    private Data(Parcel in) {
        name = in.readString();
        number = in.readString();
        adrress = in.readString();
        date = in.readString();
        category = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        count = in.readInt();
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(adrress);
        dest.writeString(date);
        dest.writeString(category);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeInt(count);



    }
    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }
    public void setCount(int n){
        this.count = n;
    }
}
