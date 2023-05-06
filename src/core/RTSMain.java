package core;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import map.TerrainType;
import map.UnitMap;
import units.Unit;
import units.UnitType;

import map.TerrainMap;


import static com.almasb.fxgl.dsl.FXGL.*;

public class RTSMain extends GameApplication 
{
	
	private static int blockSize=50;
	TerrainMap terrainMap= new TerrainMap(16, 12);
	UnitMap uMap = new UnitMap(16,12);
	ArrayList<Entity> unitEntitys= new ArrayList<Entity>();
	Node map;//used for camera
	
	
	

	
	@Override
	protected void initSettings(GameSettings settings) 
	{
		settings.setWidth(800);
        settings.setHeight(600);
	}
	
	@Override
	protected void initInput() {
	
//		onKey(KeyCode.A, () -> camera.getComponent(PlayerComponent.class).moveLeft());
//        onKey(KeyCode.D, () -> player.getComponent(PlayerComponent.class).moveRight());
//        onKey(KeyCode.W, () -> player.getComponent(PlayerComponent.class).moveUp());
//        onkey(KeyCode.S, ()-> camera.)
	}
	

	
	protected void initGame() 
	{
		//adds things to the game world
		getGameWorld().addEntityFactory(new TerrainFactory());
		getGameWorld().addEntityFactory(new UnitFactory());
		renderTerrain();
		renderUnits();   
		
	}

	
	int frame=0;
	int temp=1;
	protected void onUpdate(double tpf) {
		
		frame++;
		if(frame!=30) {}
			
			
		else //runs the code once every 30 times the onUpdate method runs
		{
			frame=0;
			renderUnits();
		}
	}
	

	
	private void renderTerrain() 
	{
		//iterates through the terrain Map and spawns entity's with the terrains texture
		for(int i=0; i<terrainMap.getMap().size(); i++) 
		{
        	
        	for(int j = 0; j<terrainMap.getMap().get(i).size();j++ ) 
        	{
        		TerrainType terrain=terrainMap.getMap().get(i).get(j).getType();
        		switch(terrain) 
        		{
				
					
				case CLIFF:
					spawn("cliff",i*blockSize,j*blockSize);
					break;
				case GROUND:
					spawn("ground",i*blockSize,j*blockSize);
			
					break;
				case WATER:
					spawn("water",i*blockSize,j*blockSize);
					break;
				default:
					break;
        		
        		}
        		
        	}
		}
	}

	private void renderUnits() 
	{
		
		for(int i=0; i<unitEntitys.size();i++) {
			unitEntitys.get(i).removeFromWorld();//need better solution  
		}
		
		
		//iterates through the unitMap and spawns entity's with the unit's texture 
		
		for(int i=0; i<uMap.getUMap().length; i++) 
		{
        	
        	for(int j = 0; j<uMap.getUMap()[i].length;j++ ) 
        	{
        		if(uMap.getUMap()[i][j]!=null) 
        		{
        		UnitType unitType =uMap.getUMap()[i][j].getUType();
        		
        		
        		
        		switch(unitType) 
        			{
				case INFANTRY:
					unitEntitys.add(spawn("infantry",i*blockSize,j*blockSize));
					break;

				default:
					break;
        		
        			}
        		}
        	}
		}
	}
	
	
	
public static int getBlockSize() 
	{
		return blockSize;
	}
	
	
	public static void main(String[] args) 
	{
		
		launch(args);
	}
}
