package models;
import Utilities.*;

public class Matrix{
	private
	int orig[][];
	int comp[][];
	
	public Matrix(int[][] inp){
		orig = inp;
		comp = new int[Constants.com_height][Constants.com_width];	
	}
	
	public void matrixCompression(){
		int flag = 0;
		for(int i=0;i<Constants.com_height;i++){
			for(int j=0;j<Constants.com_width;j++){
				flag = 0;
				for(int k=0;k<Constants.mat_comp;k++){
					for(int l=0;l<Constants.mat_comp;l++){
						if(orig[i*Constants.mat_comp + k][j*Constants.mat_comp + l]==1)
							{
								flag = 1;
								break;
							}	
					}
					if(flag==1)
						break;
				}
				if(flag==1)
					comp[i][j]=1;
				else
					comp[i][j] = 0;
			}
			
		}
		
	}
	public int[][] getComp(){
		return comp;
	}
	
	
}