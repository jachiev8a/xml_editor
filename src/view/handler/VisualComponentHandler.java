/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.handler;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author uidj5418
 */
public class VisualComponentHandler {
    
    private final String RE_TCM_EXTENSION_PATTERN = "[\\w\\d\\-\\_\\=]*\\.tcm";
    
    public void setTextInTextFieldComponent(JTextField component, String text){
        component.setText(text);
    }
    
    public void copyTextFieldComponentContentToClipboard(JTextField component){
        StringSelection stringSelection = new StringSelection(component.getText());
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }
    
    public boolean showAcceptCancelDialog(JFrame mainFrameComponent, String message){
        int option = JOptionPane.showConfirmDialog(
            mainFrameComponent,
            message,
            "Warning",
            JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return true;
        }
        // NO_OPTION Selected
        return false;
    }
    
    public String chooseTcmFileFromWindowsExplorer(JFrame mainFrameComponent){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "TCM Files (.tcm)", "tcm");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(mainFrameComponent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            Logger.getGlobal().info(String.format(
                "TCM File chosen: \"%s\"",
                fileChooser.getSelectedFile().getAbsolutePath()));
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    
    public String chooseTcmFileDestinationFromWindowsExplorer(
            JFrame mainFrameComponent,
            String tcmFileName){
        
        String tcmFullFileName = null;
        
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle(
            "Choose directory to save TCM file: " + tcmFileName + ".tcm");
        
        fileChooser.setFileFilter(new FileNameExtensionFilter("TCM File", "tcm"));
        fileChooser.setSelectedFile(new File(tcmFileName+".tcm"));

        int userSelection = fileChooser.showSaveDialog(mainFrameComponent);

        if (userSelection != JFileChooser.CANCEL_OPTION) {
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                tcmFullFileName = fileChooser.getSelectedFile().getAbsoluteFile().toString();
                // RE to find TCM extension in the file name
                Pattern pattern = Pattern.compile(this.RE_TCM_EXTENSION_PATTERN);
                Matcher matcher = pattern.matcher(tcmFullFileName);
                // 
                if (!matcher.find()) {
                    tcmFullFileName = tcmFullFileName + ".tcm";
                }
                Logger.getGlobal().info(
                    String.format("[Controller] TCM file path to save: \'%s\'", tcmFullFileName));
            }
        }
        return tcmFullFileName;
    }
    
    public String chooseComponentName(JFrame mainFrameComponent){
        
        String tcmFileName = (String)JOptionPane.showInputDialog(  
            mainFrameComponent,
            "Type your Component Name",
            "New Component",
            JOptionPane.QUESTION_MESSAGE);
        
        if (tcmFileName != null){
            if (tcmFileName.trim().isEmpty()){
                JOptionPane.showMessageDialog(
                    mainFrameComponent,
                    "ERROR: No Data was inserted",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                tcmFileName = null;
            }
        }
        return tcmFileName;
    }
    
}
