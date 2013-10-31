/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.listRenderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Przemo
 */
    /**
     * Abstract class for list items rendering
     */
     public abstract class AbstractListItemsRenderer extends JLabel implements ListCellRenderer{

        protected abstract String renderTextItem(Object o);
        
        @Override
        public Component getListCellRendererComponent(JList list,
                                     Object value,
                                     int index,
                                     boolean isSelected,
                                     boolean cellHasFocus) {
            setText(renderTextItem(value));

            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(Color.white);
            }  
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
        
    }
