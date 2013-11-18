package com.guerillacraft.birds;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Birds implements ApplicationListener {
	private static final String TAG = "Birds";

	private static final int NUMBER_OF_FRAMES = 5;
	private static final String KEY_FRAME_NAME = "birdie_fly";
	private static final float ANIMATION_DURATION_IN_SEC = 0.75f;
	private static final float FLYING_DURATION = 5f;
	private float flyingSpeed;

	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Animation animation;
	private float stateTime;
	private TextureRegion currentFrame;
	private OrthographicCamera camera;
	float screenWidth;
	float screenHeight;

	private Texture backgroundTexture;

	private Sprite background;

	private float bobX;

	private BitmapFont font;

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
		
		backgroundTexture = new Texture("data/birdie_bckg.png");
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		background = new Sprite(backgroundTexture);
		background.setSize(screenWidth, screenHeight);
		background.setPosition(-screenWidth/2, -screenHeight/2);
		
		flyingSpeed = screenWidth / FLYING_DURATION;
		bobX = -screenWidth/2;
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		font.setColor(1f, 0f, 0f, 1f);
		font.setScale(5f);
	}

	@Override
	public void dispose() {
		batch.dispose();
		atlas.dispose();
		backgroundTexture.dispose();
		font.dispose();
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
		background.draw(batch);
		batch.draw(currentFrame, getX(deltaTime), 0);
		font.draw(batch, "fps:"+Gdx.graphics.getFramesPerSecond(), 0f, -screenHeight/2 + font.getLineHeight());
		batch.end();
	}

	private float getX(float deltaTime) {
		bobX += flyingSpeed * deltaTime;
		if (bobX > screenWidth) bobX = -screenWidth/2;
		return bobX;
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
