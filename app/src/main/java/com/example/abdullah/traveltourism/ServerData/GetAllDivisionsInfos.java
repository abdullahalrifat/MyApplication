package com.example.abdullah.traveltourism.ServerData;

import android.app.ProgressDialog;
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

public class GetAllDivisionsInfos
{
    // Progress Dialog
    private ProgressDialog pDialog;


    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    public static ArrayList<HashMap<String, String>> productsList=new ArrayList<>();

    // url to get all products list
    public static String php = "get_Dhaka";
    // url to get all products list
    private static String url_all_products = "http://theicthub.com/Travel/" + php + ".php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static  String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "Id";
    private static final String TAG_NAME = "Name";

    private static final String TAG_URL = "Image";
    private static final String TAG_DESCRIPTION = "Description";
    // products JSONArray
    JSONArray products = null;
    public void setTable(String table)
    {

            php = "get_"+table;
            url_all_products = "http://theicthub.com/Travel/" + php + ".php";

            TAG_PRODUCTS=table;
    }

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
                    String description = c.getString(TAG_DESCRIPTION);
                    String url=c.getString(TAG_URL);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_PID, id);
                    map.put(TAG_NAME, name);
                    map.put(TAG_DESCRIPTION, description);
                    map.put(TAG_URL, url);

                    // adding HashList to ArrayList
                    productsList.add(map);
                }
            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList getList() {
        return productsList;
    }
}
