/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Players;
import database.DataDealer;
import gubas.forms.Dialog;
import gubas.forms.DialogForm;
import gubas.javaapplication1.FormsCaller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Przemo
 */
public class UpdateChecker extends DialogForm {
    boolean up = false;
    final UpdateChecker me = this;
    
    public UpdateChecker(){
        super( Dialog.OK, "Checking data status");
        this.setPreferredSize(new Dimension(250,180));
            new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        up = checkUpdates("", (int) new DataDealer().rowsCount(Players.class)).equals("OK");//The class name parameter should be extended, so that every table should be checked
                        me.updateCallback();
                    } catch (ConnectException ex) {
                        //Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
                        me.setErrorMessage(ex.getMessage());
                    }
                }
        
            }).start();

    }
    
    void setErrorMessage(String mssg){
        this.addMessage(mssg);
    }
    
    void updateCallback(){
        if(up){ 
            this.addMessage("Status OK");
        } else {
            this.addMessage("Update required");
        } 
        me.repaint();
        me.revalidate();
    }
    
    private static String checkUpdates(String lastDate, int noOfRecords) throws java.net.ConnectException {
        start.DataUpdate_Service service = new start.DataUpdate_Service();
        start.DataUpdate port = service.getDataUpdatePort();
        return port.checkUpdates(lastDate, noOfRecords);
    }
}
