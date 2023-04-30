package core;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;

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
        	
        	for(int j = 0; j<map.getMap().get(j).size();j++ ) {
		
        		spawn("terrain",i*blockSize,j*blockSize);
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
