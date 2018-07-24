/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.trace.parameters.Parameter;
import model.trace.parameters.ParameterTraceCommand;
import model.trace.PriorityClass;
import model.trace.TraceCommand;
import model.trace.TraceObjectBaseClass;
import model.trace.TracePoint;
import model.trace.parameters.ParameterFix;
import model.trace.parameters.ParameterStringFix;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author uidj5418
 */
public class TcmCreator {
    
    private ArrayList<TracePoint> tracePointList = new ArrayList();
    private ArrayList<TraceCommand> traceCommandList = new ArrayList();
    private ArrayList<PriorityClass> priorityClassList = new ArrayList();
    
    public void setTracePointList(ArrayList tracePointListSource){
        this.tracePointList.clear();
        this.tracePointList.addAll(tracePointListSource);
    }
    
    public void setTraceCommandList(ArrayList traceCommandListSource){
        this.traceCommandList.clear();
        this.traceCommandList.addAll(traceCommandListSource);
    }
    
    public void setPriorityClassList(ArrayList priorityClassListSource){
        this.priorityClassList.clear();
        this.priorityClassList.addAll(priorityClassListSource);
    }
    
    public String create(String tcmFilePath, String componentName) {
        
        String tcmAbsolutePath = null;
        
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element: Component
            Document doc = docBuilder.newDocument();
            Element rootComponentElement = doc.createElement("Component");
            
            rootComponentElement.setAttribute("name", componentName);
            rootComponentElement.setAttribute("longName", componentName);
            
            doc.appendChild(rootComponentElement);

            // <PriorityClasses> main tag
            Element tagPriorityClasses = doc.createElement("PriorityClasses");
            rootComponentElement.appendChild(tagPriorityClasses);
            
            for( PriorityClass pc : this.priorityClassList ){
                //
                Element priorityClassElement = doc.createElement("PrioClass");
                
                priorityClassElement.setAttribute("name", pc.getName());
                priorityClassElement.setAttribute("id", pc.getValue());
                
                tagPriorityClasses.appendChild(priorityClassElement);
            }
            
            // <TracePoints> main tag
            Element tagTracePoints = doc.createElement("TracePoints");
            rootComponentElement.appendChild(tagTracePoints);
            
            for( TracePoint tp : this.tracePointList ){
                //
                Element tracePointElement = doc.createElement("TracePoint");
                
                tracePointElement.setAttribute("name", tp.getName());
                tracePointElement.setAttribute("type", tp.getType());
                tracePointElement.setAttribute("severityLevel", tp.getSeverityLevel());
                tracePointElement.setAttribute("configType", tp.getConfigType());
                tracePointElement.setAttribute("encoding", tp.getEncoding());
                tracePointElement.setAttribute("prioClass", tp.getPrioClass());
                tracePointElement.setAttribute("length", tp.getLength().toString());
                
                tagTracePoints.appendChild(tracePointElement);
                
                //
                this.createDocElement(doc, tracePointElement, tp);
                
                // Trace Point Parameters
                if (tp.hasParameters()) {
                    
                    // <Parameters> main tag
                    Element tagParameters = doc.createElement("Parameters");
                    tracePointElement.appendChild(tagParameters);
                    
                    for( Parameter param : tp.getParameterList() ){
                        //
                        Element paramElement = doc.createElement("Parameter");
                        
                        //
                        paramElement.setAttribute("name", param.getName());
                        paramElement.setAttribute("type", param.getType());
                        
                        //
                        if (param instanceof ParameterStringFix){
                            paramElement.setAttribute(
                                    "stringValue",
                                    ((ParameterStringFix)param).getStringValue());
                        }
                        else if (param instanceof ParameterFix){
                            paramElement.setAttribute(
                                    "length",
                                    ((ParameterFix)param).getLength());
                        }
                        
                        //
                        tagParameters.appendChild(paramElement);
                        
                        //
                        this.createDocElement(doc, paramElement, param);
                    }
                }
            }
            /*=========================
            * <TracePoints> main tag
            =========================*/
            Element tagTraceCommands = doc.createElement("TraceCommands");
            rootComponentElement.appendChild(tagTraceCommands);
            
            for( TraceCommand tc : this.traceCommandList ){
                //
                Element traceCommandElement = doc.createElement("TraceCommand");
                
                traceCommandElement.setAttribute("name", tc.getName());
                traceCommandElement.setAttribute("type", tc.getType());
                
                tagTraceCommands.appendChild(traceCommandElement);
                
                //
                this.createDocElement(doc, traceCommandElement, tc);
                
                // Trace Command Parameters
                if (tc.hasParameters()) {
                    // <CParams> main tag
                    Element tagCParams = doc.createElement("CParams");
                    traceCommandElement.appendChild(tagCParams);
                    
                    for( Parameter param : tc.getParameterList() ){
                        //
                        Element paramElement = doc.createElement("CParam");
                        
                        //
                        paramElement.setAttribute("name", param.getName());
                        paramElement.setAttribute("type", param.getType());
                        
                        //
                        tagCParams.appendChild(paramElement);
                        
                        //
                        this.createDocElement(doc, paramElement, param);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(tcmFilePath));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!: "+tcmFilePath);
            
            tcmAbsolutePath = tcmFilePath;

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
        return tcmAbsolutePath;
    }
    
    private void createDocElement(
            Document sourceDoc,
            Element elementToAppend,
            TraceObjectBaseClass traceObject)
    {
        String objectDocString = traceObject.getDoc();
        Element docElement = null;
        
        if (!(objectDocString == null)) {
            docElement = sourceDoc.createElement("Doc");
            docElement.setTextContent(objectDocString);
            elementToAppend.appendChild(docElement);
        }
    }
    
}
