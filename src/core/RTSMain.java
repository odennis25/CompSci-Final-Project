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

import map.TerrainMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static com.almasb.fxgl.dsl.FXGL.*;

public class RTSMain extends GameApplication 
{
	
	private static int blockSize=50;//the amount of space each entity will take up
	private TerrainMap terrainMap= new TerrainMap(21, 21);
	private UnitMap uMap = new UnitMap(21,21);
	
	private Entity[][] unitEntitys= new Entity[21][21];
	private ArrayList<Entity> terrainEntitys= new ArrayList<Entity>();
	private Camera camera;

	private int mouseX;
	private int mouseY;
	
	private Node[][] nodeMap = new Node[20][20];


	//returns nodeMap
	public Node[][] getNMap()
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
        
        onBtnDown(MouseButton.PRIMARY,() -> System.out.println( unitEntitys[mouseX][mouseY]));
    	
        onBtnDown(MouseButton.SECONDARY,() -> System.out.println( mouseX +" "+   mouseY));
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
		
			
			moveTerrain(camera.getDX(),camera.getDY());//moves the camera
			moveUnit(camera.getDX(),camera.getDY());//moves the camera
			camera.setDX(0);//sets the change in  x to zero
			camera.setDY(0);//sets the change in  x to zero
			
			
			
		
	}

	/**iterates through the terrain entity's list moving each entity on the panel, moving the entire map*/
	private void moveTerrain(double dx, double dy) 
	{
		
		
		for(int i=0; i<terrainEntitys.size();i++) {
			terrainEntitys.get(i).setPosition(
					terrainEntitys.get(i).getX() - (dx * blockSize ),
					terrainEntitys.get(i).getY() - (dy * blockSize )
				);//need better solution
		
		}
  	
		
	}
	/**iterates through the unit entity's list moving each entity on the panel, moving the entire map*/
	private void moveUnit(double dx, double dy) 
	{
		
		
		for(int i=0; i<unitEntitys.length;i++) {
			
			for(int j=0; j<unitEntitys[i].length;j++)
			unitEntitys[i][j].setPosition(
					unitEntitys[i][j].getX() - (dx * blockSize),
					unitEntitys[i][j].getY() - (dy * blockSize)
				);

			
		}
  	
		
	}

	
	
	
	
	/**iterates through the terrain Map and spawns entity's with the terrains texture*/
	private void renderTerrain(int dx, int dy) 
	{
		
		
		for(int i=0; i<terrainMap.getMap().size(); i++) 
		{
        	
        	for(int j = 0; j<terrainMap.getMap().get(i).size();j++ ) 
        	{
        		TerrainType terrain=terrainMap.getMap().get(i).get(j).getType();
        		switch(terrain) 
        		{
				
					
				case CLIFF:
					terrainEntitys.add(spawn("cliff",(i+dx)*blockSize,(j+dy)*blockSize));
					break;
				case GROUND:
					terrainEntitys.add(spawn("ground",(i+dx)*blockSize,(j+dy)*blockSize));
			
					break;
				case WATER:
					terrainEntitys.add(spawn("water",(i+dx)*blockSize,(j+dy)*blockSize));
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
        		if(uMap.getUMap()[i][j]!=null) 
        		{
        		UnitType unitType =uMap.getUMap()[i][j].getUType();
        		
        		switch(unitType) 
        			{
				case INFANTRY:
					unitEntitys[i][j]=(spawn("infantry",(i+dx)*blockSize,(j+dy)*blockSize));
					break;

				case NONE:
					unitEntitys[i][j]=(spawn("none",(i+dx)*blockSize,(j+dy)*blockSize));
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
