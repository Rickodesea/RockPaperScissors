package com.algodal.game.rockpaperscissors;

import com.algodal.game.rockpaperscissors.platform.Platform;
import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	
	private SubGame sg;
	private boolean running = true;
	
	public final Platform platform;
	
	public Game(Platform platform) {
		super();
		this.platform = platform;
	}
	
	@Override
	public void create() {
		sg = new SubGame(platform);
		platform.setSubGame(sg);
		running = true;
	}
	
	@Override
	public void resize(int width, int height) {
		sg.resize(width, height);
	}
	
	@Override
	public void render() {

		if(running) {
			sg.render();
			platform.update(1f/60);
		}
	}
	
	@Override
	public void pause() {
		sg.pause();
		running = false;
	}
	
	@Override
	public void resume() {
		running = true;
	}
	
	@Override
	public void dispose() {
		sg.destroy();
	}
}
