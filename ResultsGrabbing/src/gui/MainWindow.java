/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import data.Players;
import gubas.forms.*;
import gubas.javaapplication1.FormsCaller;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *
 * @author Przemo
 */
public class MainWindow extends MenuContainingForm {
    
    private static final String refreshDataActionCommands ="refreshData", exitActionCommand = "exit";
    private static final String statisticsActionCommand = "statistics";
    
    private static final String[] editMenuItemNames = new String[]{"Players", "Matches", "Games"};
    private static final String[] editCommands = new String[]{"editPlayers", "editMatches", "editGames"};
    private static final String helpActionCommand="help";
    
    @Override
    protected JMenuBar createMenuBar(){
        // File->Refresh Data, Exit, Edit->Players,Matches,Games, Statistics, Help
        super.myMenuBar = new JMenuBar();
        
        JMenu file = new JMenu("File");
        JMenuItem refreshData = new JMenuItem("Refresh Data");
        refreshData.setActionCommand(refreshDataActionCommands);
        refreshData.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand(exitActionCommand);
        exit.addActionListener(this);
        file.add(refreshData);
        file.add(exit);
        
        //Edit
        JMenu edit = new JMenu("Edit");
        for(int i=0; i<editMenuItemNames.length; i++){
            JMenuItem p = new JMenuItem(editMenuItemNames[i]);
            p.setActionCommand(editCommands[i]);
            p.addActionListener(this);
            edit.add(p);
        }
        
        //Statistics
        JMenu statistics = new JMenu("Statistics");
        statistics.setActionCommand(statisticsActionCommand);
        statistics.addActionListener(this);
        
        //Help
        JMenu help = new JMenu("Help");
        help.setActionCommand(helpActionCommand);
        help.addActionListener(this);
        
        myMenuBar.add(file);
        myMenuBar.add(edit);
        myMenuBar.add(statistics);
        myMenuBar.add(help);
        
        return myMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(exitActionCommand)){
            this.dispose();
        } else if(e.getActionCommand().equals(editCommands[0])) {
            FormsCaller.callNewWindow("Edit", new EditForm(Players.class));
        }
        
    }
}
