package core;

import javafx.scene.layout.Pane;
import map.TerrainMap;
import map.UnitMap;
import javafx.scene.transform.Translate;
public class Camera {
	private int X;
	private int Y;
	
	public Camera(int x, int y) {
		X=x;
		Y=y;
		
	}
	
	public void moveUp() {
		Y-=1;
		
		
	}
	
	public void moveDown() {
		Y+=1;
		
	}
	
	public void moveLeft() {
		X-=1;		
	}
	
	public void moveRight() {
		X+=1;
		
	}
	
	public int getDX() {
		return X;
	}
	
	public int getDY() {
		return Y;
	}

	
	public void setDX(int x) {
		X=x;
	}
	
	public void setDY(int y) {
		Y=y;
	}

}
