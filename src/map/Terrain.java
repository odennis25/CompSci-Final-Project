package map;

public class Terrain {
private boolean occupied;
private TerrainType type;

public Terrain(TerrainType type) {
	occupied=false;
	this.type=type;
}
	
public TerrainType getType() {
	return type;
};

public boolean getOccupied(){
	return occupied;
}

public void setType(TerrainType type) {
		this.type=type;
	}
}
