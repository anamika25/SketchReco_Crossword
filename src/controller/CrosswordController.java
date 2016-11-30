package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import Utilities.Constants;
import models.*;

import models.Grid;
import models.Sketch;
import models.Stroke;
import view.CrosswordPanel;
import view.Field;
import view.CrosswordPanel.SubPanel;


public class CrosswordController extends MouseInputAdapter {
	private CrosswordPanel crosswordPanel;    
    private Grid grid;                  
  //  private TemplateMatcher templateMatcher; TO DO : Need to implement our classifier: Nishant and Varinder
    private Stroke currentStroke;
    private Sketch currentSketch;
    private Timer timer;
    private TimerTask timerTask;
    private Field currentField;
    private HashMap<Field, ArrayList<Sketch>> fieldSketchMap;
    private SubPanel currentPanel;

    public CrosswordController(CrosswordPanel panel, Grid grid) {
        this.crosswordPanel = panel;
        this.grid = grid;
        this.timer = new Timer();
        this.fieldSketchMap = new HashMap<Field, ArrayList<Sketch>>();
       // this.templateMatcher = new TemplateMatcher(".");
    }

    public void mousePressed(MouseEvent e) {
        SubPanel panel = (SubPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Field) {
        	Field field = (Field)component;
            int x = field.getFieldX();
            int y = field.getFieldY();
	        if(timerTask != null)
	        	timerTask.cancel();
	        if(_changedField(e.getX(), e.getY(), panel) && currentSketch != null){
        		_startNewSketch();
        	}
	        currentStroke = new Stroke(new ArrayList<Point>());
	        currentStroke.addPoint(e.getX(), e.getY());        		
	        this.currentField = field;
            
        }
    }
    
    public void mouseDragged(MouseEvent e) {
    	SubPanel panel = (SubPanel)e.getSource();
    	currentStroke.addPoint(e.getX(), e.getY());
        panel.setOngoingStroke(currentStroke);
        panel.repaint();
    }
    
    public void mouseReleased(MouseEvent e) {
    	SubPanel panel = (SubPanel)e.getSource();
    	panel.resetPoints();
    	if(currentSketch == null)
    	{
    		int row = _getStrokeRow()+(panel.getYOffset());
    		int column = _getStrokeColumn()+(panel.getXOffset());
    		currentSketch = new Sketch(row, column);
    		System.out.println("the row is " + row);
    		currentSketch = new Sketch(row, column);
    		System.out.println("the column is " + column);
    	}
    	
    	currentSketch.addStroke(currentStroke);
    	currentField = (Field)panel.getComponentAt(e.getPoint());
    	for(Point p : currentStroke.getPoints()) {
    		System.out.println(p.getX() + " , " + p.getY());
    		currentSketch.setMatrixIndex((int)(p.getY()), (int)(p.getX()));
    	}
 
    	
    	if(!panel.getSketches().contains(currentSketch)) {
    		panel.addSketch(currentSketch);
    	}
    	else {
    		List<Sketch> sketches = panel.getSketches();
    		int idx = sketches.indexOf(currentSketch);
    		sketches.set(idx, currentSketch);	
    	}
    	
    	currentPanel = panel;
    	ArrayList<Sketch> sketchList;
    	if(fieldSketchMap.containsKey(currentField))
    		sketchList = fieldSketchMap.get(currentField);
    	else sketchList = new ArrayList<Sketch>();
    	
    	this.currentPanel = panel;
    	
    	if(!sketchList.contains(currentSketch))
    		sketchList.add(currentSketch);
		fieldSketchMap.put(currentField, sketchList);
    	
    	timerTask = new TimerTask() {
  		  @Override
  		  public void run() {
  		    _startNewSketch();
  		  }
  		};
  		timer.schedule(timerTask, 5*1000);
    }
    
    public void mouseClicked(MouseEvent e) {
    	SubPanel panel = (SubPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Field) {
            Field field = (Field)component;
            int x = field.getFieldX(), y = field.getFieldY();
            //game.setNumber(x, y, "A");
            field.setLetter(0, true);
            List<Sketch> sketches = currentPanel.getSketches();
            List<Sketch> fieldSketch = fieldSketchMap.get(field);
            for(Sketch s: fieldSketch) {
            	int idx = sketches.indexOf(s);
            	if(idx != -1)
            		sketches.remove(idx);
            	panel.repaint();
            }            	           
        }
    }
    
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    
    private int _getStrokeRow(){
		return _getStrokeRow(currentStroke.getFirstPoint().y);
    }
    
    private int _getStrokeRow(int y){
		return y/80;
    }
    
    private int _getStrokeColumn(){
		return _getStrokeColumn(currentStroke.getFirstPoint().x);
    }
    
    private int _getStrokeColumn(int x){
		return x/80;
    }
    
    private boolean _changedField(int x, int y, SubPanel panel){
    	return currentSketch == null || currentSketch.getRow() != _getStrokeRow(y)+(panel.getYOffset()) || currentSketch.getColumn() != _getStrokeColumn(x)+(panel.getXOffset());
    }
    
    private void _startNewSketch(){
    	if(currentSketch != null){
    		//_setFieldValue(_classifySketch(currentSketch));
    		List<Sketch> sketches = currentPanel.getSketches();
    	    int idx = sketches.indexOf(currentSketch);
    	    if(idx != -1) sketches.remove(idx);
    	    	
    	    List<Sketch> fieldSketch = fieldSketchMap.get(currentField);
    	    for(Sketch s: fieldSketch) {
            	int id = sketches.indexOf(s);
            	if(id != -1)
            		sketches.remove(id);
            	currentPanel.repaint();
            }
    	    int[][] inp = currentSketch.getImageMatrix();
        	Matrix mat = new Matrix(inp);
        	mat.matrixCompression();
        	int comp[][] = mat.getComp();
        	
        	for(int i = 0;i<Constants.com_height;i++){
        		for(int j=0;j<Constants.com_width;j++)
        			System.out.print(comp[i][j]);
        		System.out.println();
        	}
	    	currentSketch = null;
	    	//crosswordPanel.repaint();
		} 
    }
   
}
