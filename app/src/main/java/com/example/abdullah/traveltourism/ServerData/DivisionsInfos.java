package com.example.abdullah.traveltourism.ServerData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by abdullah on 6/23/17.
 */

public class DivisionsInfos
{
    private static final String TAG_NAME = "Name";
    private static HashMap<String, ArrayList<HashMap<String,String>>> Places=new HashMap<>();
    ArrayList<HashMap<String, String>> values=new ArrayList<>();
    ArrayList vPlaces=new ArrayList();
    public void load()
    {
        GetDivisions get=new GetDivisions();
        GetAllDivisionsInfos g=new GetAllDivisionsInfos();
        values=get.getList();
        for(int i=0;i<values.size();i++) {

            HashMap<String, String> tmpData = (HashMap<String,String>) values.get(i);
            vPlaces.add(tmpData.get(TAG_NAME));

        }
        for(int i=0;i<vPlaces.size();i++)
        {
            g.setTable((String)vPlaces.get(i));
            g.load();
            ArrayList<HashMap<String, String>> prod=new ArrayList<>(g.getList());

            Places.put((String)vPlaces.get(i),prod);
            ArrayList<HashMap<String, String>> val;
            val=Places.get("Dhaka");
            val.get(0).get("Name");

        }

    }
    public static ArrayList<HashMap<String,String>> getPlaces(String division)
    {

        ArrayList<HashMap<String, String>> val=Places.get(division);
           return val;
    }
}
