package com.javarush.task.task33.task3309;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, TransformerException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        marshaller.marshal(obj, document);
        
        NodeList nodeList = document.getElementsByTagName("*");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node thisNode = nodeList.item(i).getFirstChild();
            if (thisNode.getTextContent().matches(".*[<>&\'\"].*")) {
                CDATASection cdataSection = document.createCDATASection(thisNode.getTextContent());
                nodeList.item(i).replaceChild(cdataSection, thisNode);
            }
            if (nodeList.item(i).getNodeName().equals(tagName)) {
                nodeList.item(i).getParentNode().insertBefore(document.createComment(comment), nodeList.item(i));
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException {
        System.out.println(toXmlWithComment(new First(), "nothing", "it's a comment"));
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));
    }
}

@XmlRootElement(name = "first")
class First {
    @XmlElement(name = "second")
    public String item1 = "some string";
    @XmlElement(name = "second")
    public String item2 = "need CDATA because of <second>";
    @XmlElement(name = "second")
    public String item3 = "";
    @XmlElement(name = "third")
    public String item4;
    @XmlElement(name = "forth")
    public Second[] third = new Second[]{new Second()};
    @XmlElement(name = "fifth")
    public String item5 = "need CDATA because of \"";
}

class Second {
    @XmlElement(name = "second")
    public String item1 = "some string";
    @XmlElement(name = "second")
    public String item2 = "need CDATA because of <second>";
}