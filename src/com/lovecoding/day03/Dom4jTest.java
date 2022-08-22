package com.lovecoding.day03;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Dom4jTest {
    public static void main(String[] args) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read("src/demo.xml");
            Element rootElement = document.getRootElement();
            System.out.println(rootElement.getName());
//            System.out.println(rootElement.getText());
            List<Element> elements = rootElement.elements();
            for(Element e : elements) {
                //找到第一个元素了
                if(e.attributeValue("id").equals("1")) {
                    System.out.println(e.getName());
                    List<Element> elements1 = e.elements();
                    for(Element e1 : elements1) {
                        System.out.println(e1.getName());
                        if(e1.getName().equals("name")) System.out.println("姓名为 : " + e1.getText());
                        if(e1.getName().equals("age")) System.out.println("年龄为 : " + e1.getText());
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
