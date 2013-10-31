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
    
    public static JPanel createPlayersControl(Players p){
        JPanel ctrl = new playersEditPanel(p);
            return ctrl;
        }
    
    public static JPanel createMatchesControl(Matches m){
        JPanel ctrl = new MatchesEditPanel(m);
            return ctrl;
    }
    
    public static JPanel createGamesControl(){
        JPanel ctrl = new JPanel();
        
            return ctrl;
    }
    
    public static JPanel createControl(Class<?> c, Object o){
        if(c.equals(Players.class)){
            return createPlayersControl((Players)o);
        }
        if(c.equals(Games.class)){
            return createGamesControl();
        }
        if(c.equals(Matches.class)){
            return createMatchesControl((Matches)o);
        }
        return null;
    }
}
