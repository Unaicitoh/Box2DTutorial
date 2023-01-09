package com.gamesugs.box2dtutorial.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class B2dAssetManager {
	
	public final AssetManager manager = new AssetManager();
	
	// Textures
	public final String playerImage = "images/player.png";
	public final String enemyImage = "images/enemy.png";
	
	
	public void queueAddImages(){
		manager.load(playerImage, Texture.class);
		manager.load(enemyImage, Texture.class);
	}
}
