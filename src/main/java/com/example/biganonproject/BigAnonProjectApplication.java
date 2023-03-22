package com.example.biganonproject;

import com.example.biganonproject.filesystem.DirTree;
import com.example.biganonproject.model.MyFiles;
import com.example.biganonproject.repository.MyFilesRep;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigAnonProjectApplication implements CommandLineRunner {
    @Autowired
    DirTree dirTree;

    @Autowired
    private MyFilesRep myFilesRep;

    public static void main(String[] args) {
        SpringApplication.run(BigAnonProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dirTree.findFolder();
    }
}
