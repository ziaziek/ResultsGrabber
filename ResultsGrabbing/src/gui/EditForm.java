/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Players;
import database.DataDealer;
import gubas.forms.BaseForm;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Przemo
 */
public class EditForm extends BaseForm implements MouseListener{
    
    JPanel panel = new JPanel();
    Class<?> editorClass;
    JList lvp;
    
    public EditForm(Class<?> c){
        editorClass = c;
        add(createMainPanel());
    }
    
    protected JPanel createMainPanel(){
        if(panel==null){
          panel = new JPanel();  
        } else {
            panel.removeAll();
        }
        
        List lp = new DataDealer().readConditionedData(editorClass, "id>0");
        lvp = new JList();
        JScrollPane listPane = new JScrollPane(lvp);
        DefaultListModel lvpModel = new DefaultListModel();
        lvp.addMouseListener(this);
        lvp.setCellRenderer(new PlayersListRenderer());
        for(Object p: lp){
            lvpModel.addElement(p);
        }
        lvp.setModel(lvpModel);
        panel.add(listPane);
        return panel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getClickCount()==2 && lvp!=null){
            int ix = lvp.locationToIndex(me.getPoint());
            System.out.println("Selected item is "+ ((Players)(lvp.getModel().getElementAt(ix))).getId());

        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    /**
     * Renders the list item displayed value, which is First name + Last Name
     */
    protected class PlayersListRenderer extends JLabel implements ListCellRenderer{
        
       @Override
        public Component getListCellRendererComponent(JList list,
                                     Object value,
                                     int index,
                                     boolean isSelected,
                                     boolean cellHasFocus){

            if (value instanceof Players){
                Players p = (Players)value;
                setText(p.getFirstName() + " "+ p.getLastName());
            }
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
}
