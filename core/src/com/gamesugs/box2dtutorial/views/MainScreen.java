package com.gamesugs.box2dtutorial.views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gamesugs.box2dtutorial.B2dModel;
import com.gamesugs.box2dtutorial.Box2DTutorial;

public class MainScreen implements Screen {

	private Box2DTutorial parent; // a field to store our orchestrator
	private B2dModel model;
	private OrthographicCamera cam;
	private Box2DDebugRenderer debugRenderer;

	// our constructor with a Box2DTutorial argument
	public MainScreen(Box2DTutorial box2dTutorial){
		parent = box2dTutorial;     // setting the argument to our field.

		model = new B2dModel();
		cam = new OrthographicCamera(32,24);
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		model.logicStep(delta);
		ScreenUtils.clear(0,0,0,1);
		debugRenderer.render(model.world, cam.combined);
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
