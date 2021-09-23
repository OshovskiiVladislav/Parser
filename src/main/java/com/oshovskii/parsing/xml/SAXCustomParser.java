package com.oshovskii.parsing.xml;

import com.oshovskii.parsing.model.Root;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXCustomParser {

    public Root parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParserHandler handler = new SAXParserHandler();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }

        File file = new File("src/main/resources/data/test.xml");
        try {
            parser.parse(file, handler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }

        return handler.getRoot();
    }
}
