package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JSonUtils";

    public static Sandwich parseSandwichJson(String json) {


        try {
            JSONObject myJson        = new JSONObject(json);
            String mainName          = getJsonString(getJsonObject(myJson,"name"),"mainName");
            List<String> alsoKnownAs = getJsonArrayList(getJsonObject(myJson,"name"),"alsoKnownAs");
            String placeOfOrigin     = getJsonString(myJson   ,"placeOfOrigin");
            String description       = getJsonString(myJson   ,"description");
            String image             = getJsonString(myJson   ,"image");
            List<String> ingredients = getJsonArrayList(myJson,"ingredients");

            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

        }
        catch (JSONException e) {
            Log.d(TAG,"Couldn't parse Json Object"+json);
            e.printStackTrace();
        }

        return null;
    }

    public static String getJsonString(JSONObject pJson,String propertyName) throws JSONException  {
     return pJson.getString(propertyName);
    }

    public static JSONObject getJsonObject(JSONObject pJson,String propertyName) throws JSONException  {
        return pJson.getJSONObject(propertyName);
    }

    public static List<String> getJsonArrayList(JSONObject pJson,String propertyName) throws JSONException  {
        List<String> aList = new ArrayList<>();
        JSONArray array = pJson.getJSONArray(propertyName);
        for (int i=0;i<array.length();i++) {aList.add(array.getString(i));};
        return aList;
    }

}
