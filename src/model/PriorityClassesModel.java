/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.trace.PriorityClass;
import model.trace.TCM;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author uidq2031
 */
public class PriorityClassesModel extends TraceBaseModel{    
    
    private final String RE_ID_VALUE = "0?x?0?(\\d)";
    
    public void setModel(NodeList nodes){
        
        for(int count = 0; count < nodes.getLength(); count++)
        {
            if(nodes.item(count).getNodeType() == Node.ELEMENT_NODE && 
                nodes.item(count).getNodeName().equals(TCM.XML_TAG_PRIORITY_CLASS))
            {
                PriorityClass object = createObject(nodes.item(count));
                
                // add documentation string to object (if any)
                object.setDoc(this.getDocString(nodes.item(count)));
                
                this.add(object);
            }
        }
    }
    
    private PriorityClass createObject(Node node){
        
        String name = null;
        String idValue = null;
        
        NamedNodeMap nodeAttributes = node.getAttributes();
        
        name =      this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_NAME);
        idValue =   this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_ID);
        
        Pattern pattern = Pattern.compile(this.RE_ID_VALUE);
        Matcher matcher = pattern.matcher(idValue);
        
        if (matcher.find())
        {
            idValue = "0x0000000" + matcher.group(1);
        }
        
        PriorityClass priorityClass = new PriorityClass(name, idValue);
        
        return priorityClass;
    }
}
