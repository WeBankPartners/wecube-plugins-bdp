package com.webank.wecube.plugins.bdp.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static String toJsonString(Object object) {
        if (object == null) {
            return "";
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return String.valueOf(object);
        }
    }

    public static String toBase64String(String jsonString) {
        return Base64Utils.encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));
    }

    public static String fromBase64String(String base64String) {
        return Arrays.toString(Base64Utils.decodeFromString(base64String));
    }

    public static <T> List<T> toList(String jsonContent, Class<T> clzz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clzz);
        return (List<T>) mapper.readValue(jsonContent.getBytes(Charset.forName("UTF-8")), javaType);
    }

    public static <T> T toObject(String jsonContent, Class<T> clzz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructType(clzz);
        return mapper.readValue(jsonContent.getBytes(Charset.forName("UTF-8")), javaType);
    }

    public static <T> T toObject(Object mapContent, Class<T> clzz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mapContent, clzz);
    }
}