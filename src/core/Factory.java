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

public class Factory implements EntityFactory {


@Spawns("terrain")
public Entity ground(SpawnData data) {
    Image image = new Image("D:\\CompSciFinal\\src\\resources/grass.png");
	//System.out.println(image.getHeight()+""+image.getWidth());
    return entityBuilder(data)
            .type(TerrainType.GROUND)
            .view(new Texture(image))
            .build();
}
}