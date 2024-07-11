package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestUtils {

    public static final long TIME_OUT = 8;

    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringHashMap = new HashMap<>();
        // Get Document Builder
        var factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build Document
        Document document = builder.parse(file);

        // Normalize XML Structure; It's just too important!!
        document.getDocumentElement().normalize();

        // Here comes the root node
        Element rootElement = document.getDocumentElement();
//        System.out.println(rootElement.getNodeName());

        // Get all elements
        NodeList nodeList = document.getElementsByTagName("string");
//        System.out.println("======================");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
//            System.out.println(" "); // Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                // Store each element key value in map
                stringHashMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringHashMap;
    }

    public String currentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Logger logger() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

}
