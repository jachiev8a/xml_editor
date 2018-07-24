/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author uidj5418
 */
public class DropListener extends Observable implements DropTargetListener {

    @Override
    public void dragEnter(DropTargetDragEvent evt) {
        Logger.getGlobal().info("[Controller] A File has been detected inside dropping area");
        this.notifyObservers(evt);
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragExit(DropTargetEvent evt) {
        Logger.getGlobal().info("[Controller] A File has been has left the dropping area");
        this.notifyObservers(evt);
    }

    @Override
    public void drop(DropTargetDropEvent evt) {
        
        File fileDropped = null;
        
        // abstract dropped items
        Transferable tranferableObject = evt.getTransferable();
        
        // get all data flavors that could be dropped
        DataFlavor[] flavorsTransfered = tranferableObject.getTransferDataFlavors();
        
        // go through all data flavors looking for the tcm files
        for (DataFlavor x : flavorsTransfered){
            try{
                // check if dropped item is a file
                if(x.isFlavorJavaFileListType()){
                    
                    // Accept dropped items
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    
                    // Look for the file name that was dropped as a file
                    List droppedObjectList = (List) tranferableObject.getTransferData(x);
                    for (int j = 0; j < droppedObjectList.size(); j++) {
                        
                        // Get dropped file name
                        fileDropped = new File(droppedObjectList.get(j).toString());                        
                        Logger.getGlobal().info(
                            String.format("[Controller] A file has been dropped: \"%s\"",fileDropped));
                        
                        // notify all observers about the new file dropped
                        this.notifyObservers(fileDropped);
                    }
                }
            } 
            catch(Exception ex){
                Logger.getGlobal().warning(
                    String.format("[Controller] File dropped error: \'%s\'", ex));
            }
        }
        
    }
    
}
