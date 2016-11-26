package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import com.sun.corba.se.impl.orbutil.closure.Constant;

import Utilities.*;
public class Field extends JLabel {
	
	public static class LetterSet {
		private static final String[] alphabets = 
				{"1", "2", "3", "4", "5", "6", "7", "8", "9",};
	}
	
	private int x;      // X position in game.
    private int y;      // Y position in game.
    public Field(int x, int y) {
        super("", CENTER);
        this.x = x;
        this.y = y;
        //this.parentPanel = parent;
        setPreferredSize(new Dimension(Constants.width,Constants.height));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        setOpaque(false);
    }

    public void setLetter(int letter, boolean userInput) {
        setForeground(userInput ? Color.BLUE : Color.BLACK);
        String num = letter > 0? LetterSet.alphabets[letter-1] + "" : "";
        setText(num);
    }

    public int getFieldX() {
        return x;
    }

    public int getFieldY() {
        return y;
    }
    /*
    public SubPanel getParentPanel(){
    	return parentPanel;
    }
    */
    
}