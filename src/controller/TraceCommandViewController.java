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
import utils.ConfigWindowEvents;
import view.WindowTraceCommandConfig;
import view.handler.CustomListModelHandler;

/**
 *
 * @author uidj5418
 */
public class TraceCommandViewController {
        
    private MainEditorModel                 model = null;
    private WindowTraceCommandConfig        view = null;
    
    private CustomListModelHandler<ParameterTraceCommand>   tempParameterModel;
    
    private LinkedHashMap<String,String> parameterTypeMap = new LinkedHashMap(){ 
        {
            put( TRACE.TC_PARAM_TYPE_UINT8,     "(UINT8) - Unsigned Integer 8");
            put( TRACE.TC_PARAM_TYPE_UINT16,    "(UINT16) - Unsigned Integer 16");
            put( TRACE.TC_PARAM_TYPE_UINT32,    "(UINT32) - Unsigned Integer 32");
            put( TRACE.TC_PARAM_TYPE_VAR,       "(PVAR) - Variable Data Parameter");
            put( TRACE.TC_PARAM_TYPE_STRING,    "(PSTRING) - String Parameter");
        }
    };
    
    public TraceCommandViewController(){
        this.tempParameterModel =   new CustomListModelHandler();
    }
    
    public void setModel(MainEditorModel model){
        if(this.model == null){
            this.model = model;
        }
    }
    
    public void setView(WindowTraceCommandConfig view){
        if(this.view == null){
            this.view = view;
            this.view.setLocationRelativeTo(null);
        }
    }
    
    public CustomListModelHandler<ParameterTraceCommand> getParameterList(){
        return this.tempParameterModel;
    }
    
    public boolean createTraceCommandParameter(){
        
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
                    ParameterTraceCommand tcp = this.createParameter(
                        parameterName, 
                        parameterType);

                    //
                    if (tcp != null){
                        Logger.getGlobal().info(
                            String.format("[Controller] New valid Parameter (Trace Command) created: \"%s\"", tcp));
                        parameterCreatedFlag = true;

                        //
                        this.tempParameterModel.addObjectToList(tcp);
                        this.view.update(ConfigWindowEvents.UPDATE_PARAMETER_LIST);
                        
                        //
                        this.view.setCurrentTraceCommandType(this.getCurrentTypeFromParameterList());
                        this.view.update(ConfigWindowEvents.UPDATE_CURRENT_TRACE_COMMAND_TYPE_LABEL);
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
    
    private String getCurrentTypeFromParameterList(){
        String type = null;
        if(!this.getParameterList().isEmpty()) {
            for( ParameterTraceCommand param : this.getParameterList() ){
                if((param.getType().equals(TRACE.TC_PARAM_TYPE_STRING)) ||
                   (param.getType().equals(TRACE.TC_PARAM_TYPE_VAR))){
                    type = TRACE.TC_TYPE_DATA16;
                }
            }
            if(type == null){
                type = TRACE.TC_TYPE_DATAFIX;
            }
        }
        else{
            type = TRACE.TC_TYPE_EVENT;
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
    
    public boolean addTraceCommandData(
        String TraceCommandName,
        String TraceCommandLongName
    ) {
        boolean itemAddedFlag = false;
        TraceCommand tc = this.createTraceCommand(
            TraceCommandName, 
            TraceCommandName);
        if (tc != null){
            Logger.getGlobal().info(
                String.format("[Controller] New valid Trace Command created: \"%s\"", tc));
            //
            this.addCurrentParameterListToTraceObject(tc);
            tc.validateTypeFromParameters();
            itemAddedFlag = this.model.addItem(tc);
        }
        return itemAddedFlag;
    }
    
    private TraceCommand createTraceCommand(
        String name,
        String longName
    ){
        TraceCommand newTraceCommand = null;
        // parameter validations here:
        
        
        // validations end
        newTraceCommand = new TraceCommand(
            name,
            null,
            longName
        );
        return newTraceCommand;
    }
    
    private void addCurrentParameterListToTraceObject(TraceCommand traceCommandObj){
        for ( ParameterTraceCommand param : this.tempParameterModel.getObjectArrayList() ) {
            traceCommandObj.addParameter(param);
        }
        this.tempParameterModel.clearList();
    }
    
    private ParameterTraceCommand createParameter(
        String name,
        String type
    ){
        ParameterTraceCommand newTraceCommandParameter = null;
        // parameter validations here:
        
        // validations end
        newTraceCommandParameter = new ParameterTraceCommand(
            name,
            type
        );
        return newTraceCommandParameter;
    }
    
    public void openTraceCommandWindow(){
        this.view.setCurrentTraceCommandType(TRACE.TC_TYPE_EVENT);
        this.view.resetWindowToDefaultState();
        this.view.setVisible(true);
    }
    
}
