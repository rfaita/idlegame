package com.idle.game.server.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Serializable o) throws Exception {
        return OBJECT_MAPPER.writeValueAsString(o);
    }

    public static Serializable toObject(String o, Class clazz) throws Exception {
        return (Serializable) OBJECT_MAPPER.readValue(o, clazz);
    }

}
