package models;
import Utilities.*;

public class Matrix{
	int orig[][];
	int comp[][];
	
	public Matrix(){
		orig = new int[Constants.height][Constants.width];
		comp = new int[Constants.com_height][Constants.com_height];	
	}
}