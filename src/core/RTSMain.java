package core;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.time.TimerAction;


import javafx.util.Duration;
import map.TerrainType;
import map.UnitMap;
import units.Unit;
import units.UnitType;
import map.Map;

public class RTSMain extends GameApplication 
{
	
	private static double blockSize=50;
	Map map= new Map(16, 12);
	UnitMap uMap = new UnitMap(16,12);
	ArrayList<Entity> unitEntitys= new ArrayList<Entity>();

   
	

	
	@Override
	protected void initSettings(GameSettings settings) 
	{
		settings.setWidth(800);
        settings.setHeight(600);
	}
	
	@Override
	protected void initInput() {
	
		
	}
	

	
	protected void initGame() 
	{
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
			
			
		else 
		{
			frame=0;
			
			if(uMap.getUMap().get(1).get(temp).getType()!=UnitType.EMPTY) {System.out.println("render "+temp);// proof of concept
					MoveUnit(1, temp, 1, temp+=1, uMap);}
			
			renderUnits();
		}
	}
	
	
	
	private void renderTerrain() 
	{
		
		for(int i=0; i<map.getMap().size(); i++) 
		{
        	
        	for(int j = 0; j<map.getMap().get(i).size();j++ ) 
        	{
        		TerrainType terrain=map.getMap().get(i).get(j).getType();
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
			unitEntitys.get(i).removeFromWorld();//need working solution  
		}
		
		
		
		
		for(int i=0; i<uMap.getUMap().size(); i++) 
		{
        	
        	for(int j = 0; j<uMap.getUMap().get(i).size();j++ ) 
        	{
        		UnitType unitType =uMap.getUMap().get(i).get(j).getType();
        		switch(unitType) 
        		{
				case INFANTRY:
					unitEntitys.add(spawn("infantry",i*blockSize,j*blockSize));
					break;
					
				case EMPTY:
					break;

				default:
					break;
        		
        		}
		
        	}
		}
	}
	public static void MoveUnit(int startX, int startY, int desX, int desY, UnitMap map) 
	{
		
		UnitType type =map.getUMap().get(startX).get(startY).getType();
		
		map.setUMap(desX, desY, type);
		map.setUMap(startX, startY, UnitType.EMPTY);
		
	}
	
public static double getBlockSize() 
	{
		return blockSize;
	}
	
	
	public static void main(String[] args) 
	{
		
		launch(args);
	}
}
