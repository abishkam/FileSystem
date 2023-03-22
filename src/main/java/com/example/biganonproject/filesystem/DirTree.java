package com.example.biganonproject.filesystem;

import com.example.biganonproject.model.MyFiles;
import com.example.biganonproject.repository.MyFilesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class DirTree
{


    MyFilesRep myFilesRep;

    public DirTree(MyFilesRep rep){
        this.myFilesRep = rep;
    }

    public void findFolder()
    {
        list("D:\\ex");
    }

    public void list(String szDir)
    {
        File f = new File(szDir);

        String[] sDirList = f.list();

        int i;
        for(i = 0; i < sDirList.length; i++)
        {
            System.out.println(szDir +
                    File.separator + sDirList[i]);
            File f1 = new File(szDir +
                    File.separator + sDirList[i]);

            if(f1.isFile()){
                if(sDirList[i].contains("."))
                    myFilesRep.save(new MyFiles(sDirList[i], sDirList[i].substring(sDirList[i].indexOf(".")), f1.length(), f1.getAbsolutePath()));
                else myFilesRep.save(new MyFiles(sDirList[i], "file", f1.length(), f1.getAbsolutePath()));
            }
            else
            {
                list(szDir +
                        File.separator + sDirList[i]);
            }
        }

    }

}

