/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import model.trace.TCM;
import model.trace.TraceObjectBaseClass;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

public abstract class TraceBaseModel<ObjectClass extends TraceObjectBaseClass> {
    
    private LinkedHashMap<String,ObjectClass> map = new LinkedHashMap();
    
    abstract public void setModel(NodeList nodes);
    
    public boolean add(ObjectClass object){
        boolean boReturn = false;
        String name = object.getName();
        
        // Add Trace Object if it does not exists
        if (!this.map.containsKey(name))
        {
            this.map.put(name, object);
            this.logCurrentModel();
            boReturn = true;
        }
        return boReturn;
    }
    
    public boolean delete(ObjectClass object)
    {
        boolean boReturn = false;
        String name = object.getName();
        
        // Remove Trace Object if it exists
        if (this.map.containsKey(name))
        {
            this.map.remove(name);
            Logger.getGlobal().info(
                String.format("[Model] Object with ID \'%s\' successfully deleted from the model", name));
            this.logCurrentModel();
            boReturn = true;
        }
        return boReturn;
    }
    
    public ObjectClass get(String objectKey)
    {
        ObjectClass objectToSearch = null;
        // Retrieve Trace Object if it exists
        if (this.map.containsKey(objectKey))
        {
            objectToSearch = (ObjectClass)this.map.get(objectKey);
        }
        return objectToSearch;
    }
    
    protected String getDocString(Node node){
        String docString = null;
        if (node.hasChildNodes()) {
            NodeList objectNodeList = node.getChildNodes();
            for(int count = 0; count < objectNodeList.getLength(); count++)
            {
                if(objectNodeList.item(count).getNodeType() == Node.ELEMENT_NODE && 
                    objectNodeList.item(count).getNodeName().equals(TCM.XML_TAG_DOC))
                {
                    docString = objectNodeList.item(count).getTextContent();
                }
            }
        }
        return docString;
    }
    
    protected String getMandatoryAttribute(NamedNodeMap nodeAttributes, String attribute){
        String attributeValue = null;
        try {
            attributeValue = nodeAttributes.getNamedItem(attribute).getNodeValue();
        }
        catch (java.lang.NullPointerException e) {
            Logger.getGlobal().warning(
                String.format("[Model] \'%s\' not found at XML parsing",
                    attribute));
        }
        return attributeValue;
    }
    
    protected String getOptionalAttribute(
            NamedNodeMap nodeAttributes, 
            String attribute,
            String defaultValue){
        String attributeValue = null;
        try {
            attributeValue = nodeAttributes.getNamedItem(attribute).getNodeValue();
        }
        catch (java.lang.NullPointerException e) {
            attributeValue = defaultValue;
        }
        return attributeValue;
    }
    
    public ArrayList getArrayList(){
        ArrayList<ObjectClass> objectList = new ArrayList(this.map.values());
        return objectList;
    }
    
    private void logCurrentModel(){
        String modelArray = "";
        for ( TraceObjectBaseClass value : this.map.values()){
            modelArray = modelArray + value.getName() + " | ";
        }
        Logger.getGlobal().info(
            String.format("[Model] %s current model: [ %s ]",
                this.getClass().getSimpleName(),
                modelArray));
    }
    
    public void clearModel(){
        this.map.clear();
    }
}
