/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TracePointViewController;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Logger;
import model.trace.TracePoint;
import utils.ConfigWindowEvents;
import view.handler.VisualComponentHandler;

/**
 *
 * @author uidj5418
 */
public class WindowTracePointConfig extends javax.swing.JFrame {
  
    /**
     * Creates new form windowTracePointConfig
     */
    
    private final TracePointViewController      controller;
    private final String                        nameDefaultText;
    private       String                        currentTracePointType;
    private       ArrayList<String>             currentPriorityClasses;
    
    public WindowTracePointConfig(TracePointViewController controller) {
        this.lookAndFeelSetup();
        this.initComponents();
        this.controller = controller;
        this.currentPriorityClasses = new ArrayList();
        nameDefaultText = "Type your Trace Point name here...";
    }

    /**
     * Configure "Look And Feel Like Settings"
     */
    public void lookAndFeelSetup() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelButtons = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jPanelParam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListParameterList = new javax.swing.JList<String>();
        jButtonAddParameter = new javax.swing.JButton();
        jButtonEditParameter = new javax.swing.JButton();
        jButtonDeleteParameter = new javax.swing.JButton();
        jPanelBasic = new javax.swing.JPanel();
        jTextFieldName = new javax.swing.JTextField();
        jComboBoxSevLev = new javax.swing.JComboBox<String>();
        jComboBoxPriorityClass = new javax.swing.JComboBox<String>();
        jComboBoxEncoding = new javax.swing.JComboBox<String>();
        jComboBoxConfigType = new javax.swing.JComboBox<String>();
        jLabelSvtLvl = new javax.swing.JLabel();
        jLabelPrioClass = new javax.swing.JLabel();
        jLabelConfigType = new javax.swing.JLabel();
        jLabelEncoding = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jLabelTracePointType = new javax.swing.JLabel();

        setTitle("Trace Point Configuration Window");

        jPanelButtons.setName("PanelButtons"); // NOI18N

        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonExit.setText("EXIT");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelParam.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        jScrollPane1.setViewportView(jListParameterList);

