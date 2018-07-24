/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Logger;
import model.MainEditorModel;
import model.trace.PriorityClass;
import view.WindowPriorityClassConfig;

/**
 *
 * @author uidj5418
 */
public class PriorityClassViewController {
        
    private MainEditorModel                 model = null;
    private WindowPriorityClassConfig       view = null;
    
    public PriorityClassViewController(){
    }
    
    public void setModel(MainEditorModel model){
        if(this.model == null){
            this.model = model;
        }
    }
    
    public void setView(WindowPriorityClassConfig view){
        if(this.view == null){
            this.view = view;
            this.view.setLocationRelativeTo(null);
        }
    }
    
    public boolean addPriorityClassData(
        String priorityClassName,
        String priorityClassId
    ) {
        boolean itemAddedFlag = false;
        PriorityClass pc = this.createPriorityClass(
            priorityClassName,
            priorityClassId);
        if (pc != null){
            Logger.getGlobal().info(
                String.format("[Controller] New valid Priority Class created: \"%s\"", pc));
            itemAddedFlag = this.model.addItem(pc);
        }
        return itemAddedFlag;
    }
    
    private PriorityClass createPriorityClass(
        String name,
        String id
    ) {
        PriorityClass newPriorityClass = null;
        // parameter validations here:
        
        // validations end
        newPriorityClass = new PriorityClass(
            name,
            id
        );
        return newPriorityClass;
    }
    
    public void openPriorityClassWindow(){
        this.view.resetWindowToDefaultState();
        this.view.setVisible(true);
    }
    
}
