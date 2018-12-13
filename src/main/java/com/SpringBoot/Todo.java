package com.SpringBoot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    @NotNull(message = "Check your Name!!")
    @Size(min=3,max = 10,message ="Min:3 and Max:10" )
    private String name;

    @NotNull(message = "Check your Age!!")
    private String age;

    private String userId;

    public Todo() {
    }

    public Todo(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
