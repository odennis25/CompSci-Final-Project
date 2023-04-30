package map;

import java.util.ArrayList;
import map.TerrainType;
public class Map {
	private int width;
	private int height;
	private ArrayList<ArrayList<Terrain>> map;
	
	public Map(int width,int height) 
	{
		map = new ArrayList<ArrayList<Terrain>>();
		for(int i=0; i<width;i++) 
		{
			map.add(i,new ArrayList<Terrain>());
			for(int j=0; j<height; j++) 
			{
				
				map.get(i).add(j, new Terrain(TerrainType.GROUND));
				
			}
			
		}
		
	}

	public ArrayList<ArrayList<Terrain>> getMap() {
		// TODO Auto-generated method stub
		
			return map;
		
	}

}
