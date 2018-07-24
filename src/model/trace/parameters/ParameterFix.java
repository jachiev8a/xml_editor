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
public class ParameterFix extends ParameterTracePoint {

    private String length;
    
    public ParameterFix(String name) {
        super(name, TRACE.TP_PARAM_TYPE_FIX);
        this.length = null;
    }
    
    public String getLength() {
        return this.length;
    }

    public void setLength(String length) {
        this.length = length;
    }
    
}