        jButtonAddParameter.setText("Add");
        jButtonAddParameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddParameterActionPerformed(evt);
            }
        });

        jButtonEditParameter.setText("Edit");

        jButtonDeleteParameter.setText("Delete");

        javax.swing.GroupLayout jPanelParamLayout = new javax.swing.GroupLayout(jPanelParam);
        jPanelParam.setLayout(jPanelParamLayout);
        jPanelParamLayout.setHorizontalGroup(
            jPanelParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelParamLayout.createSequentialGroup()
                        .addComponent(jButtonAddParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEditParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDeleteParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelParamLayout.setVerticalGroup(
            jPanelParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelParamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddParameter)
                    .addComponent(jButtonEditParameter)
                    .addComponent(jButtonDeleteParameter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jTextFieldName.setText("Insert the Trace Point name here...");
        jTextFieldName.setToolTipText("Name");
        jTextFieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusGained(evt);
            }
        });

        jComboBoxSevLev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Info", "Warning", "Error", "Fatal", "DebugRaw", "DebugFine", "Test" }));
        jComboBoxSevLev.setToolTipText("Severity Level");
        jComboBoxSevLev.setName("SeverityLevel"); // NOI18N

        jComboBoxPriorityClass.setToolTipText("Priority Class");

        jComboBoxEncoding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hex", "bin", "dec", "ascii" }));
        jComboBoxEncoding.setToolTipText("Encoding");

        jComboBoxConfigType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Static", "Static Level", "Dynamic", "DynSwitchDefaultOn", "DynSwitchDefaultOff" }));
        jComboBoxConfigType.setToolTipText("ConfigType");

        jLabelSvtLvl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSvtLvl.setText("Severity Level :");

        jLabelPrioClass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPrioClass.setText("Priority Class :");

        jLabelConfigType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelConfigType.setText("Configuration Type :");

        jLabelEncoding.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelEncoding.setText("Encoding :");

        jLabelType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelType.setText("Type :");

        jLabelTracePointType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTracePointType.setText("-");

        javax.swing.GroupLayout jPanelBasicLayout = new javax.swing.GroupLayout(jPanelBasic);
        jPanelBasic.setLayout(jPanelBasicLayout);
        jPanelBasicLayout.setHorizontalGroup(
            jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBasicLayout.createSequentialGroup()
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBasicLayout.createSequentialGroup()
                        .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelConfigType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jLabelEncoding, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSvtLvl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelPrioClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSevLev, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxConfigType, 0, 159, Short.MAX_VALUE)
                            .addComponent(jComboBoxPriorityClass, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxEncoding, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTracePointType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelBasicLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldName)))
                .addContainerGap())
        );
        jPanelBasicLayout.setVerticalGroup(
            jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBasicLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSevLev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSvtLvl))
                .addGap(12, 12, 12)
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPriorityClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrioClass))
                .addGap(12, 12, 12)
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxConfigType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelConfigType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEncoding))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBasicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jLabelTracePointType))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBasic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBasic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelParam, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        String tpName =             jTextFieldName.getText();
        String tpSeverityLevel =    jComboBoxSevLev.getSelectedItem().toString();
        String tpConfigType =       jComboBoxConfigType.getSelectedItem().toString();
        String tpPriorityClass =    jComboBoxPriorityClass.getSelectedItem().toString();
        String tpEncoding =         jComboBoxEncoding.getSelectedItem().toString();
        Logger.getGlobal().info(
            String.format("[View] Trace Point data to be added: \"%s\"", tpName));
        //
        if (this.controller.addTracePointData(
            tpName,
            null,
            tpSeverityLevel,
            tpConfigType,
            tpPriorityClass,
            tpEncoding,
            0)) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        this.jTextFieldName.selectAll();
    }//GEN-LAST:event_jTextFieldNameFocusGained

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonAddParameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddParameterActionPerformed
        this.controller.createTracePointParameter();
    }//GEN-LAST:event_jButtonAddParameterActionPerformed

    public void clearAllPriorityClassValues(){
        this.currentPriorityClasses.clear();
    }
    
    public void addPriorityClassValue(String priorityClassValue){
        this.currentPriorityClasses.add(priorityClassValue);
    }
    
    public void setCurrentTracePointType(String type){
        this.currentTracePointType = type;
        this.update(ConfigWindowEvents.UPDATE_CURRENT_TRACE_POINT_TYPE_LABEL);
    }
    
    public void resetWindowToDefaultState() {
        this.jTextFieldName.setText(this.nameDefaultText);
        this.jTextFieldName.requestFocus();
        
        this.jComboBoxSevLev.setSelectedIndex(0);
        this.jComboBoxConfigType.setSelectedIndex(0);
        this.jComboBoxEncoding.setSelectedIndex(0);
    }
    
    public void update(String event) {
        switch(event){
            case ConfigWindowEvents.UPDATE_PARAMETER_LIST:
                Logger.getGlobal().info(
                    String.format("[View] Event update: \"%s\"", event));
                this.jListParameterList.setModel(
                        this.controller.getParameterList());
                break;
            case ConfigWindowEvents.UPDATE_CURRENT_TRACE_POINT_TYPE_LABEL:
                Logger.getGlobal().info(
                    String.format("[View] Event update: \"%s\"", event));
                this.jLabelTracePointType.setText(this.currentTracePointType);
                break;
            case ConfigWindowEvents.ENABLE_PARAMETER_CONTROLS:
                //
                Logger.getGlobal().info(
                    String.format("[View] Event update: \"%s\"", event));
                this.jButtonAddParameter.setEnabled(true);
                this.jButtonEditParameter.setEnabled(true);
                this.jButtonDeleteParameter.setEnabled(true);
                this.jListParameterList.setEnabled(true);
                break;
            case ConfigWindowEvents.DISABLE_PARAMETER_CONTROLS:
                //
                Logger.getGlobal().info(
                    String.format("[View] Event update: \"%s\"", event));
                this.jButtonAddParameter.setEnabled(false);
                this.jButtonEditParameter.setEnabled(false);
                this.jButtonDeleteParameter.setEnabled(false);
                this.jListParameterList.setEnabled(false);
                break;
            case ConfigWindowEvents.UPDATE_CURRENT_PRIORITY_CLASS_VALUES:
                //
                Logger.getGlobal().info(
                    String.format("[View] Event update: \"%s\"", event));
                this.jComboBoxPriorityClass.removeAllItems();
                for( String pcValue : this.currentPriorityClasses ){
                    this.jComboBoxPriorityClass.addItem(pcValue);
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowTracePointConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowTracePointConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowTracePointConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowTracePointConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddParameter;
    private javax.swing.JButton jButtonDeleteParameter;
    private javax.swing.JButton jButtonEditParameter;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JComboBox<String> jComboBoxConfigType;
    private javax.swing.JComboBox<String> jComboBoxEncoding;
    private javax.swing.JComboBox<String> jComboBoxPriorityClass;
    private javax.swing.JComboBox<String> jComboBoxSevLev;
    private javax.swing.JLabel jLabelConfigType;
    private javax.swing.JLabel jLabelEncoding;
    private javax.swing.JLabel jLabelPrioClass;
    private javax.swing.JLabel jLabelSvtLvl;
    private javax.swing.JLabel jLabelTracePointType;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JList<String> jListParameterList;
    private javax.swing.JPanel jPanelBasic;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelParam;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
