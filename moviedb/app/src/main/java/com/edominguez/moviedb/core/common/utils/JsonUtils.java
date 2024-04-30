package com.edominguez.moviedb.core.common.utils;

import android.net.Uri;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static Object getFromJson(JSONObject item, String name){
        try {
            if(!item.isNull(name))
                return item.get(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringFromJson(JSONObject item, String name){
        try {
            if(!item.isNull(name))
                return item.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIntFromJson(JSONObject item, String name){
        try {
            if(!item.isNull(name))
                return item.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean getBooleanFromJson(JSONObject item, String name){
        try {
            if(!item.isNull(name))
                return item.getBoolean(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object getFromJsonArray(JSONArray item, int position){
        try {
            if(!item.isNull(position))
                return item.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJObjectFromJsonArray(JSONArray item, int position){
        try {
            if(!item.isNull(position))
                return (JSONObject) item.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject parseJson(String json){
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray parseJsonArray(String json){
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray removeFromJsonArray(JSONArray json, String value){
        JSONArray newJson = new JSONArray();
        String current;
        for (int i = json.length()-1; i >= 0 ; i--) {
            current = (String) getFromJsonArray(json,i);
            if( !value.equals(current) ){
                newJson.put(current);
            }
        }
        return newJson;
    }

    public static JSONArray removeFromJsonArray(JSONArray json, int position){
        JSONArray newJson = new JSONArray();
        String current;
        for (int i = json.length()-1; i >= 0 ; i--) {
            current = (String) getFromJsonArray(json,i);
            if( position!=i ){
                newJson.put(current);
            }
        }
        return newJson;
    }

    public static void addToJson(JSONObject json, String key, String value){
        try {
            json.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addToJson(JSONObject json, String key, boolean value){
        try {
            json.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addToJson(JSONObject json, String key, Uri value){
        try {
            json.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addToJson(JSONObject json, String key, int value){
        try {
            json.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addToJson(JSONObject json, String key, Object value){
        try {
            json.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
