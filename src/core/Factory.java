package core;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.dsl.components.LiftComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import map.TerrainType;
import units.UnitType;

public class Factory implements EntityFactory {


@Spawns("ground")
public Entity ground(SpawnData data) {
    Image image = new Image("D:\\CompSciFinal\\src\\resources/grass.png");//need to make this url universal
	
    return entityBuilder(data)
            .type(TerrainType.GROUND)
            .view(new Texture(image))
            .build();
    
}
@Spawns("water")
public Entity water(SpawnData data) {
    Image image = new Image("D:\\CompSciFinal\\src\\resources/water.png");//need to make this url universal
   
    return entityBuilder(data)
            .type(TerrainType.WATER)
            .view(new Texture(image))
            .build();
}
@Spawns("cliff")
public Entity cliff(SpawnData data) {
    Image image = new Image("cliff.png");//need to make this url universal
	
    return entityBuilder(data)
            .type(TerrainType.WATER)
            .view(new Texture(image))
            .build();
}

@Spawns("infantry")
public Entity infantry(SpawnData data) {
    Image image = new Image("D:\\CompSciFinal\\src\\resources/grass.png");//need to make this url universal
	
    return entityBuilder(data)
            .type(UnitType.INFANTRY)
            .view(new Texture(image))
            .build();
}

}