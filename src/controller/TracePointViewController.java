/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.MainEditorModel;
import model.trace.parameters.Parameter;
import model.trace.parameters.ParameterTraceCommand;
import model.trace.parameters.ParameterTracePoint;
import model.trace.PriorityClass;
import model.trace.TRACE;
import model.trace.TraceCommand;
import model.trace.TracePoint;
import model.trace.parameters.ParameterFactory;
import model.trace.parameters.ParameterFix;
import model.trace.parameters.ParameterStringFix;
import utils.ConfigWindowEvents;
import view.WindowTraceCommandConfig;
import view.WindowTracePointConfig;
import view.handler.CustomListModelHandler;

/**
 *
 * @author uidj5418
 */
public class TracePointViewController {
        
    private MainEditorModel                 model = null;
    private WindowTracePointConfig          view = null;
    
    private CustomListModelHandler<ParameterTracePoint>   tempParameterModel;
    
    private LinkedHashMap<String,String> parameterTypeMap = new LinkedHashMap(){ 
        {
            put( TRACE.TP_PARAM_TYPE_UINT8,     "(UINT8) - Unsigned Integer 8");
            put( TRACE.TP_PARAM_TYPE_UINT16,    "(UINT16) - Unsigned Integer 16");
            put( TRACE.TP_PARAM_TYPE_UINT32,    "(UINT32) - Unsigned Integer 32");
            put( TRACE.TP_PARAM_TYPE_INT8,      "(INT8) - Signed Integer 8");
            put( TRACE.TP_PARAM_TYPE_INT16,     "(INT16) - Signed Integer 16");
            put( TRACE.TP_PARAM_TYPE_INT32,     "(INT32) - Signed Integer 32");
            put( TRACE.TP_PARAM_TYPE_FIX,       "(PFIX) - Parameter with fixed length");
            put( TRACE.TP_PARAM_TYPE_STRING,    "(PSTRING) - String Parameter");
            put( TRACE.TP_PARAM_TYPE_STRINGFIX, "(PSTRINGFIX) - Parameter with fixed String");
            put( TRACE.TP_PARAM_TYPE_VAR,       "(PVAR) - Variable Data Parameter");
        }
    };
    
    public TracePointViewController(){
        this.tempParameterModel =   new CustomListModelHandler();
    }
    
    public void setModel(MainEditorModel model){
        if(this.model == null){
            this.model = model;
        }
    }
    
    public void setView(WindowTracePointConfig view){
        if(this.view == null){
            this.view = view;
            this.view.setLocationRelativeTo(null);
        }
    }
    
    public CustomListModelHandler<ParameterTracePoint> getParameterList(){
        return this.tempParameterModel;
    }
    
