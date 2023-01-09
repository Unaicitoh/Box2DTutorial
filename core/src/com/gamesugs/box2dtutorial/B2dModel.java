package com.gamesugs.box2dtutorial;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gamesugs.box2dtutorial.controller.KeyboardController;

public class B2dModel {

	public World world;
	private Body bodyd;
	private Body bodys;
	private Body bodyk;
	public Body player;
	public boolean isSwimming = false;
	private KeyboardController controller;
	private OrthographicCamera camera;
	
	public B2dModel(KeyboardController contr, OrthographicCamera cam) {
		controller=contr;
		camera=cam;
		world = new World(new Vector2(0,-10f),true);
		world.setContactListener(new B2dContactListener(this));
		createFloor();
		//createObject();
		//createMovingObject();
			
		// get our body factory singleton and store it in bodyFactory
		BodyFactory bodyFactory = BodyFactory.getInstance(world);
			
		// add a player
		player = bodyFactory.makeBoxPolyBody(1, 1, 2, 2, BodyFactory.RUBBER, BodyType.DynamicBody,false);
			
		// add some water
		Body water =  bodyFactory.makeBoxPolyBody(1, -8, 40, 15, BodyFactory.RUBBER, BodyType.StaticBody,false);
		water.setUserData("IAMTHESEA");
		// make the water a sensor so it doesn't obstruct our player
		bodyFactory.makeAllFixturesSensors(water);	
			
	}

	public boolean pointIntersectsBody(Body body, Vector2 mouseLocation){
		Vector3 mousePos = new Vector3(mouseLocation,0); //convert mouseLocation to 3D position
		camera.unproject(mousePos); // convert from screen potition to world position
		if(body.getFixtureList().first().testPoint(mousePos.x, mousePos.y)){
			return true;
		}
		return false;
	}
	
	public void logicStep(float delta) {
		if(controller.left){
			player.applyForceToCenter(-10, 0,true);
		}if(controller.right){
			player.applyForceToCenter(10, 0,true);
		}if(controller.up){
			player.applyForceToCenter(0, 10,true);
		}if(controller.down){
			player.applyForceToCenter(0, -10,true);
		}
		if(isSwimming){
			player.applyForceToCenter(0, 45, true);
		}

		// check if mouse1 is down (player click) then if true check if point intersects
		if(controller.isMouse1Down && pointIntersectsBody(player,controller.mouseLocation)){
			System.out.println("Player was clicked");
		}
		world.step(delta,3, 3);
	}
	
	private void createObject(){
		
		//create a new body definition (type and location)
	        BodyDef bodyDef = new BodyDef();
	        bodyDef.type = BodyDef.BodyType.DynamicBody;
	        bodyDef.position.set(0,0);


	        // add it to the world
	        bodyd = world.createBody(bodyDef);

	        // set the shape (here we use a box 1 meters wide, 1 meter tall )
	        PolygonShape shape = new PolygonShape();
	        shape.setAsBox(1,1);

	        // set the properties of the object ( shape, weight, restitution(bouncyness)
	        FixtureDef fixtureDef = new FixtureDef();
	        fixtureDef.shape = shape;
	        fixtureDef.density = 1f;

	        // create the physical object in our body)
	        // without this our body would just be data in the world
	        bodyd.createFixture(fixtureDef);

	        // we no longer use the shape object here so dispose of it.
	        shape.dispose();
	        
	        
	        
	}
	
	private void createFloor() {
		// create a new body definition (type and location)
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(0, -10);
		// add it to the world
		bodys = world.createBody(bodyDef);
		// set the shape (here we use a box 50 meters wide, 1 meter tall )
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50, 1);
		// create the physical object in our body)
		// without this our body would just be data in the world
		bodys.createFixture(shape, 0.0f);
		// we no longer use the shape object here so dispose of it.
		shape.dispose();
	}
	
	private void createKinematicObject(){
		
		//create a new body definition (type and location)
	        BodyDef bodyDef = new BodyDef();
	        bodyDef.type = BodyDef.BodyType.KinematicBody;
	        bodyDef.position.set(0,-10);


	        // add it to the world
	        bodyk = world.createBody(bodyDef);

	        // set the shape (here we use a box 1 meters wide, 1 meter tall )
	        PolygonShape shape = new PolygonShape();
	        shape.setAsBox(1,1);

	        // set the properties of the object ( shape, weight, restitution(bouncyness)
	        FixtureDef fixtureDef = new FixtureDef();
	        fixtureDef.shape = shape;
	        fixtureDef.density =1f;

	        // create the physical object in our body)
	        // without this our body would just be data in the world
	        bodyk.createFixture(fixtureDef);

	        // we no longer use the shape object here so dispose of it.
	        shape.dispose();
	        
	        bodyk.setLinearVelocity(0f, 2f);
	        
	        
	}
	
}
