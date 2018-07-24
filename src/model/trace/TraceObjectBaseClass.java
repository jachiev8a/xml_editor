/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.trace;

import java.util.ArrayList;

/**
 *
 * @author uidj5418
 */
public class TraceObjectBaseClass {
    
    private String                  name;
    private String                  type;
    private String                  doc;
    
    public TraceObjectBaseClass(String name, String type){
        this.name = name;
        this.type = type;
        this.doc = null;
    }
    
    public TraceObjectBaseClass(String name){
        this.name = name;
        this.type = null;
        this.doc = null;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getDoc() {
        return this.doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    @Override
    public String toString(){
        String stringRepr;
        stringRepr = String.format(
            " \'%s\' |",
            this.getName());
        return stringRepr;
    }
    
//    @Override
//    public String toString() {
//        String stringRepr;
//        // if there is only one parameter, print 'parameter' text
//        if(this.parameterList.size() == 1){
//            stringRepr = String.format(
//                " \'%s\' | %s | %d parameter",
//                this.getName(),
//                this.getType(),
//                this.parameterList.size());
//        }
//        // print 'parameters' in plural
//        else{
//            stringRepr = String.format(
//                " \'%s\' | %s | %d parameters",
//                this.getName(),
//                this.getType(),
//                this.parameterList.size());
//        }
//        return stringRepr;
//    }
    
    
    
}
