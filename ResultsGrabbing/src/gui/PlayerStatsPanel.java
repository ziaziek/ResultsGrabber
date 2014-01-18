/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.stats.PlayerStats;
import gubas.forms.Dialog;
import gubas.forms.DialogForm;
import gubas.javaapplication1.FormsCaller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Przemo
 */
public class PlayerStatsPanel extends javax.swing.JPanel implements MouseListener, ChangeListener {

    
    PlayerStats statistics;
    Calendar initialDateRange = null, initialStartDate=null;
    /**
     * Creates new form playerStats
     */
    public PlayerStatsPanel() {
        initComponents();
    }

    public PlayerStatsPanel(PlayerStats stat){
        statistics = stat;
        initialDateRange = Calendar.getInstance();
        initialStartDate = statistics.getFirstDate();
        
        Calendar ldate = statistics.getLastDate();
        initComponents();
        if (initialStartDate != null && ldate != null) {
            
            initialDateRange.setTimeInMillis(ldate.getTimeInMillis() - initialStartDate.getTimeInMillis());
            setDisplayedValues();
            sldTime.setValue(sldTime.getMaximum());
            sldTime.addMouseListener(this);
        } else {
            FormsCaller.callNewWindow("No data available", new DialogForm(Dialog.OK, "No data available for this combination of players"));
        }
        
    }
    
    
    
    private void setDisplayedValues(){
        labAvgPoints.setText(String.format("%.4f", statistics.getAveragePoints()));
        labPercLost.setText(String.format("%.2f", statistics.getPercentageOfGamesLost()));
        labPercWon.setText(String.format("%.2f", statistics.getPercentageOfGamesWon()));
        labPlayer.setText(statistics.getPlayersName());
    }
    
    
    private double calculateTimeDistance(){
        return ((double) (sldTime.getMaximum() - sldTime.getValue()) / (double) (sldTime.getMaximum() - sldTime.getMinimum()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labPlayer = new javax.swing.JLabel();
        generalStatsPane = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labPercWon = new javax.swing.JLabel();
        labPercLost = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labAvgPoints = new javax.swing.JLabel();
        sldTime = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Player : ");

        labPlayer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        labPlayer.setText("jLabel2");

        generalStatsPane.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setText("% of WON");

        jLabel5.setText("% of LOST");
        jLabel5.setToolTipText("");

        labPercWon.setText("-");

        labPercLost.setText("-");

        jLabel8.setText("Avg. points");

        labAvgPoints.setText("-");

        jLabel3.setText("Time frame:");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout generalStatsPaneLayout = new javax.swing.GroupLayout(generalStatsPane);
        generalStatsPane.setLayout(generalStatsPaneLayout);
        generalStatsPaneLayout.setHorizontalGroup(
            generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalStatsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(generalStatsPaneLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(32, 32, 32)
                            .addComponent(labPercWon))
                        .addGroup(generalStatsPaneLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labPercLost))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalStatsPaneLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(100, 100, 100)
                        .addComponent(labAvgPoints))
                    .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        generalStatsPaneLayout.setVerticalGroup(
            generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalStatsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labPercWon)
                    .addComponent(jLabel8)
                    .addComponent(labAvgPoints))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labPercLost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalStatsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalStatsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labPlayer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generalStatsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel generalStatsPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labAvgPoints;
    private javax.swing.JLabel labPercLost;
    private javax.swing.JLabel labPercWon;
    private javax.swing.JLabel labPlayer;
    private javax.swing.JSlider sldTime;
    // End of variables declaration//GEN-END:variables


    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        if (me.getSource().equals(sldTime)) { //time on the slider has changed   
            statistics.reset();
            Calendar c1 = statistics.getFirstDate();
            Calendar c2 = statistics.getLastDate();
            if (c1 != null) {
                Calendar dt = Calendar.getInstance();
            double p = calculateTimeDistance();
                dt.setTimeInMillis(initialStartDate.getTimeInMillis() + (long) (p * initialDateRange.getTimeInMillis()));
                statistics.setFromDate(dt);
            }
            setDisplayedValues();
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if(ce.getSource()!=null && ce.getSource() instanceof PlayerStats){
            //TODO: Implement if neseccary
        }
    }
}
