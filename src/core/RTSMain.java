package core;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import java.util.ArrayList;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import map.TerrainType;
import map.UnitMap;
import units.Unit;
import units.UnitType;
import map.Terrain;
import map.TerrainMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static com.almasb.fxgl.dsl.FXGL.*;

public class RTSMain extends GameApplication 
{
	
	private static int blockSize=50;//the amount of space each entity will take up
	private TerrainMap terrainMap= new TerrainMap(21, 21);
	private UnitMap uMap = new UnitMap(21,21);
	
	private Entity[][] unitEntities= new Entity[21][21];
	private Entity[][] terrainEntities= new Entity[21][21];
	
	private Camera camera;
	private ArrayList<Entity> selected=new ArrayList<Entity>() ;
	private int mouseX;
	private int mouseY;
	
	private static Node[][] nodeMap = new Node[21][21];


	//returns nodeMap
	public static Node[][] getNMap()
	{
		return nodeMap;
	}
	
	
	@Override
	/**Initializes settings*/
	protected void initSettings(GameSettings settings) 
	{
		camera= new Camera(0,0);
		settings.setWidth(800);
        settings.setHeight(600);
	}
	
	@Override
	/**Initializes inputs*/
	protected void initInput() {
		Input input = getInput();
		onKey(KeyCode.A, () -> camera.moveLeft());
		onKey(KeyCode.D, () -> camera.moveRight());
		onKey(KeyCode.W, () -> camera.moveUp());
        onKey(KeyCode.S, () -> camera.moveDown());
        
        onKey(KeyCode.TAB,()-> selected.clear());
      
        
        onBtnDown(MouseButton.PRIMARY,() -> selected.add(unitEntities[mouseX][mouseY]) );
    	
        onBtnDown(MouseButton.SECONDARY,() -> System.out.println( mouseX +" "+   mouseY));
        
        onBtnDown(MouseButton.SECONDARY,() -> moveSelected(selected,mouseX*blockSize, mouseY*blockSize));
	}
	

	/**Initializes game world*/
	protected void initGame() 
	{
		
		getGameWorld().addEntityFactory(new TerrainFactory());
		getGameWorld().addEntityFactory(new UnitFactory());
		renderTerrain(0,0);
		renderUnits(0,0);   
		
	}

	
	int frame=0;
	int temp=1;
	//runs at speed tpf
	
	
	
	protected void onUpdate(double tpf) {
		Input input = getInput();
		frame++;
		if(frame!=12) {
			
			mouseX=(int)(input.getMouseXWorld()/blockSize+camera.getx());
			mouseY=(int)(input.getMouseYWorld()/blockSize+camera.gety());
		}
		else
			frame=0;
		
			
			moveTerrainMap(camera.getDX(),camera.getDY());//moves the camera
			moveUnitMap(camera.getDX(),camera.getDY());//moves the camera
			camera.setDX(0);//sets the change in  x to zero
			camera.setDY(0);//sets the change in  x to zero
			
			
			
		
	}
	
	
	
	private void moveSelected(ArrayList<Entity> selected,int x, int y) {//work in progress
		
		for(int i=0; i<selected.size();i++) {
			if(selected.get(i).getType()!=UnitType.NONE) {
			
			selected.get(i).setPosition(x, y);
			
			
			
			
			
			//			System.out.println((int)selected.get(i).getX()/blockSize+" "+(int)selected.get(i).getY()/blockSize);
			
			
			
			
			}
		}
		
		
		
		
	}
	/**iterates through the terrain entity's list moving each entity on the panel, moving the entire map*/
	private void moveTerrainMap(double dx, double dy) 
	{
		
		
		for(int i=0; i<terrainEntities.length;i++) {
			
			for(int j=0; j<terrainEntities[i].length;j++)
				terrainEntities[i][j].setPosition(
						terrainEntities[i][j].getX() - (dx * blockSize),
						terrainEntities[i][j].getY() - (dy * blockSize)
					
				);
		
		}
  	
		
	}
	/**iterates through the unit entity's list moving each entity on the panel, moving the entire map*/
	private void moveUnitMap(double dx, double dy) 
	{
		
		
		for(int i=0; i<unitEntities.length;i++) {
			
			for(int j=0; j<unitEntities[i].length;j++)
				unitEntities[i][j].setPosition(
						unitEntities[i][j].getX() - (dx * blockSize),
						unitEntities[i][j].getY() - (dy * blockSize)
					
				);
			
		}
  	
		
	}

	
	
	
	
	/**iterates through the terrain Map and spawns entity's with the terrains texture*/
	private void renderTerrain(int dx, int dy) 
	{
		
		
		for(int i=0; i<terrainMap.getMap().length; i++) 
		{
        	
        	for(int j = 0; j<terrainMap.getMap()[i].length;j++ ) 
        	{
        		TerrainType terrain=terrainMap.getMap()[i][j].getTType();
        		switch(terrain) 
        		{
				
					
				case CLIFF:
					terrainEntities[i][j]=spawn("cliff",(i+dx)*blockSize,(j+dy)*blockSize);
					break;
				case GROUND:
					terrainEntities[i][j]=spawn("ground",(i+dx)*blockSize,(j+dy)*blockSize);
			
					break;
				case WATER:
					terrainEntities[i][j]=spawn("water",(i+dx)*blockSize,(j+dy)*blockSize);
					break;
				default:
					break;
        		
        		}
        		
        	}
		}
	}

	/**iterates through the unitMap and spawns entity's with the unit's texture*/ 
	private void renderUnits(int dx, int dy) 
	{
	
		for(int i=0; i<uMap.getUMap().length; i++) 
		{
        	
        	for(int j = 0; j<uMap.getUMap()[i].length;j++ ) 
        	{
        		//if(uMap.getUMap()[i][j]!=null) 
        		{
        		
        			
        		UnitType unitType =uMap.getUMap()[i][j].getUType();
        		
        		switch(unitType) 
        			{
				case INFANTRY:
					unitEntities[i][j]=spawn("infantry",(i+dx)*blockSize,(j+dy)*blockSize);
					break;

				case NONE:
					unitEntities[i][j]=spawn("none",(i+dx)*blockSize,(j+dy)*blockSize);
					break;
        		
        			}
        		}
        	}
		}
	}
	

	
	
	/**returns block size*/ 
public static int getBlockSize() 
	{
		return blockSize;
	}
public static void setBlockSize(double b) {
	blockSize=(int) b;
}
	
	/**starts the game application*/ 
	public static void main(String[] args)
	{
		
		launch(args);
	}
}
