package com.algodal.game.rockpaperscissors.dialogs;

import com.algodal.game.rockpaperscissors.LateInitialization;
import com.algodal.game.rockpaperscissors.SubGame;

public interface IDialog {

	public void show(SubGame sg);
	public LateInitialization getLateInitialization();
}
