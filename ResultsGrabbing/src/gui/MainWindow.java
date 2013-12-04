/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import data.Games;
import data.Matches;
import data.Players;
import gubas.forms.*;
import gubas.javaapplication1.FormsCaller;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import logsreader.logsreader.LogsReader;
/**
 *
 * @author Przemo
 */
public class MainWindow extends MenuContainingForm {
    
    private static final String refreshDataActionCommands ="refreshData", showLogActionCommand="showLog", exitActionCommand = "exit";
    private static final String statisticsPlayerActionCommand = "playerStats";
    
    private static final Map<String, Class<?>> editStringClassMap = new HashMap<String, Class<?>>(){
        {put("Players", Players.class);
        put("Matches", Matches.class);
        put("Games", Games.class);
        }
    };
    
    private static final Map<String, String> statisticsMenuMap = new HashMap<String, String>(){
        {put("Player", statisticsPlayerActionCommand);
        put("Player vs Player", "playerVsPlayerStats");
        }
    };
    
    private static final String helpActionCommand="help";
    
    @Override
    protected JMenuBar createMenuBar(){
        // File->Refresh Data, Show Log, Exit, Edit->Players,Matches,Games, Statistics, Help
        super.myMenuBar = new JMenuBar();
        
        JMenu file = new JMenu("File");
        JMenuItem refreshData = new JMenuItem("Refresh Data");
        JMenuItem showLog = new JMenuItem("Show log");
        refreshData.setActionCommand(refreshDataActionCommands);
        refreshData.addActionListener(this);
        showLog.setActionCommand(showLogActionCommand);
        showLog.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand(exitActionCommand);
        exit.addActionListener(this);
        file.add(refreshData);
        file.add(showLog);
        file.add(exit);
        
        //Edit
        JMenu edit = new JMenu("Edit");
        for(String comm: editStringClassMap.keySet()){
            JMenuItem p = new JMenuItem(comm);
            p.setActionCommand(comm);
            p.addActionListener(this);
            edit.add(p);
        }
        
        //Statistics
        JMenu statistics = new JMenu("Statistics");
        for(String comm: statisticsMenuMap.keySet()){
            JMenuItem p = new JMenuItem(comm);
            p.setActionCommand(statisticsMenuMap.get(comm));
            p.addActionListener(this);
            statistics.add(p);
        }
        
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
        } else if( editStringClassMap.containsKey(e.getActionCommand())) {
            FormsCaller.callNewWindow("Edit", new EditForm(editStringClassMap.get(e.getActionCommand())));
        } else if(e.getActionCommand().equals(showLogActionCommand)){
            LogsReader.start("test.log");
        } else if(e.getActionCommand().equals(statisticsPlayerActionCommand)){
            FormsCaller.callNewWindow("Player's statistics", new PlayersStatsForm());
        }
        
    }
}
