package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.ButtonController;
import controller.CrosswordController;
import models.Grid;

public class Crossword extends JFrame{
	public Crossword() {
		super("Crossword");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        Grid grid = new Grid();
        
        JPanel subButtonPanel = new JPanel();
        subButtonPanel.setLayout(new GridLayout(3,0,0,0));
        
        ButtonController buttonController = new ButtonController(grid);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setController(buttonController);
        subButtonPanel.add(buttonPanel);
        add(subButtonPanel, BorderLayout.EAST);
        
        CrosswordPanel panel = new CrosswordPanel();
        CrosswordController controller = new CrosswordController(panel, grid);
        panel.setController(controller);
        add(panel, BorderLayout.CENTER);
        grid.addObserver(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //controller.trainNeuralNetwork();
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
