package com.oshovskii.parsing.json;

import com.oshovskii.parsing.Parser;
import com.oshovskii.parsing.model.People;
import com.oshovskii.parsing.model.Root;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleParser extends Parser {

    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";

    @Override
    public Root parse() {
        Root root = new Root();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/data/test.json")){
           JSONObject rootJsonObject = (JSONObject) parser.parse(reader);
           String name = (String) rootJsonObject.get(TAG_NAME_MAIN);
           JSONArray peopleJsonArray = (JSONArray) rootJsonObject.get(TAG_PEOPLE);
           List<People> peopleList = new ArrayList<>();
           for (Object it : peopleJsonArray) {
               JSONObject peopleJsonObject = (JSONObject) it;
               String namePeople = (String) peopleJsonObject.get(TAG_NAME);
               long agePeople = (Long) peopleJsonObject.get(TAG_AGE);
                People people = new People(namePeople, (int) agePeople);
                peopleList.add(people);
           }
           root.setName(name);
           root.setPeople(peopleJsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }
}
