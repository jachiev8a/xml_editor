/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Logger;
import model.trace.TCM;
import model.trace.TRACE;
import model.trace.TracePoint;
import model.trace.parameters.ParameterFactory;
import model.trace.parameters.ParameterTraceCommand;
import model.trace.parameters.ParameterTracePoint;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class TracePointsModel extends TraceBaseModel{
    
    public void setModel(NodeList nodes){
        
        for(int count = 0; count < nodes.getLength(); count++)
        {
            if(nodes.item(count).getNodeType() == Node.ELEMENT_NODE && 
                nodes.item(count).getNodeName().equals(TCM.XML_TAG_TRACEPOINT))
            {   
                TracePoint object = createObject(nodes.item(count));
                
                // add documentation string to object (if any)
                object.setDoc(this.getDocString(nodes.item(count)));
                
                this.add(object);
            }
        }
    }
    
    private TracePoint createObject(Node node){
        
        String name = null;
        String type = null;
        String severityLevel = null;
        String configType = null;
        String encoding = null;
        String length = null;
        
        NamedNodeMap nodeAttributes = node.getAttributes();
        
        name = this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_NAME);
        type = this.getMandatoryAttribute(nodeAttributes, TCM.XML_ATTR_TYPE);
        
        TracePoint tracePoint = new TracePoint(name, type);
        
        severityLevel = this.getOptionalAttribute(
                nodeAttributes,
                TCM.XML_ATTR_SEVERITY_LEVEL,
                "info");
        
        configType = this.getOptionalAttribute(
                nodeAttributes, 
                TCM.XML_ATTR_CONFIG_TYPE,
                TRACE.TP_CONFIG_TYPE_DYNAMIC);
        
        encoding = this.getOptionalAttribute(
                nodeAttributes,
                TCM.XML_ATTR_ENCODING,
                TRACE.TP_ENCODING_HEX);
        
        length = this.getOptionalAttribute(
                nodeAttributes, 
                TCM.XML_ATTR_LENGTH,
                null);
        
        tracePoint.setSeverityLevel(severityLevel);
        tracePoint.setConfigType(configType);
        tracePoint.setEncoding(encoding);
        
        if (length == null) {
            tracePoint.setLength(0);
        }
        else {
            tracePoint.setLength(Integer.valueOf(length));
        }
        
        NodeList tracePointChildNodes = node.getChildNodes();
        
        //Get Parameters Tag position
        int parametersTagId = this.getIdByTag(tracePointChildNodes, TCM.XML_TAG_PARAMETERS);
        
        if (parametersTagId != -1) {
            
            Node ParametersNode = tracePointChildNodes.item(parametersTagId);
            
            NodeList tracePointParams = ParametersNode.getChildNodes();
            
            for(int count = 0; count < tracePointParams.getLength(); count++)
            {
                if (tracePointParams.item(count).getNodeName().equals(TCM.XML_TAG_PARAMETER)){
                    
                    NamedNodeMap paramAttributes = tracePointParams.item(count).getAttributes();
                    
                    String parameterName = this.getMandatoryAttribute(paramAttributes, TCM.XML_ATTR_NAME);
                    String parameterType = this.getMandatoryAttribute(paramAttributes, TCM.XML_ATTR_TYPE);
                    
                    if(parameterType != null){
                        ParameterTracePoint newTracePointParam = 
                                ParameterFactory.getParameter(parameterType);
                        
                        newTracePointParam.setName(parameterName);

                        tracePoint.addParameter(newTracePointParam);
                    }
                }
            }
        }
        
        return tracePoint;
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
