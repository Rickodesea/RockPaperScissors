package com.algodal.game.rockpaperscissors.entities;

import static com.algodal.game.rockpaperscissors.Constants.defAtlas;
import static com.algodal.game.rockpaperscissors.Constants.fromLeft;
import static com.algodal.game.rockpaperscissors.Constants.fromTop;
import static com.algodal.game.rockpaperscissors.Constants.worldHeight;
import static com.algodal.game.rockpaperscissors.Constants.worldWidth;

import com.algodal.game.rockpaperscissors.Entity;
import com.algodal.game.rockpaperscissors.State;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.Unit;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Title extends Entity {

	private final NormalState normalState = new NormalState();
	
	public Title() {
		setState(normalState);
	}
	
	public class NormalState extends State {

		private final Unit body;
		
		public NormalState() {
			body = new Unit();
			body.width = worldWidth * 0.60f;
			body.height = worldHeight * 0.07f;
			body.x = fromLeft(0.50f);
			body.y = fromTop(0.0600f);
		}
		
		@Override
		public void render(SubGame sg, float delta) {
			final TextureAtlas atlas = sg.get(defAtlas);
			final TextureRegion tr = atlas.findRegion("title");
			
			sg.begin(null, null);
			sg.draw(tr, body);
			sg.end();
		}
	}
}
