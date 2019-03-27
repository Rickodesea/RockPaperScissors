package com.algodal.game.rockpaperscissors.scenes;

import static com.algodal.game.rockpaperscissors.Constants.advScene;

import com.algodal.game.rockpaperscissors.Scene;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.entities.AdvanceButtons;
import com.algodal.game.rockpaperscissors.entities.Background;
import com.algodal.game.rockpaperscissors.entities.Title;

public class Advance extends Scene {

	public Advance() {
		entities.add(new Background());
		entities.add(new Title());
		entities.add(new AdvanceButtons());
	}
	
	@Override
	public void show(SubGame sg) {
		sg.st.clear();
		super.show(sg);
	}
	
	@Override
	public String name() {
		return advScene;
	}
}
