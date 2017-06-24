package com.example.abdullah.traveltourism.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abdullah.traveltourism.R;
import com.example.abdullah.traveltourism.ServerData.DivisionsInfos;
import com.example.abdullah.traveltourism.ServerData.GetDivisions;
import com.example.abdullah.traveltourism.StaticDatas.PlaceDetails;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlacesLists.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlacesLists#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacesLists extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    View RootView;
    ListView listView ;

    private static final String TAG_PID = "Id";
    private static final String TAG_NAME = "Name";
    private static final String TAG_URL = "Image";
    private static final String TAG_DESCRIPTION = "Description";
    public PlacesLists() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlacesLists.
     */
    // TODO: Rename and change types and number of parameters
    public static PlacesLists newInstance(String param1, String param2) {
        PlacesLists fragment = new PlacesLists();
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
        RootView=inflater.inflate(R.layout.fragment_places_lists, container, false);
        getList();
        // Inflate the layout for this fragment
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
    ArrayList<HashMap<String, String>> values=new ArrayList<>();
    public static ArrayList vPlaces=new ArrayList();


    public void getList()
    {
       DivisionsInfos div=new DivisionsInfos();


        // Get ListView object from xml
        listView = (android.widget.ListView) RootView.findViewById(R.id.listPlace);
        values.clear();
        vPlaces.clear();
        String selectedDivision=Divisions.getselectedValue();
        values= div.getPlaces(selectedDivision);

        for(int i=0;i<values.size();i++) {

            HashMap<String, String> tmpData = (HashMap<String,String>) values.get(i);
            vPlaces.add(tmpData.get(TAG_NAME));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,vPlaces);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                HashMap<String, String> tmpData = (HashMap<String,String>) values.get(position);
                PlaceDetails place=new PlaceDetails(Integer.parseInt(tmpData.get(TAG_PID)),tmpData.get(TAG_NAME),tmpData.get(TAG_URL),tmpData.get(TAG_DESCRIPTION));
                Fragment fragment=new MainFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment=new Place();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                /*
                    AlertDialogueBuilder al=new AlertDialogueBuilder();
                    String show="Phone : "+tmpData.get(TAG_PHONE)+"\n"+"Level : "+tmpData.get(TAG_LEVEL);
                    al.setTitle("Details...");
                    al.DialogueBox(getActivity(),show);
                */


            }
        });
    }

}
