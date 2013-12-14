/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Players;
import database.DataDealer;
import gubas.forms.Dialog;
import gubas.forms.DialogForm;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemo
 */
public class UpdateChecker extends DialogForm {
    
    public UpdateChecker(){
        super( Dialog.OK, "Updates Checker");
        try {
            Thread.currentThread().wait(250);
            if(checkUpdates("", (int) new DataDealer().rowsCount(Players.class)).equals("OK")){
                this.dispose();
            } //The class name parameter should be extended, so that every table should be checked
        } catch (InterruptedException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String checkUpdates(String lastDate, int noOfRecords) {
        start.DataUpdate_Service service = new start.DataUpdate_Service();
        start.DataUpdate port = service.getDataUpdatePort();
        System.out.println(port.checkUpdates(lastDate, noOfRecords));
        return port.checkUpdates(lastDate, noOfRecords);
    }
}
