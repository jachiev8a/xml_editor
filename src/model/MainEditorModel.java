/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.logging.Logger;
import model.trace.PriorityClass;
import model.trace.TCM;
import model.trace.TraceCommand;
import model.trace.TracePoint;
import org.w3c.dom.NodeList;
import utils.Observable;

/**
 *
 * @author uidj5418
 */
public class MainEditorModel extends Observable {

    //Public identfiers, used in public interfaces
    public  static final int    TRACE_POINT_TYPE = 0;
    public  static final int    TRACE_COMMAND_TYPE = 1;
    public  static final int    PRIORITY_CLASS_TYPE = 2;
    public  static final int    ALL_TYPES = 3;
    
    //Private models
    private final TraceBaseModel <TracePoint> tracePointsModel;
    private final TraceBaseModel <TraceCommand> traceCommandsModel;
    private final TraceBaseModel <PriorityClass> priorityClassesModel;
    
    public MainEditorModel(){
        //Instantiate submodels
        
        tracePointsModel = new TracePointsModel();
        traceCommandsModel = new TraceCommandsModel();
        priorityClassesModel = new PriorityClassesModel();
    }

    public boolean setModel(NodeList modelList)
    {
        //clear current models
        this.clearAllModels();
        //default return value 
        boolean boReturn = true;
        //Get Component Tag position
        int intCompId = this.getIdByTag(modelList, TCM.XML_TAG_COMPONENT);
        //Validate Component found
        if(-1 != intCompId)
        {
            //Log INFO
            Logger.getGlobal().info(String.format("Component %s", modelList.item(intCompId).getAttributes().getNamedItem("name")));
            //Create child models
            this.createChildModels(modelList.item(intCompId).getChildNodes());
        }
        else {
            boReturn = false;
        }
        return boReturn;
    }
    
    public void clearAllModels(){
        this.tracePointsModel.clearModel();
        this.traceCommandsModel.clearModel();
        this.priorityClassesModel.clearModel();
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
    
    private void createChildModels(NodeList componentChildList)
    {
        int intTempId;
        // Create Trace Points submodel
        intTempId = this.getIdByTag(componentChildList, TCM.XML_TAG_TRACEPOINTS);
        if (-1 != intTempId)
        {
            this.tracePointsModel.setModel(componentChildList.item(intTempId).getChildNodes());
        }
        
        // Create Trace Commands submodel
        intTempId = this.getIdByTag(componentChildList, TCM.XML_TAG_TRACECOMMANDS);
        if (-1 != intTempId)
        {
            this.traceCommandsModel.setModel(componentChildList.item(intTempId).getChildNodes());
        }        
        
        // Create Priority Classes submodel
        intTempId = this.getIdByTag(componentChildList, TCM.XML_TAG_PRIORITY_CLASSES);
        if (-1 != intTempId)
        {
            this.priorityClassesModel.setModel(componentChildList.item(intTempId).getChildNodes());
        }   
    }
    
    public boolean addItem(TracePoint tp)
    {
        boolean boReturn = false;
        boReturn = this.tracePointsModel.add(tp);
        if (boReturn) {
            Logger.getGlobal().info(
                String.format("[Model] Trace Point successfully added the model: \'%s\'", tp));
            this.notifyObservers(tp);
        }
        return boReturn;
    }
    
    public boolean addItem(TraceCommand tc)
    {
        boolean boReturn = false;
        boReturn = this.traceCommandsModel.add(tc);
        if (boReturn) {
            Logger.getGlobal().info(
                String.format("[Model] Trace Command successfully added the model: \'%s\'", tc));
            this.notifyObservers(tc);
        }
        return boReturn;
    }
    
    public boolean addItem(PriorityClass pc)
    {
        boolean boReturn = false;
        boReturn = this.priorityClassesModel.add(pc);
        if (boReturn) {
            Logger.getGlobal().info(
                String.format("[Model] Priority Class successfully added the model: \'%s\'", pc));
            this.notifyObservers(pc);
        }
        return boReturn;
    }
    
    public Object getItem(int itemType, String itemId){
        Object objectToGet = null;
        switch (itemType) {
            case MainEditorModel.TRACE_POINT_TYPE:
                objectToGet = this.tracePointsModel.get(itemId);
                break;
            case MainEditorModel.TRACE_COMMAND_TYPE:
                objectToGet = this.traceCommandsModel.get(itemId);
                break;
            case MainEditorModel.PRIORITY_CLASS_TYPE:
                objectToGet = this.priorityClassesModel.get(itemId);
                break;
            default:
                break;
        }
        if (objectToGet == null) {
            Logger.getGlobal().warning(
                String.format("[Model] Object with ID \'%s\' not found in the model", itemId));
        }
        else{
            Logger.getGlobal().info(
                String.format("[Model] Object with ID \'%s\' successfully found in the model", itemId));
        }
        return objectToGet;
    }
    
    public boolean deleteItem(TracePoint tp)
    {
        boolean boReturn = false;
        boReturn = this.tracePointsModel.delete(tp);
        if (boReturn) {
            this.notifyObservers(tp);
        }
        return boReturn;
    }
    
    public boolean deleteItem(TraceCommand tc)
    {
        boolean boReturn = false;
        boReturn = this.traceCommandsModel.delete(tc);
        if (boReturn) {
            this.notifyObservers(tc);
        }
        return boReturn;
    }
    
    public boolean deleteItem(PriorityClass pc)
    {
        boolean boReturn = false;
        boReturn = this.priorityClassesModel.delete(pc);
        if (boReturn) {
            this.notifyObservers(pc);
        }
        return boReturn;
    }
    
    public ArrayList getTracePointsArrayList()
    {
        ArrayList<TracePoint> tracePoints = this.tracePointsModel.getArrayList();
        return tracePoints;
    }
    
    public ArrayList getTraceCommandsArrayList()
    {
        ArrayList<TraceCommand> traceCommands = this.traceCommandsModel.getArrayList();
        return traceCommands;
    }
    
    public ArrayList<PriorityClass> getPriorityClassesArrayList()
    {
        ArrayList<PriorityClass> priorityClasses = this.priorityClassesModel.getArrayList();
        return priorityClasses;
    }
}
