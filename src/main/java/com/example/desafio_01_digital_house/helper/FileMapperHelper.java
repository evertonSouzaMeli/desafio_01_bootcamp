package com.example.desafio_01_digital_house.helper;

import com.example.desafio_01_digital_house.entity.Produto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public abstract class FileMapperHelper {

    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Getter
    private static final String PATH_PRODUTO = "src/main/java/payload.json";

    public static List<Produto> getFileProducts() {
        try {
            File file = new File(PATH_PRODUTO);
            FileInputStream fileInputStream = new FileInputStream(file);
            return Arrays.asList(objectMapper.readValue(fileInputStream, Produto[].class));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
