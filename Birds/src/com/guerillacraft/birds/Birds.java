package com.guerillacraft.birds;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Birds implements ApplicationListener {
	private static final String TAG = "Birds";

	private static final int NUMBER_OF_FRAMES = 6;
	private static final String KEY_FRAME_NAME = "frame";
	private static final float ANIMATION_DURATION_IN_SEC = 1f;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Animation animation;
	private float stateTime;
	private TextureRegion currentFrame;
	private OrthographicCamera camera;
	float screenWidth;
	float screenHeight;
	
	@Override
	public void create() {		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(screenWidth, screenHeight);
		Gdx.app.log(TAG, "screen w x h " + screenWidth + " x " + screenHeight);
		
		batch = new SpriteBatch();

		atlas = new TextureAtlas(Gdx.files.internal("data/birdie-bob.txt"), Gdx.files.internal("data/"));

		animation = new Animation(ANIMATION_DURATION_IN_SEC / NUMBER_OF_FRAMES, atlas.findRegions(KEY_FRAME_NAME));
		stateTime = 0;
	}

	@Override
	public void dispose() {
		batch.dispose();
		atlas.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		float deltaTime = Gdx.graphics.getDeltaTime(); 
		stateTime += deltaTime;
		currentFrame = animation.getKeyFrame(stateTime, true);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(currentFrame, -screenWidth/2, -screenHeight/2);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
