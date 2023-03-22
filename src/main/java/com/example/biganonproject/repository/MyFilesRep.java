package com.example.biganonproject.repository;

import com.example.biganonproject.model.MyFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MyFilesRep extends JpaRepository<MyFiles, String> {

    ArrayList<MyFiles> findMyFilesByType(String type);

}
