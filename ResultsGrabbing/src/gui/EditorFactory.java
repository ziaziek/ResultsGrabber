/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Games;
import data.Matches;
import data.Players;
import javax.swing.JPanel;

/**
 *
 * @author Przemo
 */
public class EditorFactory {
    
    public static JPanel createPlayersControl(){
        JPanel ctrl = new JPanel();
        
            return ctrl;
        }
    
    public static JPanel createMatchesControl(){
        JPanel ctrl = new JPanel();
        
            return ctrl;
    }
    
    public static JPanel createGamesControl(){
        JPanel ctrl = new JPanel();
        
            return ctrl;
    }
    
    public static JPanel createControl(Class<?> c){
        if(c.equals(Players.class)){
            return createPlayersControl();
        }
        if(c.equals(Games.class)){
            return createGamesControl();
        }
        if(c.equals(Matches.class)){
            return createMatchesControl();
        }
        return null;
    }
}
