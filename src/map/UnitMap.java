package map;

import java.util.ArrayList;

import com.almasb.fxgl.entity.Entity;

import units.Troop;
import units.Unit;
import units.UnitType;

public class UnitMap {
	private int width;
	private int height;
	private Unit[][] map;
	
	public UnitMap(int width,int height) 
	{
		/**fills the unit[][] with null*/	
		map =new Unit[width][height];
		for(int i=0; i<width;i++) 
		{
			
			for(int j=0; j<height; j++) 
			{
				
				map[i][j]= null;
				
			}
			
		}
		map[1][1]= new Unit(UnitType.INFANTRY,1,1,100);
		map[1][5]= new Unit(UnitType.INFANTRY,1,5,100);
	}

	public Unit[][] getUMap() {
			return map;
		
	}
	
	public int getHeight() {
		return height;
	}
	
	public int width() {
		return width;
				}
	
	
	
	public Unit[][] setUMap(int row, int column, Unit unit ) {
		
		map[row][column]= (unit);
		
		return map;
		
	}
}
