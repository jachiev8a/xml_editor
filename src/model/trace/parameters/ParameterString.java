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
public class ParameterString extends ParameterTracePoint {
    
    public ParameterString(String name) {
        super(name, TRACE.TP_PARAM_TYPE_STRING);
        this.setEncoding(TRACE.TP_ENCODING_ASCII);
    }
    
}
