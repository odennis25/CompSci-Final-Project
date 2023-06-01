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
public class UnitFactory implements EntityFactory{

	
	
	@Spawns("infantry")
	public Entity infantry(SpawnData data) 
	{
	   Image image = new Image("/resources/soulja.png");//need to make this url universal
	   
	   
	   
		
	    return  entityBuilder(data)

	            .type(UnitType.INFANTRY)
	            .view(new Texture(image))
	            .bbox(new HitBox(BoundingShape.circle(150)))
	            .build();
	}
	
	@Spawns ("none")
	public Entity none(SpawnData data) 
	{
	    
		
	    return  entityBuilder(data)
	            .type(UnitType.NONE)
	            .build();
	}
	
	@Spawns ("enemyInfantry")
	public Entity enemyInfantry(SpawnData data) 
	{
	   Image image = new Image("/resources/soulja.png");
		
	    return  entityBuilder(data)
	            .type(UnitType.ENEMYINFANTRY)
	            .view(new Texture(image))
	            .build();
	}
	
	@Spawns ("factory")
	public Entity factory(SpawnData data) 
	{
	    
		
	    return  entityBuilder(data)
	            .type(UnitType.FACTORY)
	            .build();
	}
	@Spawns ("button")
	public Entity button(SpawnData data) 
	{
		Image image = new Image("/resources/soulja.png");
		
	    return  entityBuilder(data)
	            .type(UnitType.BUTTON)
	            .view(new Texture(image))
	            .build();
	}
}
