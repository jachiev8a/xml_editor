/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace.parameters;

import model.trace.TraceObjectBaseClass;

/**
 *
 * @author Jachiev
 */
public class Parameter extends TraceObjectBaseClass {
    
    public Parameter(String name, String type){
        super(name, type);
    }
    
    @Override
    public String toString(){
        String stringRepr;
        stringRepr = String.format(
            " Parameter: \'%s\' | %s | VALUES: 0",
            this.getName(),
            this.getType());
        return stringRepr;
    }
    
}
