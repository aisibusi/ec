package com.lh.ec.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String toString(Object obj) throws RuntimeException {
        if(obj == null) return null;
        if(String.class.isInstance(obj))
            return obj.toString();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Unexpected issues in JsonUtils.toString, " + obj, e);
            throw new RuntimeException("Fail to parse Json to String");
        }
    }

    public static <T> T toBean(String json, Class<T> tClass) throws RuntimeException {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("Unexpected issues in JsonUtils.toString, " + json, e);
            throw new RuntimeException("Fail to parse Json to Bean");
        }
    }

    public static <E> List<E> toList(String json, Class<E> eClass) throws RuntimeException {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("Unexpected issues in JsonUtils.toList, " + json, e);
            throw new RuntimeException("Fail to parse Json to List");
        }

    }
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) throws RuntimeException {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("Unexpected issues in JsonUtils.toMap, " + json, e);
            throw new RuntimeException("Fail to parse Json to Map");
        }
    }

    public static <T> T nativeRead(String json, TypeReference<T> type) throws RuntimeException {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("Unexpected issues in JsonUtils.nativeRead, " + json, e);
            throw new RuntimeException("Fail to parse Json to nativeRead");
        }
    }
}
