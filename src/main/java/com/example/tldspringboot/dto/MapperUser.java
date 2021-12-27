package com.example.tldspringboot.dto;

import java.util.List;

public class MapperUser {

    private String name;
    private int age;

    private List<MapperCar> carList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<MapperCar> getCarList() {
        return carList;
    }

    public void setCarList(List<MapperCar> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "MapperUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carList +
                '}';
    }
}
