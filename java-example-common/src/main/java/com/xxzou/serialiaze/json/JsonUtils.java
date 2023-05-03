package com.xxzou.serialiaze.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final  Logger log = LoggerFactory.getLogger(JsonUtils.class);
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object obj){
        String json = null;
        try{
            json = MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            log.error("json serialize failed", e);
        }
        return json;
    }

    public static byte[] toByteArray(Object obj){
        byte[] bytes = null;
        try{
            bytes = MAPPER.writeValueAsBytes(obj);
        }catch (Exception e){
            log.error("json serialize failed", e);
        }
        return bytes;
    }

    public static <T> T toObject(String json, Class<T> toValueType){
        T obj = null;
        try{
            obj = MAPPER.readValue(json, toValueType);
        }catch (Exception e){
            log.error("json deserialize failed", e);
        }
        return obj;
    }

    public static <T> List<T> toList(String json, Class<T> toValueType){
        List<T> list = null;
        try{
            CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, toValueType);
            list = MAPPER.readValue(json, collectionType);
        }catch (Exception e){
            log.error("json deserialize failed", e);
        }
        return list;
    }

    public static <T> T toObject(byte[] data, Class<T> toValueType){
        T obj = null;
        try{
            obj = MAPPER.readValue(data, toValueType);
        }catch (Exception e){
            log.error("json deserialize failed", e);
        }
        return obj;
    }

    public static <T> List<T> toList(byte[] data, Class<T> toValueType){
        List<T> list = null;
        try{
            CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, toValueType);
            list = MAPPER.readValue(data, collectionType);
        }catch (Exception e){
            log.error("json deserialize failed", e);
        }
        return list;
    }

}
