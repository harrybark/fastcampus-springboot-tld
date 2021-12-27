package com.example;

import com.example.tldspringboot.dto.MapperCar;
import com.example.tldspringboot.dto.MapperUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("---main----");

        ObjectMapper objectMapper = new ObjectMapper();

        MapperUser user = new MapperUser();
        user.setName("홍길동");
        user.setAge(10);

        MapperCar car1 = new MapperCar();
        car1.setCarName("A6");
        car1.setCarNumber("11가 1111");
        car1.setType("SEDAN");

        MapperCar car2 = new MapperCar();
        car2.setCarName("X6");
        car2.setCarNumber("11나 1111");
        car2.setType("SUV");

        List<MapperCar> carList = Arrays.asList(car1, car2);
        user.setCarList(carList);

        String json = objectMapper.writeValueAsString(user);

        //System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age  = jsonNode.get("age").asInt();

        JsonNode cars = jsonNode.get("carList");
        ArrayNode arrayNode = (ArrayNode)cars;

        List<MapperCar> _carList = objectMapper.convertValue(arrayNode, new TypeReference<List<MapperCar>>() {});

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "harry");
        objectNode.put("age", "20");

        System.out.println(objectNode.toPrettyString());
    }
}
