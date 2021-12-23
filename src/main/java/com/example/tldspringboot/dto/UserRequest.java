package com.example.tldspringboot.dto;

public class UserRequest {

    private String name;
    private String mail;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name : " + this.getName() + ", email : " + this.getMail() + " , age : " + this.getAge() ;
    }
}
