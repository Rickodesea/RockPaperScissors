package com.algodal.game.rockpaperscissors.scenes;

import static com.algodal.game.rockpaperscissors.Constants.menScene;

import com.algodal.game.rockpaperscissors.Scene;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.entities.Background;
import com.algodal.game.rockpaperscissors.entities.MenuButtons;
import com.algodal.game.rockpaperscissors.entities.Title;

public class Menu extends Scene {

	public Menu() {
		entities.add(new Background());
		entities.add(new MenuButtons());
		entities.add(new Title());
	}
	
	@Override
	public void show(SubGame sg) {
		sg.st.clear();
		super.show(sg);
	}
	
	@Override
	public String name() {
		return menScene;
	}
	
}
