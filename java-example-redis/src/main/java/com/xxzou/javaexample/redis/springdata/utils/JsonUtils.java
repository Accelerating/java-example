package com.xxzou.javaexample.redis.springdata.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object obj){
        String json = null;
        try{
            json = MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> toValueType){
        T obj = null;
        try{
            obj = MAPPER.readValue(json, toValueType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> List<T> toList(String json, Class<T> toValueType){
        List<T> list = null;
        try{
            CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, toValueType);
            list = MAPPER.readValue(json, collectionType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
