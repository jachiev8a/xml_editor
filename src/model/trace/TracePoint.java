/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

import model.trace.parameters.ParameterTracePoint;
import model.trace.parameters.Parameter;
import java.util.logging.Logger;
import model.trace.interfaces.ParameterContainer;

/**
 *
 * @author uidj5418
 */
public class TracePoint extends TraceObjectDataBaseClass {
    
    private String    id;
    private String    severityLevel;
    private String    configType;
    private String    prioClass;
    private String    encoding;
    private Integer   length;
    
    
    
    /**
    * Constructor.
    * 
    * @param name
    * @param type
    */
    public TracePoint(String name, String type){
        super(name, type);
        this.severityLevel = "Info";
        this.configType = TRACE.TP_CONFIG_TYPE_DYNAMIC_SWITCH_DEFAULT_OFF;
        this.prioClass = "";
        this.encoding = TRACE.TP_ENCODING_HEX;
        this.length = 0;
        Logger.getGlobal().fine(
            String.format("[Model] New Trace Point created: \"%s\"", name));
    }
    
    /**
    * Full Constructor.
    * 
    * @param name
    * @param type
    * @param severityLevel
    * @param configType
    * @param prioClass
    * @param encoding
    * @param length
    */    
    public TracePoint(
            String name,
            String type,
            String severityLevel,
            String configType,
            String prioClass,
            String encoding,
            int    length
            ){
        super(name, type);
        this.severityLevel = severityLevel;
        this.configType = configType;
        this.prioClass = prioClass;
        this.encoding = encoding;
        this.length = length;
        Logger.getGlobal().fine(
            String.format("[Model] New Trace Point created: \"%s\"", name));
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the severityLevel
     */
    public String getSeverityLevel() {
        return severityLevel;
    }

    /**
     * @param severityLevel the severityLevel to set
     */
    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    /**
     * @return the configType
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * @param configType the configType to set
     */
    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * @return the prioClass
     */
    public String getPrioClass() {
        return prioClass;
    }

    /**
     * @param prioClass the prioClass to set
     */
    public void setPrioClass(String prioClass) {
        this.prioClass = prioClass;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }
    
    public void validateTypeFromParameters(){
        String type = null;
        if(this.hasParameters()){
            for( Parameter param : this.getParameterList() ){
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
        this.setType(type);
    }
    
    public void addParameter(ParameterTracePoint param){
        super.addParameter(param);
    }
    
}
