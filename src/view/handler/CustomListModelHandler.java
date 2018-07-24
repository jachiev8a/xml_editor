/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.handler;

import javax.swing.AbstractListModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jachiev
 */
public class CustomListModelHandler<ObjectListClass> extends AbstractListModel implements Iterable<ObjectListClass>{
 
    private ArrayList<ObjectListClass> objectList = new ArrayList<ObjectListClass>();
    
    public void cloneFromListReference(ArrayList objectListToClone){
        this.clearList();
        this.objectList.addAll(objectListToClone);
        this.fireContentsChanged(this, this.getSize(), this.getSize()+1);
    }
    
    public void clearList(){
        this.objectList.clear();
    }
    
    public boolean isEmpty(){
        if(this.objectList.isEmpty()){
            return true;
        }
        return false;
    }
    
    @Override
    public int getSize() {
        return objectList.size();
    }
 
    @Override
    public ObjectListClass getElementAt(int index) {
        ObjectListClass object = objectList.get(index);
        return object;
    }
    
    public void addObjectToList(ObjectListClass obj){
        objectList.add(obj);
        this.fireIntervalAdded(this, this.getSize(), this.getSize()+1);
    }
    
    public boolean removeObjectFromList(int selectedIndex){
        if(selectedIndex != -1){
            objectList.remove(selectedIndex);
            this.fireIntervalRemoved(selectedIndex, this.getSize(), this.getSize()+1);
            return true;
        }
        else{
            return false;
        }
    }
    
    public Object getObject(int index){
        return objectList.get(index);
    }
    
    public ArrayList<ObjectListClass> getObjectArrayList(){
        return this.objectList;
    }

    @Override
    public Iterator<ObjectListClass> iterator() {
        Iterator<ObjectListClass> iterator = this.objectList.iterator();
        return iterator; 
    }
    
}
