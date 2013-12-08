/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import data.Players;
import data.stats.PlayerStats;
import database.DataDealer;
import errors.DataDealerReadException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Przemo
 */
public class PlayerStatsTest {
    
    PlayerStats stat = null;
    
    public PlayerStatsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Players p;
        try {
            p = (Players)(new DataDealer().readData(Players.class , 1));
            try {
                stat= new PlayerStats(p);
            } catch (Exception ex) {
                Logger.getLogger(PlayerStatsTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DataDealerReadException ex) {
            Logger.getLogger(PlayerStatsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void loadTest(){
        assertTrue(stat!=null);
        int ng = stat.getNumberOfGames();
        assertTrue(ng>0);
        System.out.println("Number of games="+ng);
    }
    
}