package core;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import java.awt.Color;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;

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
	            .bbox(new HitBox(BoundingShape.box(150,150)))
	            .with(new CollidableComponent(true))
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
	   var hp = new HealthIntComponent(100);
	    return  entityBuilder(data)
	            .type(UnitType.ENEMYINFANTRY)
	            .viewWithBBox(new Texture(image))
	            .with(new CollidableComponent(true))
	            .with(hp)
	            .build();
	}
	
	@Spawns ("factory")
	public Entity factory(SpawnData data) 
	{
		Image image = new Image("/resources/factory.png");
		
	    return  entityBuilder(data)
	            .type(UnitType.FACTORY)
	            .view(new Texture(image))
	            .build();
	}
	@Spawns ("mainBase")
	public Entity mainBase(SpawnData data) 
	{
		Image image = new Image("/resources/water.png");
		
	    return  entityBuilder(data)
	            .type(UnitType.MAINBASE)
	            .view(new Texture(image))
	            .build();
	}

	
	
}
