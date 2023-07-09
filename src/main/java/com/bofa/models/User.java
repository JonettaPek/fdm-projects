package com.bofa.models;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonGetter;

@Component
public class User {
    private long id;
    private String name;
    private int age;
    public User() {
    }
    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @JsonGetter("identity")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @JsonGetter("name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @JsonGetter("age")
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
