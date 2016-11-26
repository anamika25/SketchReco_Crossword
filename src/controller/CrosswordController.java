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

import models.Grid;
import models.Sketch;
import models.Stroke;
import view.CrosswordPanel;
import view.Field;

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

   
}
