/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

import java.util.logging.Logger;

/**
 *
 * @author uidj5418
 */
public class PriorityClass extends TraceObjectBaseClass{
    
    private String value;
    
    public PriorityClass(String name, String value){
        super(name);
        this.value = value;
        Logger.getGlobal().fine(
            String.format("[Model] New Priority Class created: \"%s\"", name));
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        String stringRepr;
        stringRepr = String.format(
            " \'%s\' | %s |",
            this.getName(),
            this.getValue());
        return stringRepr;
    }
}
