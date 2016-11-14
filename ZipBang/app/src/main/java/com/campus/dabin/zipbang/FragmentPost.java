package com.campus.dabin.zipbang;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;


public class FragmentPost extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int REQ_CODE_ADDRESS = 30;
    LatLng latLng;
    String placeId = "";
    String placeAddress = "";
    Button btn_post;
    Button btn_search;
    EditText et_room_name;
    EditText et_deposit;
    EditText et_month_fee;
    EditText et_room_count;
    EditText et_room_size;
    Button btn_gallery;
    Button btn_camera;
    ImageView iv_room;

    TextView tv_address;


    float zomm_level = 15.0f;

    Place place;

    private final static int REQ_CODE_CAMERA = 10;
    private final static int REQ_CODE_GALLERY = 20;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPost() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPost.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPost newInstance(String param1, String param2) {
        FragmentPost fragment = new FragmentPost();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_post, container, false);

        btn_post = (Button) view.findViewById(R.id.btn_register);

        // 방제목
        // 보증금 월세
        // 사진 1개 필수
        // 위치 선택
        // 방 갯수
        // 평수
        et_room_name = (EditText) view.findViewById(R.id.et_room_name);
        et_deposit = (EditText) view.findViewById(R.id.et_deposit);
        et_month_fee = (EditText) view.findViewById(R.id.et_month_fee);
        et_room_count = (EditText) view.findViewById(R.id.et_room_count);
        et_room_size = (EditText) view.findViewById(R.id.et_room_size);
        iv_room = (ImageView) view.findViewById(R.id.iv_room_img);
        btn_camera = (Button) view.findViewById(R.id.btn_camera);
        btn_gallery = (Button) view.findViewById(R.id.btn_gallery);

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate()) {
                    //firebase register
                    Toast.makeText(getContext(), "사진과 다른 것들을 다 입력했네요", Toast.LENGTH_SHORT).show();
                    mListener.selectedLocal(latLng,placeAddress);
                } else {
                    Toast.makeText(getContext(), "사진과 다른 것들을 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CODE_CAMERA);

            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK
                        , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*"); // 이미지만 필터링
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_CODE_GALLERY);

            }
        });

        btn_search = (Button) view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceSearchActivity.class);
                startActivityForResult(intent, REQ_CODE_ADDRESS);
            }
        });
        tv_address = (TextView)view.findViewById(R.id.tv_address);

        return view;
    }

    private boolean checkValidate() {
        boolean result = false;
        if (et_room_count.getText().toString().length() >= 1 && et_deposit.getText().toString().length() >= 1
                && et_deposit.getText().toString().length() >= 1 && et_month_fee.getText().toString().length() >= 1
                && et_room_name.getText().toString().length() >= 1 && et_room_size.getText().toString().length() >= 1
                && iv_room.getTag() != null && place != null)
            result = true;


        return result;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Log.e("tag",requestCode +", " + resultCode);
            if (null != data.getData()) {
                Bitmap image = null;
                Uri uri = data.getData();
                String imgPath = getRealPathFromURI(uri);
                switch (requestCode) {
                    case REQ_CODE_GALLERY:
                        image = getThumbnailImage(imgPath);
                        iv_room.setTag(REQ_CODE_GALLERY);
                        iv_room.setImageBitmap(image);
                        break;
                    case REQ_CODE_CAMERA:
                        Log.i("image", "imgPath=" + imgPath);
                        image = getThumbnailImage(imgPath);
                        iv_room.setTag(REQ_CODE_CAMERA);
                        iv_room.setImageBitmap(image);
                        //imageView.setImageURI(uri);
                        break;

                }



            }else{
                if (resultCode == REQ_CODE_ADDRESS) {
                    place = data.getExtras().getParcelable("place");

                    latLng = place.getLatLng();

                    placeId = place.getId();
                    placeAddress = place.getAddress().toString();
                    Log.e("tag1", latLng.toString() + ", " + placeId + ", " + placeAddress);
                    tv_address.setText(placeAddress);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getThumbnailImage(String imgPath) {
        Bitmap image = null;
        try {
            // 이미지 축소
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3; // 1/3로 축소

            image = BitmapFactory.decodeFile(imgPath, options);

            Log.i("image", "instance=" + image);
            int exifDegree = exifOrientationToDegrees(imgPath);
            Log.i("image", "exifDegree=" + exifDegree);
            image = rotateImage(image, exifDegree);

        } catch (Exception e) {
            Log.e("Thumbnail Error", e.toString());
            e.printStackTrace();
        }
        return image;
    }


    /**
     * EXIF정보를 회전각도로 변환하는 메서드
     *
     * @param imgPath 이미지 경로
     * @return 실제 각도
     */
    public int exifOrientationToDegrees(String imgPath) {
        int degree = 0;
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(imgPath);
        } catch (IOException e) {
            Log.e("exifOrientation", "cannot read exif");
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                // We only recognize a subset of orientation tag values.
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    // 이미지 회전 함수
    public Bitmap rotateImage(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    /**
     * 이미지를 회전시킵니다.
     *
     * @param bitmap  비트맵 이미지
     * @param degrees 회전 각도
     * @return 회전된 이미지
     */
    public Bitmap rotate(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != converted) {
                    bitmap.recycle();
                    bitmap = converted;
                }
            } catch (OutOfMemoryError ex) { // 메모리 부족
                ex.printStackTrace();
            }
        }
        return bitmap;
    }

    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            e.printStackTrace();
            return contentUri.getPath();
        }
    }


}
