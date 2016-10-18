package com.leedabin.android.medialibrary_landlist;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-16.
 */
public class MediaData {
    private static MediaData instance = null;
    private ArrayList<MusicData> datas;

    private MediaData() {

    }

    public synchronized static MediaData getInstance(Context context) {
        if (instance == null) {
            instance = new MediaData();
            instance.setMusicInfo(context);
        }
        return instance;
    }

    private void setMusicInfo(Context context) {
        datas  =new ArrayList<>();

        // 미디어 스토어에서 가져올 컬럼명 세팅
        String projections[] = {
                MediaStore.Audio.Media._ID,       // 노래아이디
                MediaStore.Audio.Media.ALBUM_ID,  // 앨범아이디
                MediaStore.Audio.Media.TITLE,     // 제목
                MediaStore.Audio.Media.ARTIST     // 가수
        };

        //getContentResolver().query(주소, 검색해올컬럼명들, 조건절, 조건절에매핑되는값, 정렬);
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null, null);
        /*
        - uri        : content://스키마 형태로 정해져 있는 곳의 데이터를 가져온다
        - projection : 가져올 컬럼 이름들의 배열. null 을 입력하면 모든값을 가져온다
        - selection : 조건절(where)에 해당하는 내용
        - selectionArgs : 조건절이 preparedstatement 형태일 때 ? 에 매핑되는 값의 배열
        - sort order    : 정렬 조건
         */

        if (cursor != null) {
            while (cursor.moveToNext()) {
                MusicData data = new MusicData();
                // 데이터에 가수이름을 입력
                // 1. 가수 이름 컬럼의 순서(index)를 가져온다
                int idx = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                // 2. 해당 index를 가진 컬럼의 실제값을 가져온다
                data.singer = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                data.title = cursor.getString(idx);

                Long album_id_idx = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

                Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, album_id_idx);
                try {

                    ParcelFileDescriptor fd = context.getContentResolver().openFileDescriptor(sAlbumArtUri, "r");
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor(), null, null);
                    data.album_id = Bitmap.createScaledBitmap(bitmap, 500, 500, true);

                    idx = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                    data.music_id = cursor.getString(idx);
                } catch (Exception e) {

                }
                datas.add(data);
            }
        }
        cursor.close();
    }

    public ArrayList<MusicData> getDatas(){
        return this.datas;
    }
}
