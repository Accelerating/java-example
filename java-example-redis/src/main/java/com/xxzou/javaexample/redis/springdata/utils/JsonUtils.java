package com.xxzou.javaexample.redis.springdata.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj){
        String json = null;
        try{
            json = mapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> toValueType){
        T obj = null;
        try{
            obj = mapper.readValue(json, toValueType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> List<T> toList(String json, Class<T> toValueType){
        List<T> list = null;
        try{
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, toValueType);
            list = mapper.readValue(json, collectionType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
