package core;

import javafx.scene.layout.Pane;
import map.TerrainMap;
import map.UnitMap;
import javafx.scene.transform.Translate;
public class Camera {
	private int globalX;
	private int globalY;
	Pane pane;
	public Camera(int x, int y,Pane p) {
		globalX=x*RTSMain.getBlockSize();
		globalY=y*RTSMain.getBlockSize();;
		pane=p;
	}
	
	public void moveUp() {
		globalY-=1*RTSMain.getBlockSize();
		
		
	}
	
	public void moveDown() {
		globalY+=1*RTSMain.getBlockSize();
		
	}
	
	public void moveLeft() {
		globalX-=1*RTSMain.getBlockSize();
		
	}
	
	public void moveRight() {
		globalX+=1*RTSMain.getBlockSize();
		
	}
	
	private void move(int dx, int dy) {
		
		
	}



}
