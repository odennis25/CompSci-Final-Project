package map;

import java.io.Serializable;

import com.almasb.fxgl.entity.Entity;

public class Terrain extends Entity{
private boolean occupied;
private TerrainType type;

public Terrain(TerrainType type) {
	super();
	occupied=false;
	this.type=type;
}
	

public TerrainType getTType() {
	return type;
};

public boolean getOccupied(){
	return occupied;
}



public void setType(TerrainType type) {
		this.type=type;
	}


}
