package com.example.biganonproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(indexes = {
        @Index(name = "type_index", columnList = "type"),
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyFiles {

    @Id
    private String name;
    private String type;
    private long size;
    private String folder;

    @Override
    public boolean equals(Object o) {
        if(! (o instanceof MyFiles)) return false;
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MyFiles myFiles = (MyFiles) o;
        return name != null && Objects.equals(name, myFiles.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
