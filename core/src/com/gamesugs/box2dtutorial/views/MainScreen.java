package com.gamesugs.box2dtutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gamesugs.box2dtutorial.B2dModel;
import com.gamesugs.box2dtutorial.Box2DTutorial;
import com.gamesugs.box2dtutorial.controller.KeyboardController;

public class MainScreen implements Screen {

	private Box2DTutorial parent; // a field to store our orchestrator
	private B2dModel model;
	private OrthographicCamera cam;
	private Box2DDebugRenderer debugRenderer;
	private KeyboardController controller;
	private AtlasRegion playerTex;
	private SpriteBatch sb;
	private TextureAtlas atlas;

	// our constructor with a Box2DTutorial argument
	public MainScreen(Box2DTutorial box2dTutorial){
		parent = box2dTutorial;     // setting the argument to our field.
		controller = new KeyboardController();
		cam = new OrthographicCamera(32,24);
		model = new B2dModel(controller,cam,parent.assMan);
		sb = new SpriteBatch();
		sb.setProjectionMatrix(cam.combined);
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
		// tells our asset manger that we want to load the images set in loadImages method
		atlas = parent.assMan.manager.get("images/game.atlas"); // new
		playerTex = atlas.findRegion("player"); // updated
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void render(float delta) {
		
		model.logicStep(delta);
		ScreenUtils.clear(0,0,0,1);
		debugRenderer.render(model.world, cam.combined);
		sb.begin();
		sb.draw(playerTex, model.player.getPosition().x-1,model.player.getPosition().y-1,2,2);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
