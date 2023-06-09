package map;
import java.util.ArrayList;

import com.almasb.fxgl.entity.Entity;

import map.TerrainType;
import units.Unit;
import units.UnitType;
/**
 * makes the terrain map
 * @author Owen
 *
 */
public class TerrainMap {
	private int width;
	private int height;
	private Terrain[][] map;
	/**
	 * 
	 * @param width	width of map
	 * @param height height of map
	 */
	public TerrainMap(int width,int height) {
	map =new Terrain[width][height];
	for(int i=0; i<width;i++) 
	{
		
		for(int j=0; j<height; j++) 
		{
			
			map[i][j]= new Terrain(TerrainType.GROUND);
			
		}
		
	}
	}
	
	public Terrain[][] getMap() {
			return  map;
		
	}
	

}
