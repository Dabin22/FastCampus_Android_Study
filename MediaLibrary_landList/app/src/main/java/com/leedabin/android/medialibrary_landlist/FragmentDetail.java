package com.leedabin.android.medialibrary_landlist;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentDetail extends Fragment {



    private OnListFragmentInteractionListener mListener;
    private ArrayList<MusicData> datas;
    private static ViewPager pager;
    private MusicRecyclerAdapter adapter;
    private static FragmentDetail fragment = null;



    public FragmentDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetail newInstance() {
        if(fragment == null)
            fragment= new FragmentDetail();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_detail, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);

        MediaData mediaData = MediaData.getInstance(getContext());
        datas = mediaData.getDatas();
        adapter = new MusicRecyclerAdapter(inflater);

        pager.setAdapter(adapter);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void setPagerCurrentItem(int position)
    {
        Log.i("tag","pager = "+ pager);
        pager.setCurrentItem(position);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    class MusicRecyclerAdapter extends PagerAdapter {
        LayoutInflater inflater;
        public MusicRecyclerAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.pager_item, null);
            TextView title = (TextView) view.findViewById(R.id.title_tv_detail);
            TextView signer = (TextView) view.findViewById(R.id.singer_tv_detail);
            ImageView image = (ImageView) view.findViewById(R.id.music_img_detail);
            MusicData data = datas.get(position);
            title.setText(data.title);
            signer.setText(data.singer);
            image.setImageBitmap(data.album_id);
            // ViewPager를 만든 View에 추가
            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }


}
