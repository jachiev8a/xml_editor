package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.MainEditorController;
import controller.PriorityClassViewController;
import controller.TraceCommandViewController;
import controller.TracePointViewController;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MainEditorModel;
import utils.events.EventEnableAllComponentControls;
import view.MainEditorFrame;
import view.WindowPriorityClassConfig;
import view.WindowTraceCommandConfig;
import view.WindowTracePointConfig;

/**
 *
 * @author uidj5418
 */
public class TraceEditor {
    public static void main( String args [] ){
        
        Logger.getGlobal().setLevel(Level.INFO);
        
        MainEditorController    editorController =  new MainEditorController();        
        MainEditorModel         editorModel =       new MainEditorModel();
        MainEditorFrame         TraceEditorFrame =  new MainEditorFrame(editorController, editorModel);
        
        editorController.setView(TraceEditorFrame);
        
        editorModel.addObserver(editorController);
        
        //
        // Partner Controllers
        //
        TraceCommandViewController traceCommandViewController =
                new TraceCommandViewController();
        
        editorController.setPartnerController(traceCommandViewController);
        
        WindowTraceCommandConfig traceCommandView = new WindowTraceCommandConfig(traceCommandViewController);
        
        traceCommandViewController.setView(traceCommandView);
        traceCommandViewController.setModel(editorModel);
        
        //
        //
        //
        
        TracePointViewController tracePointViewController =
                new TracePointViewController();
        
        editorController.setPartnerController(tracePointViewController);
        
        WindowTracePointConfig tracePointView = new WindowTracePointConfig(tracePointViewController);
        
        tracePointViewController.setView(tracePointView);
        tracePointViewController.setModel(editorModel);
        //
        //
        //
        
        PriorityClassViewController priorityClassViewController =
                new PriorityClassViewController();
        
        editorController.setPartnerController(priorityClassViewController);
        
        WindowPriorityClassConfig priorityClassView = new WindowPriorityClassConfig(priorityClassViewController);
        
        priorityClassViewController.setView(priorityClassView);
        priorityClassViewController.setModel(editorModel);
        //
        //
        //
        
        // ------------------------------------------------------
        // CODE FOR TESTING ONLY (PLEASE ERASE THIS)
        // ------------------------------------------------------
            //TraceEditorFrame.update( new EventEnableAllComponentControls() );
        // ------------------------------------------------------
        // END OF TEST CODE
        // ------------------------------------------------------
        
        TraceEditorFrame.setLocationRelativeTo(null);
        TraceEditorFrame.setVisible(true);
    }
}
