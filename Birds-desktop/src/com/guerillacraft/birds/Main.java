package com.guerillacraft.birds;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Birds";
		cfg.useGL20 = false;
		cfg.width = 1240;
		cfg.height = 760;
		
		new LwjglApplication(new Birds(), cfg);
	}
}
