package core;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import map.TerrainType;
import map.Map;

public class RTSMain extends GameApplication {
	
	private static double blockSize=50;
	Map map= new Map(16, 12);
	@Override
	protected void initGame() {
		getGameWorld().addEntityFactory(new Factory());
		
		initTerrain();
	        
	}
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(800);
        settings.setHeight(600);
	}

	private void initTerrain() {
        
		for(int i=0; i<map.getMap().size(); i++) {
        	
        	for(int j = 0; j<map.getMap().get(i).size();j++ ) {
        		TerrainType terrain=map.getMap().get(i).get(j).getType();
        		switch(terrain) {
				
					
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
	
	public static double getBlockSize() {
		return blockSize;
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
