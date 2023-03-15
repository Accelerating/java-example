package com.xxzou.javaexample.elasticsearch.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public <T> T toObject(String json, Class<T> type){
        try{
            return mapper.readValue(json, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
