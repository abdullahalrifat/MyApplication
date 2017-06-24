package com.example.abdullah.traveltourism.ServerData;

import android.util.Log;

import com.example.abdullah.traveltourism.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abdullah on 6/23/17.
 */

public class GetDivisions
{
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    private static ArrayList<HashMap<String, String>> productsList=new ArrayList<>();







    public static String php = "get_Divisions";
    // url to get all products list
    private static String url_all_products = "http://theicthub.com/Travel/" + php + ".php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "Divisions";
    private static final String TAG_PID = "Id";
    private static final String TAG_NAME = "Name";
    public ArrayList getList() {

        return productsList;
    }
    // products JSONArray
    JSONArray products = null;
    public void load()
    {


        productsList.clear();
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Products: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                products = json.getJSONArray(TAG_PRODUCTS);

                // looping through All Products
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_PID);
                    String name = c.getString(TAG_NAME);


                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_PID, id);
                    map.put(TAG_NAME, name);


                    // adding HashList to ArrayList
                    productsList.add(map);
                }
            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
