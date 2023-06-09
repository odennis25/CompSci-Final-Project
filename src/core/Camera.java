package core;

import javafx.scene.layout.Pane;
import map.TerrainMap;
import map.UnitMap;
import javafx.scene.transform.Translate;
/**
 * camera that can move around
 * @author Owen
 *
 */
public class Camera {
	private double dX;
	private double dY;
	private double x;
	private double y;
	
		
		public Camera(double x, double y) {
			dX = x;
			dY = y;
			this.x=x;
			
			this.y=0;
		}
		
		public void moveUp() {
			
			dY -= 1;
			y-=1;	}	
			
		
		public void moveDown() {
			dY += 1;
			y+=1;
		}
		
		public void moveLeft() {
			
			
			dX -= 1;	
			x-=1;}
			
		
		public void moveRight() {
			
			dX += 1;	
			x+=1;}
		
		
		
		public void setDX(int num) {
			dX=num;
		}
		
		public void setDY(int num) {
			dY=num;
		}
		
		
		public double getx() { //left right
			return x;
		}
		
		public double gety() { //up down
			return y;
		}
		
		
		
		public double getDX() { //left right
			return dX;
		}
		
		public double getDY() { //up down
			return dY;
		}
}
