package com.gamesugs.box2dtutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamesugs.box2dtutorial.Box2DTutorial;

public class PreferencesScreen implements Screen {

	private Box2DTutorial parent; // a field to store our orchestrator
	private Stage stage;
	// our new fields
		private Label titleLabel; 
		private Label volumeMusicLabel;
		private Label volumeSoundLabel;
		private Label musicOnOffLabel;
		private Label soundOnOffLabel;
	
	// our constructor with a Box2DTutorial argument
	public PreferencesScreen(Box2DTutorial box2dTutorial){
		parent = box2dTutorial;     // setting the argument to our field.
		stage = new Stage(new ScreenViewport());
		
	}
	
	@Override
	public void show() {
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
//		table.setDebug(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		//volume
		final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin);
		        volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
		        volumeMusicSlider.addListener( new EventListener() {
		  		@Override
				public boolean handle(Event event) {
		  			parent.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
		                return false;
			}
		});
		        
		final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, skin);
		volumeMusicSlider.setValue(parent.getPreferences().getSoundVolume());
		volumeMusicSlider.addListener( new EventListener() {
				@Override
				public boolean handle(Event event) {
		  			parent.getPreferences().setSoundVolume(volumeSoundSlider.getValue());
		                return false;
			}
		});   
		

		 //music
		final CheckBox musicCheckbox = new CheckBox(null, skin);
		musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
		musicCheckbox.addListener( new EventListener() {
		   	@Override
			public boolean handle(Event event) {
		       	boolean enabled = musicCheckbox.isChecked();
		       	parent.getPreferences().setMusicEnabled( enabled );
		       	return false;
			}
		});
		
		 //music
		final CheckBox soundCheckbox = new CheckBox(null, skin);
		musicCheckbox.setChecked( parent.getPreferences().isSoundEffectsEnabled() );
		musicCheckbox.addListener( new EventListener() {
		   	@Override
			public boolean handle(Event event) {
		       	boolean enabled = soundCheckbox.isChecked();
		       	parent.getPreferences().setSoundEffectsEnabled(enabled);
		       	return false;
			}
		});
		
		// return to main screen button
		final TextButton backButton = new TextButton("Back", skin, "small"); // the extra argument here "small" is used to set the button to the smaller version instead of the big default version
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(Box2DTutorial.MENU);
			}
		});
		
		titleLabel = new Label( "Preferences", skin );
		volumeMusicLabel = new Label( "Music Volume", skin );
		volumeSoundLabel = new Label( "Sound Volume", skin );
		musicOnOffLabel = new Label( "Music", skin );
		soundOnOffLabel = new Label( "Sound Effect", skin );
			
		table.add(titleLabel).colspan(2);
		table.row().pad(10,0,0,10);
		table.add(volumeMusicLabel);
		table.add(volumeMusicSlider);
		table.row().pad(10,0,0,10);
		table.add(musicOnOffLabel);
		table.add(musicCheckbox);
		table.row().pad(10,0,0,10);
		table.add(volumeSoundLabel);
		table.add(volumeSoundSlider);
		table.row().pad(10,0,0,10);
		table.add(soundOnOffLabel);
		table.add(soundCheckbox);
		table.row().pad(10,0,0,10);
		table.add(backButton).colspan(2);
		
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
