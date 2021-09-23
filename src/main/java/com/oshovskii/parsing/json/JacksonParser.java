package com.oshovskii.parsing.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oshovskii.parsing.Parser;
import com.oshovskii.parsing.model.Root;

import java.io.IOException;
import java.nio.file.Paths;

public class JacksonParser extends Parser {

    @Override
    public Root parse() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Root root = objectMapper.readValue(Paths.get("src/main/resources/data/test.json").toFile(), Root.class);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
