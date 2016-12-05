package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.Grid;
import view.Field;

public class ButtonController implements ActionListener {
    private Grid grid;
    private CrosswordController controller;
    
    public ButtonController(Grid game) {
        this.grid = game;     
    }
    
    public void setController(CrosswordController controller) {
    	this.controller = controller;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Train")) {
        	grid.trainData();
        }else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if(e.getActionCommand().equals("Erase")) {
        	for(Field field : controller.wrongFields) {
        		int index = field.getText().indexOf("<br");
        		if(index != -1) {
        			field.setText(field.getText().substring(0, index) + "<html>");
        		} else {
        			field.setText("");
        		}	
        	}
        	controller.wrongFields = new ArrayList<Field>();
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