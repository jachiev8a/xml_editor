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
public class ParameterInt8 extends ParameterFix {

    public ParameterInt8(String name) {
        super(name);
        this.setType(TRACE.TP_PARAM_TYPE_INT8);
        this.setLength(TRACE.TP_PARAM_8_LENGTH);
    }
    
}
