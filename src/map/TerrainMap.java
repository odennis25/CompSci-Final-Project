package map;

import java.util.ArrayList;
import map.TerrainType;
public class TerrainMap {
	private int width;
	private int height;
	private ArrayList<ArrayList<Terrain>> map;
	
	public TerrainMap(int width,int height) 
	{
		//fills the map with water--unfinished--
		map = new ArrayList<ArrayList<Terrain>>();
		for(int i=0; i<width;i++) 
		{
			map.add(i,new ArrayList<Terrain>());
			for(int j=0; j<height; j++) 
			{
				
				map.get(i).add(j, new Terrain(TerrainType.WATER));
				
			}
			
		}
		
	}

	public ArrayList<ArrayList<Terrain>> getMap() {
			return map;
		
	}
	

}
