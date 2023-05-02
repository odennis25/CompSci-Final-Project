package units;

import com.almasb.fxgl.entity.Entity;

import map.UnitMap;

public class Unit extends Entity {
	private UnitType type;

	public Unit(UnitType type) {
	
		this.type=type;
	}
		
	public UnitType getUType() {
		return type;
	}

	

	public void setUType(UnitType type) {
			this.type=type;
		}
	


	public static void MoveUnit(int startX, int startY, int desX, int desY, UnitMap map) {
	
	UnitType type =map.getUMap()[startX][startY].getUType();
	
	map.setUMap(desX, desY, type);
	map.setUMap(startX, startY, UnitType.EMPTY);
	
}
	}