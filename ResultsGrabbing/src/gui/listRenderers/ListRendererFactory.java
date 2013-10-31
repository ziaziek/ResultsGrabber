/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listRenderers;

import data.Matches;
import data.Players;
import gui.EditForm;

/**
 *
 * @author Przemo
 */
public class ListRendererFactory {
    public  AbstractListItemsRenderer createListRendererFactory(Class<?> classType){
            if(classType.equals(Players.class)){
                return new PlayersListRenderer();
            } else if(classType.equals(Matches.class)){
                return new MatchesListRenderer();
            } else {
                return null;
            }
        }
}
