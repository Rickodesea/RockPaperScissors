package com.algodal.game.rockpaperscissors.scenes;

import static com.algodal.game.rockpaperscissors.Constants.skiScene;

import com.algodal.game.rockpaperscissors.Scene;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.entities.Background;
import com.algodal.game.rockpaperscissors.entities.SkinUI;
import com.algodal.game.rockpaperscissors.entities.Title;

public class SkinSetup extends Scene {

	public SkinSetup() {
		entities.add(new Background());
		entities.add(new Title());
		entities.add(new SkinUI());
	}
	
	@Override
	protected void lateInitialize(SubGame sg) {
		entities.add(sg.lockBG);
		entities.add(sg.lockHand);
		super.lateInitialize(sg);
	}
	
	@Override
	public void show(SubGame sg) {
		sg.st.clear();
		super.show(sg);
	}
	
	@Override
	public String name() {
		return skiScene;
	}
	
}
