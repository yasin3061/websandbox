package com.yasinbee.trello.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;

@UtilityClass
public class JsonUtils {
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode stringToJsonNode(String jsonString) {
        JsonNode actualObj = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            actualObj = mapper.readTree(jsonString);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }

        return actualObj;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
