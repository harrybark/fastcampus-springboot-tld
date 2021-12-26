package com.example.tldspringboot.dto;

public class User {

    private String user;
    private int age;

    public User() {
    }

    public User(String user, int age) {
        this.user = user;
        this.age = age;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User defaultUser() {
        return new User("default", 0);
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", age=" + age +
                '}';
    }
}
