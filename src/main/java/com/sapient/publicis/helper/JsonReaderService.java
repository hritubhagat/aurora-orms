package com.sapient.publicis.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderService {
    
private static Logger LOG = LoggerFactory.getLogger(JsonReaderService.class);
private static ObjectMapper mapper;
    
    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new MultiDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'|yyyy-MM-dd|yyyyMMdd-HH:mm:ss.sss"));
    }
    
    /**
     * This method reads json files from resources folder
     * 
     * @param inputStream
     *            : Input stream to read resource
     * @param clazz
     *            : Class type
     * @return : Type parameter of method return type
     */
    public static <T> T mockResponse(InputStream inputStream, Class<T> clazz) {
        T t = null;
        long currentTime = System.currentTimeMillis();
        try {
            t = mapper.readValue(inputStream, clazz);
        } catch (FileNotFoundException e) {
            LOG.info("Mock JSON response not found....", e);
        } catch (IOException e) {
            LOG.info("Could not read JSON response from file....", e);
        }
        LOG.info("Time taken to read mock json file::{} ms.", System.currentTimeMillis() - currentTime);
        return t;
    }
}

