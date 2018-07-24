/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

import model.trace.parameters.ParameterTraceCommand;
import model.trace.parameters.Parameter;
import java.util.logging.Logger;
import model.trace.interfaces.ParameterContainer;

/**
 *
 * @author uidj5418
 */
public class TraceCommand extends TraceObjectDataBaseClass {
    
    private String    longName;
    
    /**
    * Constructor.
    * 
    * @param name
    * @param type
    */
    public TraceCommand(String name, String type){
        super(name, type);
        this.longName = "";
        Logger.getGlobal().fine(
            String.format("[Model] New Trace Command created: \"%s\"", name));
    }
    
    /**
    * Full Constructor.
    * 
    * @param name
    * @param type
    * @param longName
    */ 
    public TraceCommand(String name, String type, String longName){
        super(name, type);
        this.longName = longName;
        Logger.getGlobal().info(String.format("New Trace Command created: %s :: %s", name, type));
    }

    /**
     * @return the longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName the longName to set
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }
    
    public void validateTypeFromParameters(){
        String type = null;
        if(this.hasParameters()){
            for( Parameter param : this.getParameterList() ){
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
        this.setType(type);
    }
    
    public void addParameter(ParameterTraceCommand param){
        super.addParameter(param);
    }
    
}
