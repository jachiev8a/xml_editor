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
public class ParameterVarLen extends ParameterTracePoint {
    
    public ParameterVarLen(String name) {
        super(name, TRACE.TP_PARAM_TYPE_VAR);
        this.setDataType(TRACE.TP_PARAM_DATA_TYPE_VOID_POINTER);
    }
    
}
