package com.leedabin.android.medialibrary_landlist;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by Dabin on 2016-10-07.
 */
public class Data {
    ArrayList<Observer> observers = new ArrayList<>();
    ArrayList<MusicData> datas = new ArrayList<>();



    public  interface  Observer{
        public void update();
    }



}
class MusicData{
    String music_id;
    Bitmap album_id;
    String title;
    String singer;
}
