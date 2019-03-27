package com.algodal.game.rockpaperscissors.entities;

import static com.algodal.game.rockpaperscissors.Constants.defAtlas;
import static com.algodal.game.rockpaperscissors.Constants.worldHeight;
import static com.algodal.game.rockpaperscissors.Constants.worldWidth;

import com.algodal.game.rockpaperscissors.Entity;
import com.algodal.game.rockpaperscissors.State;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.Unit;
import com.algodal.game.rockpaperscissors.utils.BackgroundSkin;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background extends Entity {
	protected final Unit body;
	
	private final NormalState normalState;
	
	public Background() {
		body = new Unit();
		body.width = worldWidth;
		body.height = worldHeight;
		
		normalState = new NormalState();
		
		setState(normalState);
	}
	
	public class NormalState extends State {

		@Override
		public String name() {
			return "normal state";
		}

		@Override
		public void render(SubGame sg, float delta) {
			final TextureAtlas atlas = sg.get(defAtlas);
			final BackgroundSkin bgSkin = sg.data.play.setting.bgSkin();
			
			final String region = bgSkin.getRegion();
			final TextureRegion tr = atlas.findRegion(region);
			
			sg.begin(null, null);
			sg.draw(tr, body);
			sg.end();
		}
	}
}
