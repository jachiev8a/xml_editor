/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace.parameters;

/**
 *
 * @author uidj5418
 */
public class ParameterTracePoint extends Parameter implements Comparable<Parameter>{

    private String longName;
    private String encoding;
    private String dataType;
    private String filter;
    private String mask;
    
    public ParameterTracePoint(String name, String type) {
        super(name, type);
        this.longName = "";
    }
    
    public String getLongName() {
        return this.longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
    
    public String getEncoding(){
        return this.encoding;
    }
    
    public void setEncoding(String encoding){
        this.encoding = encoding;
    }
    
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    @Override
    public int compareTo(Parameter p) {
        int hash = this.getName().compareTo(p.getName());
        return hash;
    }
    
}
