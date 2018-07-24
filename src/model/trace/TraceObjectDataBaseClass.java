/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

import model.trace.parameters.Parameter;
import java.util.ArrayList;

/**
 *
 * @author uidj5418
 */
public class TraceObjectDataBaseClass extends TraceObjectBaseClass {
    
    private ArrayList<Parameter>    parameterList;
    
    public TraceObjectDataBaseClass(String name, String type){
        super(name, type);
        this.parameterList = new ArrayList<>();
    }
    
    public TraceObjectDataBaseClass(String name){
        super(name);
        this.parameterList = new ArrayList<>();
    }
    
    public void addParameter(Parameter param){
        this.parameterList.add(param);
    }
    
    public ArrayList<Parameter> getParameterList(){
        return this.parameterList;
    }
    
    public boolean hasParameters() {
        if(this.parameterList.isEmpty()){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String stringRepr;
        stringRepr = String.format(
            " \'%s\' | %s | Parameters (%d)",
            this.getName(),
            this.getType(),
            this.parameterList.size());

        return stringRepr;
    }
    
}
