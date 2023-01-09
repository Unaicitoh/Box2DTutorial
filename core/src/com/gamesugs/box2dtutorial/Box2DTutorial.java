package com.gamesugs.box2dtutorial;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.gamesugs.box2dtutorial.loader.B2dAssetManager;
import com.gamesugs.box2dtutorial.views.EndScreen;
import com.gamesugs.box2dtutorial.views.LoadingScreen;
import com.gamesugs.box2dtutorial.views.MainScreen;
import com.gamesugs.box2dtutorial.views.MenuScreen;
import com.gamesugs.box2dtutorial.views.PreferencesScreen;

public class Box2DTutorial extends Game {
	
	
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private AppPreferences preferences;
	public B2dAssetManager assMan = new B2dAssetManager();
	private Music playingSong;

	 
	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		preferences = new AppPreferences();
		
		// tells our asset manger that we want to load the images set in loadImages method
		assMan.queueAddMusic();
		// tells the asset manager to load the images and wait until finished loading.
		assMan.manager.finishLoading();
		// loads the 2 sounds we use
		playingSong = assMan.manager.get("music/Rolemusic_pl4y1ng.mp3");
		playingSong.setVolume(0.5f);
		playingSong.play();

	}
	
	@Override
	public void dispose(){
		playingSong.dispose();
		assMan.manager.dispose();
	}
	
	public void changeScreen(int screen) {
		switch(screen) {
		case MENU:
			if(menuScreen == null) menuScreen = new MenuScreen(this);
			this.setScreen(menuScreen);
			break;
		case PREFERENCES:
			if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
			this.setScreen(preferencesScreen);
			break;
		case APPLICATION:
			if(mainScreen == null) mainScreen  = new MainScreen(this);
			this.setScreen(mainScreen);
			break;
		case ENDGAME:
			if(endScreen == null) endScreen = new EndScreen(this);
			this.setScreen(endScreen);
			break;
			default: System.out.println("Something went wrong");
		}
	}
	public AppPreferences getPreferences(){
		return preferences;
		
	}
}
