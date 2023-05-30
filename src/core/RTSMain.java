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
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.box2d.collision.shapes.Shape;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
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
	private static int mapSize=21;
	private static int blockSize=50;//the amount of space each entity will take up
	private TerrainMap terrainMap= new TerrainMap(mapSize, mapSize);
	private UnitMap uMap = new UnitMap(mapSize,mapSize);
	private Entity[][] unitEntities= new Entity[mapSize][mapSize];
	private Entity[][] terrainEntities= new Entity[mapSize][mapSize];
	
	private Camera camera;
	
	private ArrayList<Entity> selected=new ArrayList<Entity>() ;
	
	private static Node[][] nodeMap = new Node[21][21];
	
	private int mouseX;
	private int mouseY;
	private int frame=0;
	


	/**returns nodeMap*/
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
        settings.setHeight(800);
	}
	
	@Override
	/**Initializes inputs*/
	protected void initInput() {
		
		onKey(KeyCode.A, () -> camera.moveLeft());
		onKey(KeyCode.D, () -> camera.moveRight());
		onKey(KeyCode.W, () -> camera.moveUp());
        onKey(KeyCode.S, () -> camera.moveDown());
        
        onKey(KeyCode.TAB,()-> selected.clear());
      
        onBtnDown(MouseButton.PRIMARY,() -> selected.add(unitEntities[mouseX][mouseY]) );

        onBtnDown(MouseButton.SECONDARY,() -> moveSelected(selected,mouseX, mouseY));
        onKeyDown(KeyCode.H,() -> System.out.println(unitEntities[mouseX][mouseY]));
	}
	@Override
	protected void initUI() 
	{
	    Rectangle rect = new Rectangle();
	}
	    		
	

	/**Initializes game world*/
	protected void initGame() 
	{
		
		getGameWorld().addEntityFactory(new TerrainFactory());
		getGameWorld().addEntityFactory(new UnitFactory());
		renderTerrain(0,0);
		renderUnits(0,0);   
		
	}

	
<<<<<<< HEAD
	int frame=0;
	int temp=1;
	//runs at speed tpf

=======
	
	/**runs at speed tpf*/
>>>>>>> b5cac6a86fd220e06ff26986f6712ae4a6482b6b
	protected void onUpdate(double tpf) {
		Input input = getInput();
		frame++;
		if(frame!=12) {
			
			mouseX=(int)(input.getMouseXWorld()/blockSize+camera.getx());
			mouseY=(int)(input.getMouseYWorld()/blockSize+camera.gety());
		}
		else
		{	
			frame=0;
			
			
			for(int i = 0;i<unitEntities.length;i++)
				{
					for(int j = 0;j<unitEntities[0].length;j++)
					{
						Unit[][] map = uMap.getUMap();
						if(map[i][j].getUType() == UnitType.INFANTRY)
						{
							for(int a = 0;i<unitEntities.length;i++)
							{
								for(int b = 0;j<unitEntities[0].length;j++)
								{
									if(unitEntities[i][j].isColliding(unitEntities[a][b]))
										{
											Damage.dealDam(1,(Unit) unitEntities[i][j]);
											Damage.dealDam(1,(Unit) unitEntities[a][b]);
											System.out.println("goober");
										}
								}
							}
						}
					}
				}
		}
		
		
		
		
			moveTerrainMap(camera.getDX(),camera.getDY());//moves the camera
			moveUnitMap(camera.getDX(),camera.getDY());//moves the camera
			camera.setDX(0);//sets the change in  x to zero
			camera.setDY(0);//sets the change in  x to zero
			
			
			
		
	}
	
	/**moves units in array list selected to the x and y cord */
	private void moveSelected(ArrayList<Entity> selected,int x, int y) {//work in progress
		
		int dx=0;
		int dy=0;
		for(int i=0; i<selected.size();i++) {
			
			
			
			int susx=(int) (selected.get(i).getX()/(blockSize)+camera.getx());
			int susy=(int) (selected.get(i).getY()/(blockSize)+camera.gety());
			boolean moved=false;
			
			
			
			
			if(selected.get(i).getType()!=UnitType.NONE) {
			
			
				
			while(!moved) {//loops untill the unit moves to a valid position
			
			if(x+dx==21||y+dy==21||x+dx==-1||y+dy==-1) {//checks if out of bounds
					moved=true;}
				
			else if(unitEntities[x+dx][y+dy]==unitEntities[susx][susy]) {//checks if the unit has not moved
					moved=true;
				}
				
			
			
			else if(unitEntities[x+dx][y+dy].getType()!=UnitType.NONE) {//checks if the space is already occupied if so changes the dx and dy	
				dx+=1;
				dy+=1;
					}	
			
			
			
			
			
			else {	//moves the entity to the correct spot after all checks are made
			Entity temp=unitEntities[x+dx][y+dy];
			unitEntities[x+dx][y+dy] = unitEntities[susx][susy];
			unitEntities[susx][susy]=temp;
			selected.get(i).setPosition((x-camera.getx()+dx)*blockSize,((y-camera.gety()+dy)*blockSize));
			moved=true;
			
			
			System.out.println((x+dx)+" "+ (y+dy)+" array cord");
			System.out.println((x-camera.getx()+dx)+" "+(y-camera.gety()+dy)+" map cords");
			
			System.out.println("next character");
			}
			}
			
			}
			moved=false;
			dx=0;
			dy=0;
		}
		
		
		
		System.out.println("next Move");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
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
        		if(uMap.getUMap()[i][j]!=null) 
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
				case ENEMYINFANTRY:
					unitEntities[i][j]=spawn("enemyInfantry",(i+dx)*blockSize,(j+dy)*blockSize);
					break;
				
				case FACTORY:
					unitEntities[i][j]=spawn("factory",(i+dx)*blockSize,(j+dy)*blockSize);
					break;
				
				default:
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

	/**sets block size*/ 
public static void setBlockSize(double b) {
	blockSize=(int) b;
}
	
	/**starts the game application*/ 
	public static void main(String[] args)
	{
		
		launch(args);
	}
}
