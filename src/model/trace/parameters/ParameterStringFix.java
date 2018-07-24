/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace.parameters;

import model.trace.TRACE;

/**
 *
 * @author uidj5418
 */
public class ParameterStringFix extends ParameterTracePoint {

    private String stringValue;
    
    public ParameterStringFix(String name) {
        super(name, TRACE.TP_PARAM_TYPE_STRINGFIX);
        this.stringValue = "";
    }
    
    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    
}
