/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import utils.TcmParser;
import org.w3c.dom.NodeList;
import model.MainEditorModel;
import model.trace.PriorityClass;
import model.trace.TraceCommand;
import model.trace.TracePoint;
import utils.Observer;
import utils.events.EventUpdateAllLists;
import utils.events.EventUpdatePriorityClassList;
import utils.events.EventUpdateTraceCommandList;
import utils.events.EventUpdateTracePointList;
import view.MainEditorFrame;
import view.handler.CustomListModelHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.DropListener;
import utils.TcmCreator;
import utils.events.EventEnableAllComponentControls;
import utils.events.EventUpdateCurrentComponentNameLabel;
import utils.events.EventUpdateCurrentTcmPath;
import utils.events.EventUpdateTcmPathForegroundToNormal;
import utils.events.EventUpdateTcmPathForegroundToRed;
import view.handler.VisualComponentHandler;

/**
 *
 * @author uidj5418
 */
public class MainEditorController implements Observer {
    
    //TCM parser object
    TcmParser tcmParserObject = new TcmParser();
    TcmCreator tcmCreatorObject = new TcmCreator();
    
    VisualComponentHandler componentHandler = new VisualComponentHandler();
    
    //
    private DropListener            dropListenerObject;
    
    //
    TraceCommandViewController      traceCommandViewControler;
    TracePointViewController        tracePointViewControler;
    PriorityClassViewController     priorityClassViewControler;
    
    //Data model reference
    private MainEditorModel modelRef;
    private MainEditorFrame view;
    
    private CustomListModelHandler <TracePoint>     tracePointListModel;
    private CustomListModelHandler <TraceCommand>   traceCommandListModel;
    private CustomListModelHandler <PriorityClass>  priorityClassListModel;
    
    private final String RE_OBJECT_ID_PATTERN = "\'(.*?)\'";
    private final String RE_TCM_FILENAME_PATTERN = "[\\w\\d\\-\\_\\=\\:\\/\\s\\\\]*\\.tcm";
    private final String RE_TCM_COMPONENT_NAME_PATTERN = "([\\w\\d\\-\\_\\=\\s]*)\\.tcm";
    
    private final String DROP_TCM_MESSAGE = "Drop your TCM File Anywhere in the window";
    private final String EMPTY_TCM_MESSAGE = "";
    
    private boolean tcmFileLoaded = false;
    
    public MainEditorController(){
        this.tracePointListModel =      new CustomListModelHandler();
        this.traceCommandListModel =    new CustomListModelHandler();
        this.priorityClassListModel =   new CustomListModelHandler();
        //
        this.dropListenerObject =       null;
    }
    
    public void setView(MainEditorFrame view) {
        this.view = view;
    }
    
    public void setModelRef(MainEditorModel modelRef)
    {
        this.modelRef = modelRef;
    }
    
    public boolean hasTcmFileLoaded(){
        return tcmFileLoaded;
    }
    
    public void setTcmFileAsLoaded(){
        this.tcmFileLoaded = true;
        this.view.update( new EventEnableAllComponentControls() );
    }
    
    public void setTcmFileAsUnloaded(){
        this.tcmFileLoaded = false;
    }
    
    public void setPartnerController(TraceCommandViewController controller)
    {
        this.traceCommandViewControler = controller;
    }
	
    public void setPartnerController(TracePointViewController controller)
    {
        this.tracePointViewControler = controller;
    }
    
    public void setPartnerController(PriorityClassViewController controller)
    {
        this.priorityClassViewControler = controller;
    }
    
    public void setDropListenerObject(DropListener dropListenerObj){
        if (this.dropListenerObject == null) {
            this.dropListenerObject = dropListenerObj;
            this.dropListenerObject.addObserver(this);
        }
    }
	
    public void openTraceCommandWindow(){
        this.traceCommandViewControler.openTraceCommandWindow();
    }
    
    public void openTracePointWindow(){
        this.tracePointViewControler.openTraceCommandWindow();
    }
    
    public void openPriorityClassWindow(){
        this.priorityClassViewControler.openPriorityClassWindow();
    }
    
