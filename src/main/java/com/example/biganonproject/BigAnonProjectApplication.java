package com.example.biganonproject;

import com.example.biganonproject.filesystem.DirTree;
import com.example.biganonproject.repository.MyFilesRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigAnonProjectApplication implements CommandLineRunner {

    private final DirTree dirTree;
    private final MyFilesRep myFilesRep;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BigAnonProjectApplication(DirTree dirTree, MyFilesRep myFilesRep){
        this.dirTree = dirTree;
        this.myFilesRep = myFilesRep;
    }

    public static void main(String[] args) {
        SpringApplication.run(BigAnonProjectApplication.class, args);
    }

    @Override
    public void run(String... args){
        dirTree.findFolder();
        logger.info("All users -> {}", myFilesRep.findAll());
    }
}
