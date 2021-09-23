package com.oshovskii.parsing;

import com.oshovskii.parsing.json.GsonParser;
import com.oshovskii.parsing.json.JacksonParser;
import com.oshovskii.parsing.json.JsonSimpleParser;
import com.oshovskii.parsing.model.Root;
import com.oshovskii.parsing.xml.DOMParser;
import com.oshovskii.parsing.xml.SAXCustomParser;

public class Main {
    public static void main(String[] args) {

        DOMParser domParser = new DOMParser();
        Root root1 = domParser.parse();
        System.out.println("DOM PARSER");
        System.out.println("Root: " + root1);
        System.out.println("=========================================================\n");

        SAXCustomParser saxParser = new SAXCustomParser();
        Root root2 = saxParser.parse();
        System.out.println("SAX PARSER");
        System.out.println("Root: " + root2);
        System.out.println("=========================================================\n");

        JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
        Root root3 = jsonSimpleParser.parse();
        System.out.println("JSON SIMPLE PARSER");
        System.out.println("Root: " + root3);
        System.out.println("=========================================================\n");

        GsonParser gsonParser = new GsonParser();
        Root root4 = gsonParser.parse();
        System.out.println("GSON PARSER");
        System.out.println("Root: " + root4);
        System.out.println("=========================================================\n");

        JacksonParser jacksonParser = new JacksonParser();
        Root root5 = jacksonParser.parse();
        System.out.println("JACKSON PARSER");
        System.out.println("Root: " + root5);
        System.out.println("=========================================================\n");
    }


}
