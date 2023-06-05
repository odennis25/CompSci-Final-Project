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
import com.almasb.fxgl.texture.Texture;

//import Pathing.NodeMaker;


import javafx.scene.control.Button;

import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import map.TerrainType;
import map.UnitMap;
import units.Unit;
import units.UnitType;
import map.Terrain;
import map.TerrainMap;
import Pathing.AStar;
import Pathing.Node;
import Pathing.NodeMaker;
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
	private static Entity[][] terrainEntities= new Entity[mapSize][mapSize];
	private static Boolean[][] terrain = new Boolean[mapSize][mapSize];
	
	private Camera camera;
	
	private ArrayList<Entity> selected=new ArrayList<Entity>() ;
		
	private int mouseX;
	private int mouseY;
	private int frame=0;
	private String buildID;
	
	private static Node[][] nodeMap = new Node[mapSize][mapSize];
	
	
	//returns nodeMap


	/**returns nodeMap*/
	public static Node[][] getNMap()
	{
		return nodeMap;
	}
	public static int getMapSize()
	{
		return mapSize;
	}
	public static Boolean[][] getTerrainMap()
	{
		return terrain;
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
      
       
        
        onBtnDown(MouseButton.PRIMARY,() -> onLeftClick());

        onBtnDown(MouseButton.SECONDARY,() -> moveSelected(selected,mouseX, mouseY));
        onKeyDown(KeyCode.H,() -> System.out.println(unitEntities[mouseX][mouseY]));
        
        
        
	}
	@Override
	protected void initUI() 
	{
		//makes backround for button area. opacity is set to 0 for some reason idk why
		Rectangle rect = new Rectangle(400,100);
	    rect.setTranslateX(400); 
	    rect.setTranslateY(700);
	    getGameScene().addUINode(rect);
	    

	    //puts image of factory on screen
	  Texture tex = makeImageForUI("factory");
	    getGameScene().addUINode(tex);
	    // makes button to build factory
	    Button factorybtn = makeButtonForUI("factory");
	    getGameScene().addUINode(factorybtn);

	    
	    
	    
	}
	
 		
	

	/**Initializes game world*/
	protected void initGame() 
	{
		//play("RTS1_Hollowrock.wav");
		getGameWorld().addEntityFactory(new TerrainFactory());
		getGameWorld().addEntityFactory(new UnitFactory());
		getGameWorld().spawn("infantry",700,600);
		
		
		renderTerrain(0,0);
		renderUnits(0,0);   
        nodeMap = NodeMaker.nodeMaker(new Node[mapSize][mapSize]);

	}
	int temp=1;
	//runs at speed tpf

	
	/**runs at speed tpf*/
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
			
			
			
			int susx= (int) Math.round(selected.get(i).getX()/(blockSize)+camera.getx()); 
			int susy=(int)  Math.round(selected.get(i).getY()/(blockSize)+camera.gety());
			boolean moved=false;
			
			
			
			
			if(selected.get(i).getType()!=UnitType.NONE) 
			{
			
				
				while(!moved) 
				{//loops until the unit moves to a valid position
				
					if(unitEntities[x+dx][y+dy]==unitEntities[susx][susy]) {//checks if the unit has not moved
						break;
					}
			
			
			
			
				while(unitEntities[x+dx][y+dy].getType()!=UnitType.NONE) {//checks if the space is already occupied if so changes the dx and dy	
					dx+=1;
					}	
		
		
			
			
				if(x+dx==21||y+dy==21||x+dx==-1||y+dy==-1) {//checks if out of bounds
					break;
					}
				//moves the entity to the correct spot after all checks are made
			
			

			
			
				//moves the entity to the correct spot after all checks are made
				//use both the aStar method and then call printPath to get the array of id's
				Entity temp2=unitEntities[y+dx][x+dy];
				unitEntities[x+dx][y+dy] = unitEntities[susx][susy];
				unitEntities[susx][susy]=temp2;
				selected.get(i).setPosition((x-camera.getx()+dx)*blockSize,((y-camera.gety()+dy)*blockSize));
				moved=true;
			
				}
			moved=false;
			dx=0;
			dy=0;
		
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
		//makes the true/false map list and renders the map with mountains and grass
		for(int r = 0; r<mapSize; r++)
		{
			for(int c = 0; c<mapSize; c++)
			{
				int tempInt = (int) (Math.random()*10+1);
				
				
				
				if(tempInt==1)
				{
					terrain[r][c] = false;
					terrainEntities[r][c]=spawn("cliff",(r+dx)*blockSize,(c+dy)*blockSize);
					System.out.print("{false} ");
				}
				else
				{
					terrain[r][c] = true;
					terrainEntities[r][c]=spawn("ground",(r+dx)*blockSize,(c+dy)*blockSize);
					System.out.print("[true] ");
				}
			}
			System.out.println();	
			

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
		//spawns the main base in the lower right hand quarter
		unitEntities[(mapSize/4)*3][(mapSize/4)*3]=spawn("mainBase",((mapSize/4)*3+dx)*blockSize,((mapSize/4)*3+dy)*blockSize);
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
//called when leftclick happens
public void onLeftClick()
{
	if(buildID == "factory")
	{
		unitEntities[mouseX/blockSize][mouseY/blockSize]=spawn("factory",mouseX*blockSize,mouseY*blockSize);
		
		buildID = "";
	}
	else {
		selected.add(unitEntities[mouseX][mouseY]);
		
	}
}
public Button makeButtonForUI(String str)
{
	Button factorybtn = new Button("");
    if(str == "factory")
    	factorybtn.setOnAction(e -> buttonMaker("factory"));
    factorybtn.setTranslateX(750); 
    factorybtn.setTranslateY(700); 
    factorybtn.setPrefWidth(50);
    factorybtn.setPrefHeight(50);
    factorybtn.setOpacity(0);
    return factorybtn;
}
public Texture makeImageForUI(String str)
{
	Image image = new Image("/resources/UnitPlaceHolder.png");  
	if(str == "factory")
	{
		image = new Image("/resources/factory.png");
	}
	Texture tex = new Texture(image);
	    tex.setTranslateX(750);
	    tex.setTranslateY(700);
	    return tex;
}
	//stuff that factory button does when clicked
public void buttonMaker(String str)
{
	if(str == "factory")
	{
		buildID = "factory";
	}
	selected.clear();
	System.out.println(buildID);
}
	
	/**starts the game application*/ 
	public static void main(String[] args)
	{
		
		launch(args);
		
	}
}