package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import models.Grid;

public class Crossword extends JFrame{
	public Crossword() {
		super("Crossword");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        Grid grid = new Grid();
        
        setLocationRelativeTo(null);
        setVisible(true);
       
	}
		
	public static void main(String[] args) {
        try { 
        	
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        }
        catch (Exception ex) { 
        	ex.printStackTrace(); 
        }
        new Crossword();
    }
}
