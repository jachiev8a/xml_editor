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
public class ParameterUint16 extends ParameterFix {

    public ParameterUint16(String name) {
        super(name);
        this.setType(TRACE.TP_PARAM_TYPE_UINT16);
        this.setLength(TRACE.TP_PARAM_16_LENGTH);
    }
    
}
