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
public class ParameterTraceCommand extends Parameter implements Comparable<Parameter> {

    private String longName;
    
    public ParameterTraceCommand(String name, String type) {
        super(name, type);
        this.longName = "";
    }
    
    public String getLongName() {
        return this.longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public int compareTo(Parameter p) {
        int hash = this.getName().compareTo(p.getName());
        return hash;
    }
    
}
