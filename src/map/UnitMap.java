package map;

import java.util.ArrayList;

import units.Troop;
import units.Unit;
import units.UnitType;

public class UnitMap {
	private int width;
	private int height;
	private ArrayList<ArrayList<Unit>> map;
	
	public UnitMap(int width,int height) 
	{
		map = new ArrayList<ArrayList<Unit>>();
		for(int i=0; i<width;i++) 
		{
			map.add(i,new ArrayList<Unit>());
			for(int j=0; j<height; j++) 
			{
				
				map.get(i).add(j, new Unit(UnitType.EMPTY));
				
			}
			
		}
		map.get(1).set(1, new Unit(UnitType.INFANTRY));
		map.get(1).set(5, new Unit(UnitType.INFANTRY));
	}

	public ArrayList<ArrayList<Unit>> getUMap() {
		// TODO Auto-generated method stub
		
			return map;
		
	}
	
	public ArrayList<ArrayList<Unit>> setUMap(int row, int column, UnitType unit ) {
		// TODO Auto-generated method stub
		
		map.get(row).set(column, new Unit(unit));
		
		return map;
		
	}
}
