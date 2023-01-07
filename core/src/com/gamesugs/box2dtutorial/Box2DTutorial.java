package com.gamesugs.box2dtutorial;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

	 
	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		preferences = new AppPreferences();

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
