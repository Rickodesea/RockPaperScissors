package com.algodal.game.rockpaperscissors.dialogs;

import static com.algodal.game.rockpaperscissors.Constants.defSkin;
import static com.algodal.game.rockpaperscissors.Constants.worldWidth;

import com.algodal.game.rockpaperscissors.LateInitialization;
import com.algodal.game.rockpaperscissors.SubGame;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class ForceQuitDialog implements IDialog {

	private final LateInitialization lateInitialization;
	private Dialog dialog;
	private final static float scale = 1f / 4.0f;
	
	public String title = "Force Quit";
	public Runnable runnable;
	
	public ForceQuitDialog() {
		
		lateInitialization = new LateInitialization() {
			
			@Override
			public void initialize(final SubGame sg) {
				dialog = new Dialog("Force Quit", (Skin) sg.get(defSkin)) {
					@Override
					protected void result(Object object) {
						final boolean result = (Boolean)object;
						
						if(result && runnable!=null) runnable.run();
						sg.playTone();
					}
				};
				
				dialog.setScale(scale);
				dialog.setMovable(false);
				dialog.getTitleLabel().setAlignment(Align.center);
				
				dialog.text("You will lose your progress.");
				dialog.getContentTable().row();
				dialog.text("Are you sure you want to continue ?");
				dialog.button(" Continue ", true ); //sends "true" as the result
				dialog.button("  Cancel  ", false); //sends "false" as the result
			}
		};
	}
	
	@Override
	public void show(SubGame sg) {
		if(title==null) title = "Force Quit"; else dialog.getTitleLabel().setText(title);
		dialog.show(sg.st);
		dialog.setWidth(worldWidth/scale);
		dialog.setY(-12);
	}

	@Override
	public LateInitialization getLateInitialization() {
		return lateInitialization;
	}

}
