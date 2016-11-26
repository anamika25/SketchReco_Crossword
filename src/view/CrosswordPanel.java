package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Utilities.Constants;
import controller.CrosswordController;
import models.Sketch;
import models.Stroke;

public class CrosswordPanel extends JPanel implements Observer{
	
	public class SubPanel extends JPanel{
		private List<Sketch> sketches;
		private Stroke ongoingStroke;
		private int xOffset;
		private int yOffset;
		
		public SubPanel(int xOff, int yOff){
			super();
			sketches = new ArrayList<Sketch>();
			xOffset = xOff;
			yOffset = yOff;
		}
		
		public SubPanel(GridLayout grid, int xOff, int yOff){
			super(grid);
			sketches = new ArrayList<Sketch>();
			xOffset = xOff;
			yOffset = yOff;
		}
		
		public int getXOffset(){
			return xOffset;
		}
		
		public int getYOffset(){
			return yOffset;
		}
		
		public List<Sketch> getSketches() {
	        return sketches;
	    }

	    public void addSketch(Sketch skt) {
	        sketches.add(skt);
	    }

	    public void resetPoints() {
	    	ongoingStroke = null;
	    }
	    
	    public void setOngoingStroke(Stroke s){
	    	this.ongoingStroke = s;
	    }
	    
	    @Override
	    public void paintComponent(Graphics g) {
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, this.getWidth(), this.getHeight());
	        
	        
	        g.setColor(Color.BLACK);
	        
	        for (int i = 0; i < sketches.size(); i++) {
				for (int j = 0; j < sketches.get(i).getStrokes().size(); j++) {
					Stroke stroke = sketches.get(i).getStrokes().get(j);
					for (int k = 1; k < stroke.getPoints().size(); k++) {
			            Point p1 = stroke.getPoints().get(k - 1);
			            Point p2 = stroke.getPoints().get(k);
			            g.drawLine(p1.x, p1.y, p2.x, p2.y);
			        }
				}
			}
	        
	        //draw ongoing stroke
	        if(ongoingStroke != null){
		        for (int k = 1; k < ongoingStroke.getPoints().size(); k++) {
		            Point p1 = ongoingStroke.getPoints().get(k - 1);
		            Point p2 = ongoingStroke.getPoints().get(k);
		            g.drawLine(p1.x, p1.y, p2.x, p2.y);
		        }
	        }
	    }
	}
	
	private Field[][] fields;
	private SubPanel[][] panels;
	public CrosswordPanel() {
		super(new GridLayout(Constants.gridHeight, Constants.gridWidth));
		panels = new SubPanel[Constants.gridHeight][Constants.gridWidth];
		for (int y = 0; y < Constants.gridHeight; y++) {
            for (int x = 0; x < Constants.gridWidth; x++) {
                panels[y][x] = new SubPanel(new GridLayout(Constants.subpanelHeight, Constants.subpanelWidth), x, y);
                panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[y][x]);
            }
        }
		
		fields = new Field[Constants.gridHeight][Constants.gridWidth];
        for (int y = 0; y < Constants.gridHeight; y++) {
            for (int x = 0; x < Constants.gridWidth; x++) {
                fields[y][x] = new Field(x, y, panels[y][x]);
                panels[y][x].add(fields[y][x]);
            }
        }
		
	}
	// TO DO
	public void update(Observable o, Object arg) {
	
	}
	
	public void setController(CrosswordController controller) {
        for (int y = 0; y < Constants.gridHeight; y++) {
            for (int x = 0; x < Constants.gridWidth; x++){
               panels[y][x].addMouseListener(controller);
               panels[y][x].addMouseMotionListener(controller);
            }
        }
    }
}
