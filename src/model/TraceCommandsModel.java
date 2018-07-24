/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.trace.TCM;
import model.trace.TraceCommand;
import model.trace.parameters.ParameterTraceCommand;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author uidq2031
 */
public class TraceCommandsModel extends TraceBaseModel{    
    
    public void setModel(NodeList nodes){
        
        for(int count = 0; count < nodes.getLength(); count++)
        {
            if(nodes.item(count).getNodeType() == Node.ELEMENT_NODE && 
                nodes.item(count).getNodeName().equals(TCM.XML_TAG_TRACECOMMAND))
            {
                TraceCommand object = createObject(nodes.item(count));
                
                // add documentation string to object (if any)
                object.setDoc(this.getDocString(nodes.item(count)));
                
                this.add(object);
            }
        }
    }
    
    private TraceCommand createObject(Node node){
        
        String name = null;
        String type = null;
        
        NamedNodeMap nodeAttributes = node.getAttributes();
        
        name = this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_NAME);
        type = this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_TYPE);
        
        TraceCommand traceCommand = new TraceCommand(name, type);
        
        NodeList traceCommandChildNodes = node.getChildNodes();
        
        //Get CParams Tag position
        int cParamsId = this.getIdByTag(traceCommandChildNodes, TCM.XML_TAG_CPARAMS);
        
        if (cParamsId != -1) {
            
            Node cParamNode = traceCommandChildNodes.item(cParamsId);
            
            NodeList traceCommandParams = cParamNode.getChildNodes();
            
            for(int count = 0; count < traceCommandParams.getLength(); count++)
            {
                if (traceCommandParams.item(count).getNodeName().equals(TCM.XML_TAG_CPARAM)){
                    
                    NamedNodeMap cParamAttributes = traceCommandParams.item(count).getAttributes();
                    
                    String parameterName = this.getMandatoryAttribute(cParamAttributes, TCM.XML_ATTR_NAME);
                    String parameterType = this.getMandatoryAttribute(cParamAttributes, TCM.XML_ATTR_TYPE);
                    
                    ParameterTraceCommand newTraceCommandParam = 
                            new ParameterTraceCommand(parameterName, parameterName);
                    
                    traceCommand.addParameter(newTraceCommandParam);
                }
            }
        }
        
        // TODO: Set optional attributes for tracecommand
        
        return traceCommand;
    }
    
    private int getIdByTag(NodeList modelList, String tagName)
    {
        //default return value
        int intId = -1;
        //Transverse all Node list searching for tagName
        for(int count = 0; count < modelList.getLength(); count++)
        {
            if(modelList.item(count).getNodeName().equals(tagName))
            {
                intId = count;
                break;
            }
        }
        return intId;
    }
    
}
