package com.gamesugs.box2dtutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamesugs.box2dtutorial.Box2DTutorial;

public class LoadingScreen implements Screen {

	private static final int FONT = 1;
	private static final int PARTY = 2;
	private static final int SOUND = 3;
	private static final int MUSIC = 4;
	private Box2DTutorial parent; // a field to store our orchestrator
	private TextureAtlas atlas;
	private AtlasRegion title;
	private AtlasRegion dash;
	private SpriteBatch sb;
	private int currentLoadingStage=0;
	private float countDown=5f;
	private Animation flameAnimation;
	private float stateTime;

	// our constructor with a Box2DTutorial argument
	public LoadingScreen(Box2DTutorial box2dTutorial){
		parent = box2dTutorial;     // setting the argument to our field.
		
		sb = new SpriteBatch();
		sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
	}
	
	@Override
	public void show() {
		// load loading images and wait until finished
		parent.assMan.queueAddLoadingImages();
		parent.assMan.manager.finishLoading();
		stateTime = 0f;		
		// get images used to display loading progress
		atlas = parent.assMan.manager.get("images/loading.atlas");
		title = atlas.findRegion("staying-alight-logo");
		dash = atlas.findRegion("loading-dash");
		 
		flameAnimation = new Animation(0.07f, atlas.findRegions("flames/flames"), PlayMode.LOOP);  //new

		
		// initiate queueing of images but don't start loading
		parent.assMan.queueAddImages();
		System.out.println("Loading images....");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1); //  clear the screen 
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		    
	        stateTime += delta; // Accumulate elapsed animation time  // new
	     // Get current frame of animation for the current stateTime
	     TextureRegion currentFrame = (TextureRegion) flameAnimation.getKeyFrame(stateTime, true);  //new
	             
	     sb.begin();
	     drawLoadingBar(currentLoadingStage*2, currentFrame);  // new
	     sb.draw(title, 135, 250);
	     sb.end();
		
	        // check if the asset manager has finished loading
		if (parent.assMan.manager.update()) { // Load some, will return true if done loading
		    currentLoadingStage+= 1;
	            switch(currentLoadingStage){
	            case FONT:
	            	System.out.println("Loading fonts....");
	            	parent.assMan.queueAddFonts(); // first load done, now start fonts
	            	break;
	            case PARTY:	
	            	System.out.println("Loading Particle Effects....");
	            	parent.assMan.queueAddParticleEffects(); // fonts are done now do party effects
	            	break;
	            case SOUND:
	            	System.out.println("Loading Sounds....");
	            	parent.assMan.queueAddSounds();
	            	break;
	            case MUSIC:
	            	System.out.println("Loading music....");
	            	parent.assMan.queueAddMusic();
	            	break;
	            case 5:	
	            	System.out.println("Finished"); // all done
	            	break;
	            }
		    if (currentLoadingStage >5){
		    	countDown -= delta;  // timer to stay on loading screen for short preiod once done loading
		    	currentLoadingStage = 5;  // cap loading stage to 5 as will use later to display progress bar anbd more than 5 would go off the screen
		    	if(countDown < 0){ // countdown is complete
		    		parent.changeScreen(Box2DTutorial.MENU);  /// go to menu screen
		    	}
	            }
	      }
	}

	private void drawLoadingBar(int stage, TextureRegion currentFrame){
		for(int i = 0; i < stage;i++){
			sb.draw(currentFrame, 50 + (i * 50), 150, 50, 50);
			sb.draw(dash, 35 + (i * 50), 140, 80, 80); 
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
