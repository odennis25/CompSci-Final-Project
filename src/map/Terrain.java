package map;

public class Terrain {
private boolean occupied;
private TerrainType occupiedby;

public Terrain(TerrainType occupier) {
	occupied=false;
	occupiedby=occupier;
}
	
public TerrainType getOccupier() {
	return occupiedby;
};

public boolean getOccupied(){
	return occupied;
}

public void setOccupier(TerrainType occupier) {
		occupiedby=occupier;
	}
}
