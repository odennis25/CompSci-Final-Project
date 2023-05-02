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
		map =new Unit[width][height];
		for(int i=0; i<width;i++) 
		{
			
			for(int j=0; j<height; j++) 
			{
				
				map[i][j]= new Unit(UnitType.EMPTY);
				
			}
			
		}
		map[1][1]= new Unit(UnitType.INFANTRY);
		map[1][5]= new Unit(UnitType.INFANTRY);
	}

	public Unit[][] getUMap() {
		// TODO Auto-generated method stub
		
			return map;
		
	}
	
	public Unit[][] setUMap(int row, int column, UnitType unit ) {
		// TODO Auto-generated method stub
		
		map[row][column]= new Unit(unit);
		
		return map;
		
	}
}