    public void openTcmFile(String filePath)
    {
        //Retrieve node list from TCM file
        NodeList tcmNodes = this.tcmParserObject.parseFile(filePath);
        //Validate tcmNode list created
        if(tcmNodes != null)
        {
            //Create Model from node list
            if (this.modelRef.setModel(tcmNodes)){
                // clone TP lists from model to add them to the view reference
                this.tracePointListModel.cloneFromListReference(
                    this.modelRef.getTracePointsArrayList());
                // clone TC lists from model to add them to the view reference
                this.traceCommandListModel.cloneFromListReference(
                    this.modelRef.getTraceCommandsArrayList());
                // clone PC lists from model to add them to the view reference
                this.priorityClassListModel.cloneFromListReference(
                    this.modelRef.getPriorityClassesArrayList());
                // tell the view that all lists need to be updated with new values
                this.view.update( new EventUpdateAllLists() );
                
                //
                this.view.setCurrentFileLoaded(filePath);
                this.view.setCurrentComponentName(
                    this.getComponentNameFromTcmFileName(filePath));
                //
                this.setTcmFileAsLoaded();
                
                //
                this.view.update( new EventUpdateCurrentTcmPath() );
                this.view.update( new EventUpdateCurrentComponentNameLabel() );
                this.view.update( new EventUpdateTcmPathForegroundToNormal() );
            }
        }
    }
    
    public void createTcmFile(String absoluteFilePath, String componentName) {
        //
        this.tcmCreatorObject.setTracePointList(
            this.modelRef.getTracePointsArrayList());
        this.tcmCreatorObject.setTraceCommandList(
            this.modelRef.getTraceCommandsArrayList());
        this.tcmCreatorObject.setPriorityClassList(
            this.modelRef.getPriorityClassesArrayList());
        //
        String tcmAbsolutPath = this.tcmCreatorObject.create(absoluteFilePath, componentName);
        
        if (tcmAbsolutPath != null){
            
            //
            File tcmFileObject = new File(tcmAbsolutPath);
            String newComponentName = this.getComponentNameFromTcmFileName(tcmFileObject);
            
            // set updated values to Component and TcmPath
            this.view.setCurrentComponentName(newComponentName);
            this.view.setCurrentFileLoaded(tcmAbsolutPath);
            
            //
            this.setTcmFileAsLoaded();
            
            // try to open tcm file in OS File Explorer
            if (!this.showPathInExplorer(tcmAbsolutPath)){
                Logger.getGlobal().warning(
                    String.format("[Controller] TCM file could not be shown in explorer: \'%s\'",
                        tcmAbsolutPath));
            }
        }
        else{
            // error
        }
    }
    
    public CustomListModelHandler getTracePointList(){
        return this.tracePointListModel;
    }
    
    public CustomListModelHandler getTraceCommandList(){
        return this.traceCommandListModel;
    }
    
    public CustomListModelHandler getPriorityClassList(){
        return this.priorityClassListModel;
    }
    
    public void deleteTracePointData(String stringValue){
        Logger.getGlobal().info(
            String.format("[Controller] Delete Trace Point data: %s", stringValue));
        String tracePointId = this.getObjectIdFromString(stringValue);
        if (!(tracePointId == null))
        {
            Object objectToSearch = this.modelRef.getItem(
                MainEditorModel.TRACE_POINT_TYPE,
                tracePointId);
            if (!(objectToSearch == null)){
                this.modelRef.deleteItem((TracePoint)objectToSearch);
            }
        }
    }
    
    public void deleteTraceCommandData(String stringValue){
        String traceCommandId = this.getObjectIdFromString(stringValue);
        if (!(traceCommandId == null))
        {
            Object objectToSearch = this.modelRef.getItem(
                MainEditorModel.TRACE_COMMAND_TYPE,
                traceCommandId);
            if (!(objectToSearch == null)){
                this.modelRef.deleteItem((TraceCommand)objectToSearch);
            }
        }
    }
    
    public void deletePriorityClassData(String stringValue){
        String priorityClassId = this.getObjectIdFromString(stringValue);
        if (!(priorityClassId == null))
        {
            Object objectToSearch = this.modelRef.getItem(
                MainEditorModel.PRIORITY_CLASS_TYPE,
                priorityClassId);
            if (!(objectToSearch == null)){
                this.modelRef.deleteItem((PriorityClass)objectToSearch);
            }
        }
    }
    
