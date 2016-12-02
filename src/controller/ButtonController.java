package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Grid;

public class ButtonController implements ActionListener {
    private Grid grid;

    
    public ButtonController(Grid game) {
        this.grid = game;
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Train")) {
        	grid.trainData();
        }else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        /*       
        else if (e.getActionCommand().equals("Check"))
        	grid.checkGame();
        else if (e.getActionCommand().equals("Exit"))
            System.exit(0);
        else if (e.getActionCommand().equals("Help on"))
        	grid.setHelp(((JCheckBox)e.getSource()).isSelected());
        else
        	grid.setSelectedNumber(Integer.parseInt(e.getActionCommand())); */
    }
}