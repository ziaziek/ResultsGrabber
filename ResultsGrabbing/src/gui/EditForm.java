/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.DataDealer;
import gubas.forms.BaseForm;
import gui.listRenderers.ListRendererFactory;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    
}
