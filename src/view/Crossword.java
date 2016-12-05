package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.ButtonController;
import controller.CrosswordController;
import models.Grid;
import puzzle.Puzzle;

public class Crossword extends JFrame{
	public Crossword() {
		super("Crossword");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        Grid grid = new Grid(); // empty 
        
        Puzzle puzzle = new Puzzle();
        puzzle.buildGame();
        
        JPanel subButtonPanel = new JPanel();
        subButtonPanel.setLayout(new GridLayout(3,0,0,0));
        ButtonController buttonController = new ButtonController(grid);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setController(buttonController);
        subButtonPanel.add(buttonPanel);
        add(subButtonPanel, BorderLayout.EAST);
        
        CrosswordPanel panel = new CrosswordPanel(puzzle);
        CrosswordController controller = new CrosswordController(panel, grid, puzzle);
        panel.setController(controller);
        add(panel, BorderLayout.CENTER);
        buttonController.setController(controller);
        grid.addObserver(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        
        controller.trainNeuralNetwork();
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
