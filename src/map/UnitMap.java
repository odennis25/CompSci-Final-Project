package map;


import com.almasb.fxgl.entity.Entity;

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
				
				map[i][j]= new Unit(UnitType.NONE,i,j,0);
				
			}
			
		}
		map[1][0]= new Unit(UnitType.INFANTRY,1,0,100);
		map[1][1]= new Unit(UnitType.INFANTRY,1,1,100);
		map[1][2]= new Unit(UnitType.INFANTRY,1,2,100);
		map[1][3]= new Unit(UnitType.INFANTRY,1,3,100);
		map[1][4]= new Unit(UnitType.INFANTRY,1,4,100);
		map[1][5]= new Unit(UnitType.INFANTRY,1,5,100);
		map[1][6]= new Unit(UnitType.INFANTRY,1,6,100);
		map[1][7]= new Unit(UnitType.INFANTRY,1,7,100);
		map[3][7]= new Unit(UnitType.ENEMYINFANTRY,3,7,100);
		map[4][7]= new Unit(UnitType.ENEMYINFANTRY,4,7,100);
	
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
	
	public int length() {
		return map.length;
	}
	
	public Unit[][] setUMap(int row, int column, Unit unit ) {
		
		map[row][column]= unit;
		
		return map;
		
	}

	public Unit get(int x, int y) {
		return map[x][y];
	}
}
