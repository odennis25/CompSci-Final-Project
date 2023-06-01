package core;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import units.Unit;
import units.UnitType;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
public class UIFactory implements EntityFactory
{
	@Spawns ("button")
	public Entity button(SpawnData data) 
	{
		Image image = new Image("/resources/soulja.png");
		
	    return  entityBuilder(data)
	            .view(new Texture(image))
	            .build();
	}
}
