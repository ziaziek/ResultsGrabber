/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listRenderers;

import data.Matches;
import java.text.SimpleDateFormat;

/**
 *
 * @author Przemo
 */
    public class MatchesListRenderer extends AbstractListItemsRenderer{

        @Override
        protected String renderTextItem(Object o) {
            if( o instanceof Matches){
                Matches m = (Matches)o;
                return m.getCity()+", "+m.getCountry()+", "+ SimpleDateFormat.getInstance().format(m.getDate().getTime());
            } else{
                return null;
            }
        }
        
    }
