package core;

import map.UnitMap;
import units.UnitType;

public class  MoveUnit {

	public static void MoveUnit(int startX, int startY, int desX, int desY, UnitMap map) {
		
		UnitType type =map.getUMap().get(startX).get(startY).getType();
		
		map.setUMap(desX, desY, type);
		map.setUMap(startX, startY, UnitType.EMPTY);
		
	}
}
