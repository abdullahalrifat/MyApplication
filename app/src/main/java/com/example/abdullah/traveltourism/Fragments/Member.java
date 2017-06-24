package com.example.abdullah.traveltourism.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.abdullah.traveltourism.AlertDialogueBuilder;
import com.example.abdullah.traveltourism.R;
import com.example.abdullah.traveltourism.StaticDatas.MembersInfo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Member.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Member#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Member extends Fragment implements View.OnClickListener {
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
    Button add;
    private AdView mAdView;
    public Member() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Member.
     */
    // TODO: Rename and change types and number of parameters
    public static Member newInstance(String param1, String param2) {
        Member fragment = new Member();
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
        RootView= inflater.inflate(R.layout.fragment_members, container, false);
        add = (Button) RootView.findViewById(R.id.add);
        add.setOnClickListener(this);
        mAdView = (AdView) RootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        loadList();

        return RootView;
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
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

    @Override
    public void onClick(View v) {
        AlertDialogueBuilder al=new AlertDialogueBuilder();
        String show="Hello";
        al.setTitle("Details...");
        al.DialogueBox(getActivity());

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
    private static final String TAG_NAME = "Name";
    private static final String TAG_PHONE = "Phone";
    private static final String TAG_DEPOSITE = "DepositeMoney";
    ArrayList< HashMap<String, String>> det=new ArrayList<>();
    public  static ArrayAdapter<String> adapter;
    //public  String[] v;
    public static ArrayList v=new ArrayList();
    public static void reloadV()
    {
        v.clear();
        MembersInfo mem=new MembersInfo();
        ArrayList< HashMap<String, String>> det=new ArrayList<>();
        det=mem.getdetails();

        //v=new String[det.size()];
        for(int i=0;i<det.size();i++)
        {
            HashMap<String, String> tmpData = (HashMap<String,String>) det.get(i);
            v.add(tmpData.get(TAG_NAME));
            //v[i]=tmpData.get(TAG_NAME);
        }

    }
    public void loadList()
    {

        // Get ListView object from xml
        listView = (ListView) RootView.findViewById(R.id.list);
       // MembersInfo mem=new MembersInfo("Rifat","01671080275",2500.0);
        MembersInfo mem=new MembersInfo();
        det=mem.getdetails();
        v.clear();
        //v=new String[det.size()];
        for(int i=0;i<det.size();i++)
        {
            HashMap<String, String> tmpData = (HashMap<String,String>) det.get(i);
            v.add(tmpData.get(TAG_NAME));
            //v[i]=tmpData.get(TAG_NAME);
        }

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,v);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                HashMap<String, String> tmpData = (HashMap<String,String>) det.get(pos);

                AlertDialogueBuilder al=new AlertDialogueBuilder();
                al.DialogueBoxMembersUpdate(getActivity(),tmpData.get(TAG_NAME),tmpData.get(TAG_PHONE),tmpData.get(TAG_DEPOSITE));
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                HashMap<String, String> tmpData = (HashMap<String,String>) det.get(position);


                AlertDialogueBuilder al=new AlertDialogueBuilder();
                String show="Phone : "+tmpData.get(TAG_PHONE)+"\n"+"Deposited Money : "+tmpData.get(TAG_DEPOSITE);
                al.setTitle("Details...");
                al.DialogueBox(getActivity(),show);
            }
        });
    }
}
