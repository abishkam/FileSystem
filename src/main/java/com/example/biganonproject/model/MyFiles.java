package com.example.biganonproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(indexes = {
        @Index(name = "type_index", columnList = "type"),
})
public class MyFiles {

    @Id
    private String name;
    private String type;
    private long size;
    private String folder;

    public MyFiles() {
    }

    public MyFiles(String type, long size, String folder) {
        this.type = type;
        this.size = size;
        this.folder = folder;
    }

    public MyFiles(String name, String type, long size, String folder) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof MyFiles)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFiles that = (MyFiles) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) && size == that.size && Objects.equals(folder, that.folder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, size, folder);
    }

    @Override
    public String toString() {
        return "MyFiles{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", folder='" + folder + '\'' +
                '}';
    }

}
