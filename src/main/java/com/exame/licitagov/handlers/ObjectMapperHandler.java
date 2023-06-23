package com.exame.licitagov.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ObjectMapperHandler {

    public static <T> T parseResponseBodyToObject(ResponseBody json, Class<T> classToParse) throws IOException {
        return new ObjectMapper().readValue(json.string(), classToParse);
    }
}
