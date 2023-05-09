package core;

import javafx.scene.layout.Pane;
import map.TerrainMap;
import map.UnitMap;
import javafx.scene.transform.Translate;
public class Camera {
	private double dX;
	private double dY;
	private double x;
	private double y;
	
		
		public Camera(double x, double y) {
			dX = x;
			dY = y;
			
		}
		
		public void moveUp() {
			if(y>-4.8) {//boarder with 21x21 map and camera starting at 5,5
			dY -= 0.1;
			y-=0.1;	}	
			}
		
		public void moveDown() {
			if(y<4) {//boarder with 21x21 map and camera starting at 5,5
			dY += 0.1;
			y+=0.1;}
		}
		
		public void moveLeft() {
			
			if(x>-4.9) {//boarder with 21x21 map and camera starting at 5,5
			dX -= 0.1;	
			x-=0.1;}
			}
		
		public void moveRight() {
			if(x<-0.1) {//boarder with 21x21 map and camera starting at 5,5
			dX += 0.1;	
			x+=0.1;}
		}
		
		
		public void setDX(double num) {
			dX=num;
		}
		
		public void setDY(double num) {
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
