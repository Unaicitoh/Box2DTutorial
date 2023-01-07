package com.gamesugs.box2dtutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamesugs.box2dtutorial.Box2DTutorial;

public class PreferencesScreen implements Screen {

	private Box2DTutorial parent; // a field to store our orchestrator
	private Stage stage;
	// our constructor with a Box2DTutorial argument
	public PreferencesScreen(Box2DTutorial box2dTutorial){
		parent = box2dTutorial;     // setting the argument to our field.
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0,0,1);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/60f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

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
		stage.dispose();
	}

}
