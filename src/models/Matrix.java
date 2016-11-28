package models;
import Utilities.*;

public class Matrix{
	private
	int orig[][];
	int comp[][];
	
	public Matrix(int[][] inp){
		orig = inp;
		comp = new int[Constants.com_height][Constants.com_height];	
	}
	
	public void matrixCompression(){
		int flag = 0;
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				flag = 0;
				for(int k=0;k<8;k++){
					for(int l=0;l<8;l++){
						if(orig[i*8 + k][j*8 + l]==1)
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