package units;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

import map.UnitMap;
/**
 * Unit class that has all of the unit info
 * @author Owen
 *
 */
public class Unit extends Entity {
	private UnitType type;
	private int x;
	private int y;
	private int hp;
	private boolean selected;
	/**
	 * 
	 * @param type	unit type
	 * @param x		x position
	 * @param y		y position
	 * @param hp	unused hp value
	 */
	public Unit(UnitType type,int x,int y,int hp) {
		super();
		this.type=type;
		
	}
	
	public UnitType getUType() {
		return type;
	}

	public Unit getU() {
		return this;
	}
	public void setXCord(int x) 
	{
		this.x= x;
	}
	public void setYCord(int y) 
	{
		this.y= y;
	}
	public void setHp(int hp) 
	{
		this.hp=hp;
	}
	
	
	public void setSelected(boolean b) {
		selected=b;
	}
	
	
	
	public int getXCord() 
	{
		return x;
	}
	
	public int getYCord() 
	{
		return y;
	}
	public int getHp() 
	{
		return hp;
	}

	/**
	 * unused method that moves unit
	 * @param desX	
	 * @param desY
	 * @param map
	 */
	public void MoveUnit(int desX, int desY, UnitMap map) {
	
	Unit type =map.getUMap()[x][y].getU();
	map.setUMap(desX, desY, type);
	map.setUMap(x, y, new Unit(null,desX,desY,hp));
	
}

	}