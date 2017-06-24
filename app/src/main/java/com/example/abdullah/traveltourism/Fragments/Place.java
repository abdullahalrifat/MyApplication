package com.example.abdullah.traveltourism.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.abdullah.traveltourism.CircleTransform;
import com.example.abdullah.traveltourism.R;
import com.example.abdullah.traveltourism.StaticDatas.PlaceDetails;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Place.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Place#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Place extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View RootView;
    private OnFragmentInteractionListener mListener;

    public Place() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Place.
     */
    // TODO: Rename and change types and number of parameters
    public static Place newInstance(String param1, String param2) {
        Place fragment = new Place();
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
        RootView=inflater.inflate(R.layout.fragment_place, container, false);
        loadPage();
        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void loadPage()
    {
        TextView name=(TextView)RootView.findViewById(R.id.name);
        TextView description=(TextView)RootView.findViewById(R.id.description);
        ImageView placeImage=(ImageView) RootView.findViewById(R.id.placeImage);

        PlaceDetails pl=new PlaceDetails();
        name.setText(pl.getName());
        description.setText(pl.getDescription());

        String img=pl.getImage();
        Log.d("Image Link",img);
/*
        final ProgressBar progressBar=(ProgressBar)RootView.findViewById(R.id.progressBar);
        Glide.with(getContext())
                .load(pl.getImage())
                .asBitmap()
                //.placeholder(R.drawable.default_placeholder)
                .override(1600, 1600) // Can be 2000, 2000
                .into(new BitmapImageViewTarget(placeImage) {
                    @Override
                    public void onResourceReady(Bitmap drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        progressBar.setVisibility(View.GONE);
                    }
                });
*/

        Glide.with(this)
                .load(img)
                .into(placeImage);

/*
        Glide.with(this.getContext()).load(pl.getImage())
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this.getContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(placeImage);
*/


    }
}
