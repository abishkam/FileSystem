package com.example.biganonproject.filesystem;

import com.example.biganonproject.model.MyFiles;
import com.example.biganonproject.repository.MyFilesRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

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
            File f1 = new File(szDir +
                    File.separator + sDirList[i]);

            if(f1.isFile()){
                if(sDirList[i].contains("."))
                    myFilesRep.save(new MyFiles(sDirList[i], sDirList[i].substring(sDirList[i].indexOf(".")+1), f1.length(), f1.getAbsolutePath()));
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

