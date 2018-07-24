/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.model;

import java.util.ArrayList;
import java.util.logging.Logger;
import utils.TcmParser;
import model.MainEditorModel;
import model.trace.PriorityClass;
import model.trace.TraceCommand;
import model.trace.TracePoint;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jachiev
 */
public class AcceptanceTestTcmParser {
    public static void main( String args [] ){
        ArrayList<TracePoint> myTracePoints = null;
        ArrayList<TraceCommand> myTraceCommands = null;
        ArrayList<PriorityClass> myPriorityClasses = null;
        
        String TcmFilePath="D:\\CASDEV\\JGP2014\\Repo_1\\pkg\\midware\\pkg\\dpool\\adapt\\dpool.tcm";
        
        // Parse TCM file
        TcmParser parser = new TcmParser();
        NodeList tcmNodes = parser.parseFile(TcmFilePath);
        
        // Create Model from node list
        MainEditorModel modelRef = new MainEditorModel();
        modelRef.setModel(tcmNodes);
        
        // Print all TracePoints from TCM
        myTracePoints = modelRef.getTracePointsArrayList();
        Logger.getGlobal().info(String.format("Trace Points"));
        for (TracePoint tp : myTracePoints)
        {
            Logger.getGlobal().info(String.format("Name: %s  Type: %s", tp.getName(), tp.getType()));
        }
        
        // Print all TraceCommands from TCM
        myTraceCommands = modelRef.getTraceCommandsArrayList();
        Logger.getGlobal().info(String.format("Trace Commands"));
        for (TraceCommand tc : myTraceCommands)
        {
            Logger.getGlobal().info(String.format("Name: %s  Type: %s", tc.getName(), tc.getType()));
        }
        
        // Print all PriorityClasses from TCM
        myPriorityClasses = modelRef.getPriorityClassesArrayList();
        Logger.getGlobal().info(String.format("PriorityClasses"));
        for (PriorityClass pc : myPriorityClasses)
        {
            Logger.getGlobal().info(String.format("Name: %s  Value: %s", pc.getName(), pc.getValue()));
        }
        
    }
}
