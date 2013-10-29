package com.guerillacraft.birds;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class Birds implements ApplicationListener {
	private static final int NUMBER_OF_FRAMES = 55;
	private static final String SPRITE_NAME = "vtak_modry/";
	private static final String TAG = "Birds";
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private AtlasRegion[] atlasRegion;
	private Animation flyingBird;
	private float stateTime;
	private TextureRegion currentFrame;
	private Sprite sprite;
	private OrthographicCamera camera;
	
	@Override
	public void create() {		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float viewPortWidth = 1;
		float viewPortHeight = viewPortWidth * screenHeight / screenWidth;
		camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
		Gdx.app.log(TAG, "screen w x h " + screenWidth + " x " + screenHeight);
		Gdx.app.log(TAG, "camera w x h " + viewPortWidth + " x " + viewPortHeight);
		
		batch = new SpriteBatch();
		//batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, w, h));

		atlas = new TextureAtlas(Gdx.files.internal("data/blue_bird.txt"), Gdx.files.internal("data/"));
		sprite = atlas.createSprite("vtak_modry/0000");
		Gdx.app.log(TAG, "sprite orig size w x h " + sprite.getWidth() + " x " + sprite.getHeight());
		float spriteWidth = 0.5f;
		float spriteHeight = spriteWidth * sprite.getHeight() / sprite.getWidth();
		sprite.setSize(spriteWidth, spriteHeight);
		Gdx.app.log(TAG, "sprite set size w x h " + spriteWidth + " x " + spriteHeight);
		float x = -viewPortWidth/2 * 0;
		float y = -viewPortHeight/2 * 0;
		sprite.setPosition(x, y);
		Gdx.app.log(TAG, "sprite position x,y " + x + "," + y);
		atlasRegion = new AtlasRegion[NUMBER_OF_FRAMES];
		for (int frameIdx = 0; frameIdx < NUMBER_OF_FRAMES; frameIdx++) {
			atlasRegion[frameIdx] = atlas.findRegion(SPRITE_NAME + String.format("%04d", frameIdx));
			//Gdx.app.log(TAG, SPRITE_NAME + String.format("%04d", frameIdx));
		}
		flyingBird = new Animation(1f / NUMBER_OF_FRAMES, atlasRegion);
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
		currentFrame = flyingBird.getKeyFrame(stateTime, true);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		//batch.draw(currentFrame, 50 + 500 * deltaTime, 50 + 100 * deltaTime);
		//batch.draw(currentFrame, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
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
