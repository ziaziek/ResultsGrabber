/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Matches;
import data.Players;
import database.DataDealer;
import gubas.forms.BaseForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
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
          panel = new JPanel(new BorderLayout());  
        } else {
            panel.removeAll();
        }
        
        panel.add(createListOfItems(), BorderLayout.WEST);
        
        return panel;
    }

    protected JScrollPane createListOfItems(){
        List lp = new DataDealer().readConditionedData(editorClass, "id>0");
        lvp = new JList();
        JScrollPane listPane = new JScrollPane(lvp);
        DefaultListModel lvpModel = new DefaultListModel();
        lvp.addMouseListener(this);
        lvp.setCellRenderer(new ListRendererFactory().createListRendererFactory(editorClass));
        for(Object p: lp){
            lvpModel.addElement(p);
        }
        lvp.setModel(lvpModel);
        listPane.setOpaque(false);
        return listPane;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getClickCount()==2 && lvp!=null){
            int ix = lvp.locationToIndex(me.getPoint());
            JPanel ctrlPanel = EditorFactory.createControl(editorClass, (lvp.getModel().getElementAt(ix)));
            if(panel.getComponentCount()>1){
              panel.remove(panel.getComponentCount()-1);  
            }
            panel.add(ctrlPanel, BorderLayout.CENTER);
            panel.revalidate();
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
    
    
    protected class ListRendererFactory {
        
        protected  AbstractListItemsRenderer createListRendererFactory(Class<?> classType){
            if(classType.equals(Players.class)){
                return new PlayersListRenderer();
            } else if(classType.equals(Matches.class)){
                return new MatchesListRenderer();
            } else {
                return null;
            }
        }
        
    }
    /**
     * Abstract class for list items rendering
     */
     abstract class AbstractListItemsRenderer extends JLabel implements ListCellRenderer{

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
    /**
     * Renders the list item displayed value, which is First name + Last Name
     */
     class PlayersListRenderer extends AbstractListItemsRenderer{

        @Override
        protected String renderTextItem(Object o) {
            if (o instanceof Players){
                Players p = (Players)o;
                return (p.getFirstName() + " "+ p.getLastName());
            } else {
                return null;
            }
        }
    }
    
    protected class MatchesListRenderer extends AbstractListItemsRenderer{

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
}