    private String getObjectIdFromString(String stringId) {
        Pattern pattern = Pattern.compile(this.RE_OBJECT_ID_PATTERN);
        Matcher matcher = pattern.matcher(stringId);
        String objectId = null;
        if (matcher.find())
        {
            objectId = matcher.group(1);
            Logger.getGlobal().info(
                String.format("[Controller] Object ID Pattern found: \'%s\'", objectId));
        }
        return objectId;
    }
    
    public String createNewComponentName(){
        String componentName = this.componentHandler.chooseComponentName(this.view);
        return componentName;
    }
    
    public String chooseFileDestination(String componentName){
        String componentFileName = 
            this.componentHandler.chooseTcmFileDestinationFromWindowsExplorer(
                this.view,
                componentName);
        if (componentFileName == null) {
            
        }
        return componentFileName;
    }
    
    public boolean showPathInExplorer(String pathToShow){
        boolean isShown = true;
        try {
            Runtime.getRuntime().exec("explorer.exe /select," + pathToShow);
        } catch ( IOException ex ) {
            isShown = false;
        }
        return isShown;
    }
    
    public boolean isTcmFile(File fileObject){
        boolean tcmFileFlag = false;
        tcmFileFlag = this.isTcmFile(fileObject.getAbsolutePath());
        return tcmFileFlag;
    }
    
    public boolean isTcmFile(String fileName){
        boolean tcmFileFlag = false;
        // RE to find TCM extension in the file name
        Pattern pattern = Pattern.compile(this.RE_TCM_FILENAME_PATTERN);
        Matcher matcher = pattern.matcher(fileName);
        // 
        if (matcher.find()) {
            tcmFileFlag = true;
        }
        return tcmFileFlag;
    }
    
    public String getComponentNameFromTcmFileName(File tcmFileName){
        String componentName = null;
        componentName = this.getComponentNameFromTcmFileName(tcmFileName.getAbsolutePath());
        return componentName;
    }
    
    public String getComponentNameFromTcmFileName(String tcmFileName){
        String componentName = null;
        Pattern pattern = Pattern.compile(this.RE_TCM_COMPONENT_NAME_PATTERN);
        Matcher matcher = pattern.matcher(tcmFileName);
        if (matcher.find())
        {
            componentName = matcher.group(1);
            Logger.getGlobal().info(
                String.format("[Controller] Component name Pattern found: \'%s\'", componentName));
        }
        return componentName;
    }
            
    @Override
    public void update(Object object) {
        if ( object instanceof TracePoint ){
            this.tracePointListModel.cloneFromListReference(
                this.modelRef.getTracePointsArrayList());
            this.view.update(new EventUpdateTracePointList());
        }
        else if ( object instanceof TraceCommand ){
            this.traceCommandListModel.cloneFromListReference(
                this.modelRef.getTraceCommandsArrayList());
            this.view.update(new EventUpdateTraceCommandList());
        }
        else if ( object instanceof PriorityClass ){
            this.priorityClassListModel.cloneFromListReference(
                this.modelRef.getPriorityClassesArrayList());
            this.view.update(new EventUpdatePriorityClassList());
        }
        else if ( object instanceof File ){
            if (this.isTcmFile((File) object)) {
                this.openTcmFile(((File) object).getAbsolutePath());
            }
            else if (!this.hasTcmFileLoaded()){
                this.view.setCurrentFileLoaded(this.EMPTY_TCM_MESSAGE);
                this.view.update( new EventUpdateCurrentTcmPath() );
                this.view.update(new EventUpdateTcmPathForegroundToNormal() );
            }
        }
        else if ( object instanceof DropTargetDragEvent ){
            if(!this.hasTcmFileLoaded()){
                this.view.setCurrentFileLoaded(this.DROP_TCM_MESSAGE);
                this.view.update( new EventUpdateCurrentTcmPath() );
                this.view.update(new EventUpdateTcmPathForegroundToRed() );
            }
        }
        else if ( object instanceof DropTargetEvent ){
            if(!this.hasTcmFileLoaded()){
                this.view.setCurrentFileLoaded(this.EMPTY_TCM_MESSAGE);
                this.view.update( new EventUpdateCurrentTcmPath() );
                this.view.update(new EventUpdateTcmPathForegroundToNormal() );
            }
        }
    }

    @Override
    public void update() {
        this.view.update(new EventUpdateTracePointList());
    }
}
