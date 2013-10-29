package com.guerillacraft.birds;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix4;

public class Birds implements ApplicationListener {
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Sprite sprite;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, w, h));

		atlas = new TextureAtlas(Gdx.files.internal("data/blue_bird.txt"), Gdx.files.internal("data/"));
		sprite = atlas.createSprite("vtak_modry/0000");
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
		
		batch.begin();
		sprite.draw(batch);
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
