/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author uidj5418
 */
public class TcmParser {
    
    public TcmParser(){
    }
    
    public NodeList parseFile(String XmlFilePath){
        //Defaul return value
        NodeList returnList = null;
        //Validate XML/TCM valie path
        String filePath = this.validateXmlFilePath(XmlFilePath);
        //If valid TCM File path
        if(filePath != null) { 
            //Log INFO
            Logger.getGlobal().info(
                String.format("TCM File to be parsed: \"%s\"", filePath)
            );
            //Parse XML file
            returnList = this.parseXmlFile(filePath);
        }
        
        return returnList;
    }
    
    private String validateXmlFilePath(String XmlFilePath){
        File fileObject = new File(XmlFilePath);
        // check that file exists and it is not a directory
        if(fileObject.exists() && !fileObject.isDirectory()) { 
            return fileObject.getAbsolutePath();
        }
        return null;
    }
    
    private NodeList parseXmlFile(String xmlFilePath) {
        //Default return value
        NodeList xmlNodes = null;
        try {
            //Open and read XML file
            Document doc = openXmlFile(xmlFilePath);
            //Read XML document
            xmlNodes = readXmlDoc(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlNodes;
    }

    private NodeList readXmlDoc(Document doc) throws DOMException {        
        return doc.getChildNodes();
    }

    private Document openXmlFile(String xmlFilePath) throws SAXException, ParserConfigurationException, IOException {
        File inputFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
    
}
