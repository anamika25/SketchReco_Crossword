package puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Utilities.Constants;
import learner.*;

public class Puzzle {
	private HashMap<Integer, String> solution;
	private HashMap<Integer, String> initialState;
	public Puzzle() {
		solution = new HashMap<Integer, String>();
		initialState = new HashMap<Integer, String>(); 
	}
	
	public void buildGame() {
		FileReaderUtility fd1 = new FileReaderUtility(Constants.solutionPath);
		FileReaderUtility fd2 = new FileReaderUtility(Constants.puzzlePath);
		ArrayList<String> sol = fd1.ReadFile();
		ArrayList<String> puzzle = fd2.ReadFile();
		
		for(int i = 0 ; i < Constants.gridHeight; ++i) {
			for(int j = 0 ; j < Constants.gridWidth; ++j) {
				int unique = ((i+j) * (i+j+1))/2 + j;
				solution.put(unique, sol.get(i*Constants.gridHeight + j));
				initialState.put(unique, puzzle.get(i*Constants.gridHeight + j));
			}
		}
	}
	
	public HashMap<Integer, String> getSolution() {
		return solution;
	}
	
	public HashMap<Integer, String> getPuzzle() {
		return initialState;
	}
	
}
