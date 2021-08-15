package com.ceaa.ceaaapplication.entities;

import com.ceaa.ceaaapplication.hooks.HashGeneratorHook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(HashGeneratorHook.class)
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String author;
    @JsonIgnore
    private Date createdAt;
    @Column(length = 40000)
    private String body;
    private String category;
    private String language;
    private String tags;
}
