/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listRenderers;

import data.Players;
import data.PlayersHelper;

/**
 *
 * @author Przemo
 */
    /**
     * Renders the list item displayed value, which is First name + Last Name
     */
     public class PlayersListRenderer extends AbstractListItemsRenderer{

        @Override
        protected String renderTextItem(Object o) {
            if (o instanceof Players){
                return PlayersHelper.toFullName((Players)o);
            } else {
                return null;
            }
        }
    }
