package com.oshovskii.parsing.xml;

import com.oshovskii.parsing.Parser;
import com.oshovskii.parsing.model.People;
import com.oshovskii.parsing.model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParser extends Parser {

    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_NAME = "name";
    private static final String TAG_AGE = "age";

    @Override
    public Root parse() {
        Root root = new Root();
        Document document;
        try {
            document = buildDocument();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Node rootNode = document.getFirstChild();
        NodeList rootChilds = rootNode.getChildNodes();

        String mainName = null;
        Node peopleNode = null;
        for (int i = 0; i < rootChilds.getLength(); i++) {
            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (rootChilds.item(i).getNodeName()) {
                case TAG_NAME_MAIN: {
                    mainName = rootChilds.item(i).getTextContent();
                    break;
                }
                case TAG_PEOPLE: {
                    peopleNode = rootChilds.item(i);
                    break;
                }
            }
        }

        if (peopleNode == null) {
            return null;
        }

        List<People> peopleList = parsePeople(peopleNode);

        root.setName(mainName);
        root.setPeople(peopleList);

        return root;
    }

    private Document buildDocument() throws Exception {
        File file = new File("src/main/resources/data/test.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    private List<People> parsePeople(Node peopleNode){
        List<People> peopleList = new ArrayList<>();
        NodeList peopleChilds = peopleNode.getChildNodes();
        for (int i = 0; i < peopleChilds.getLength(); i++) {
            if (peopleChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!peopleChilds.item(i).getNodeName().equals(TAG_ELEMENT)) {
                continue;
            }

            People people = parseElement(peopleChilds.item(i));
            peopleList.add(people);
        }
        return peopleList;
    }

    private People parseElement(Node elementNode) {
        String name = "";
        int age = 0;
        NodeList elementChilds = elementNode.getChildNodes();

        for (int j = 0; j < elementChilds.getLength(); j++) {
            if (elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (elementChilds.item(j).getNodeName()) {
                case TAG_NAME: {
                    name = elementChilds.item(j).getTextContent();
                    break;
                }
                case TAG_AGE: {
                    age = Integer.parseInt(elementChilds.item(j).getTextContent());
                    break;
                }
            }
        }
        return new People(name, age);
    }
}
