package core;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;

import javafx.scene.image.Image;
import units.Unit;
import units.UnitType;

public class UnitFactory implements EntityFactory{

	
	
	@Spawns("infantry")
	public Entity infantry(SpawnData data) 
	{
	    Image image = new Image("resources/water.png");//need to make this url universal
		
	    return  entityBuilder(data)
	            .type(UnitType.INFANTRY)
	            .view(new Texture(image))
	            .build();
	}
	
	@Spawns ("none")
	public Entity none(SpawnData data) 
	{
	    
		
	    return  entityBuilder(data)
	            .type(UnitType.NONE)
	            .build();
	}
}