    public boolean createTracePointParameter(){
        
        // parameter creation validation
        boolean parameterCreatedFlag = false;
        
        // First window is shown with all Parameter Types in a list to select
        String selectedParameterType = (String) JOptionPane.showInputDialog(
                null,
                "Select a parameter type",
                "Parameter Type",
                JOptionPane.DEFAULT_OPTION,
                null,
                parameterTypeMap.values().toArray(),
                parameterTypeMap.values().toArray()[0]);
        
        if (selectedParameterType != null) {
            
            // Second window is shown to insert a name for the parameter
            String parameterName = (String) JOptionPane.showInputDialog(
                    null,
                    "Insert parameter name",
                    "Parameter Name",
                    JOptionPane.DEFAULT_OPTION);
            
            if (parameterName != null) {
                if (!parameterName.trim().isEmpty()) {
                    // Retrieve from Map the actual parameter type from raw type name
                    String parameterType = this.getParameterTypeFromSelectedValue(selectedParameterType);

                    // create a parameter object
                    ParameterTracePoint tpp = this.createParameter(
                        parameterName, 
                        parameterType);

                    //
                    if (tpp != null){
                        Logger.getGlobal().info(
                            String.format("[Controller] New %s Parameter created: \"%s\"",
                                    tpp.getType(),
                                    tpp));
                        parameterCreatedFlag = true;

                        // ask the user for extra info if the parameter is specialized
                        this.getExtraInformationFromParameter(tpp);
                        
                        //
                        this.tempParameterModel.addObjectToList(tpp);
                        this.view.update(ConfigWindowEvents.UPDATE_PARAMETER_LIST);
                        
                        //
                        this.view.setCurrentTracePointType(this.getTypeFromCurrentParameterList());
                        this.view.update(ConfigWindowEvents.UPDATE_CURRENT_TRACE_POINT_TYPE_LABEL);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                        this.view,
                        "ERROR: Name was not inserted!",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return parameterCreatedFlag;
    }
    
    private void getExtraInformationFromParameter(ParameterTracePoint parameter){
        if (parameter instanceof ParameterStringFix) {
            // 
            String stringValue = (String) JOptionPane.showInputDialog(
                    null,
                    "Insert string value for "+parameter.getType(),
                    "String Value",
                    JOptionPane.DEFAULT_OPTION);
            ((ParameterStringFix)parameter).setStringValue(stringValue);
        }
        else if (parameter instanceof ParameterFix) {
            if (parameter.getType().equals(TRACE.TP_PARAM_TYPE_FIX)) {
                // 
                String length = (String) JOptionPane.showInputDialog(
                        null,
                        "Insert length value for "+parameter.getType(),
                        "String Value",
                        JOptionPane.DEFAULT_OPTION);
                ((ParameterFix)parameter).setLength(length);
            }
        }
    }
    
    private String getTypeFromCurrentParameterList(){
        String type = null;
        if(!this.getParameterList().isEmpty()) {
            for( ParameterTracePoint param : this.getParameterList() ){
                if((param.getType().equals(TRACE.TP_PARAM_TYPE_STRING)) ||
                   (param.getType().equals(TRACE.TP_PARAM_TYPE_VAR))){
                    type = TRACE.TP_TYPE_DATA;
                }
            }
            if(type == null){
                type = TRACE.TP_TYPE_DATAFIX;
            }
        }
        else{
            type = TRACE.TP_TYPE_EVENT;
        }
        return type;
    }
    
    private String getParameterTypeFromSelectedValue(String selectedValue){
        String stringKey = null;
        for (String parameterTypeKey : parameterTypeMap.keySet()) {
            if (parameterTypeMap.get(parameterTypeKey).equals(selectedValue)) {
                stringKey = parameterTypeKey;
            }
        }
        return stringKey;
    }
    
    public boolean addTracePointData(
        String  TracePointName,
        String  TracePointType,
        String  TracePointSeverityLevel,
        String  TracePointConfigType,
        String  TracePointPriorityClass,
        String  TracePointEncoding,
        Integer TracePointlength
    ) {
        boolean itemAddedFlag = false;
        TracePoint tp = this.createTracePoint(
            TracePointName,
            TracePointType,
            TracePointSeverityLevel,
            TracePointConfigType,
            TracePointPriorityClass,
            TracePointEncoding,
            TracePointlength
        );
        if (tp != null){
            Logger.getGlobal().info(
                String.format("[Controller] New valid Trace Point created: \"%s\"", tp));
            //
            this.addCurrentParameterListToTraceObject(tp);
            tp.validateTypeFromParameters();
            itemAddedFlag = this.model.addItem(tp);
        }
        return itemAddedFlag;
    }
    
    private TracePoint createTracePoint(
        String  name,
        String  type,
        String  severityLevel,
        String  configType,
        String  priorityClass,
        String  encoding,
        Integer length
    ){
        TracePoint newTracePoint = null;
        // parameter validations here:
        
        // validations end
        newTracePoint = new TracePoint(
            name,
            type,
            severityLevel,
            configType,
            priorityClass,
            encoding,
            length
        );
        return newTracePoint;
    }
    
    private void addCurrentParameterListToTraceObject(TracePoint tracePointObj){
        for ( ParameterTracePoint param : this.tempParameterModel.getObjectArrayList() ) {
            tracePointObj.addParameter(param);
        }
        this.tempParameterModel.clearList();
    }
    
    private ParameterTracePoint createParameter(
        String name,
        String type
    ){
        ParameterTracePoint newTracePointParameter = null;
        // parameter validations here:
        
        // validations end
        newTracePointParameter = ParameterFactory.getParameter(type);
        newTracePointParameter.setName(name);
        
        return newTracePointParameter;
    }
    
    private void addCurrentPriorityClassesInView(){
        this.view.clearAllPriorityClassValues();
        for(PriorityClass pc : this.model.getPriorityClassesArrayList())
        {
            this.view.addPriorityClassValue(pc.getName());
        }
    }
    
    public void openTraceCommandWindow(){
        this.addCurrentPriorityClassesInView();
        this.view.setCurrentTracePointType(TRACE.TP_TYPE_EVENT);
        this.view.update(ConfigWindowEvents.UPDATE_CURRENT_PRIORITY_CLASS_VALUES);
        this.view.resetWindowToDefaultState();
        this.view.setVisible(true);
    }
    
}
