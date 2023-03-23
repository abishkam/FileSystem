package com.example.biganonproject.uploadingfiles.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@Component
public class StorageProperties {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Folder location for storing files
     */
    private String location = "D:\\ex";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        logger.info("New directory was set: {}", location);
        this.location = location;
    }

}