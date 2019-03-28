package com.algodal.game.rockpaperscissors.platform;

import com.algodal.game.rockpaperscissors.SubGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import static com.algodal.game.rockpaperscissors.Constants.defSkin;
import static com.algodal.game.rockpaperscissors.Constants.worldWidth;

public class Platform {

	private final static float scale = 1f / 3.5f;

	public final Object platformObject;

	public float time;
	private SubGame sg;
	
	public Platform(Object platformObject) {
		this.platformObject = platformObject;
	}
	public void setSubGame(SubGame sg) { this.sg = sg; }

	public void playInterstitialAd(){}
	public void loadBannerAd(){}
	public boolean isBannerAdLoaded(){ return false; }
	public void runInAppPurchaseWindow(){}
	public void runSignInPlayerWindow(){}
	public void runSearchForFriendsWindow(){}
	public void runSeachForPlayersWindow(){}

	public void runBooleanDialog(String title, String[] content, String yes, String no, final DialogResult result){
		final Dialog dialog = new Dialog("Dedications and Credits", (Skin) sg.get(defSkin)) {
			@Override
			protected void result(Object object) {
				Gdx.app.log("OK Dialog", object.toString());
				if(result != null) {
					if(object instanceof Boolean){
						result.isBoolean((Boolean)object);
					} else if(object instanceof Integer){
						result.isInt((Integer)object);
					} else if(object instanceof String){
						result.isString((String)object);
					}
				}
				sg.playTone();
			}
		};

		dialog.setScale(scale);
		dialog.setMovable(false);
		dialog.getTitleLabel().setAlignment(Align.center);

		dialog.text(title);
		for(int i = 0; i < content.length; i++){
			dialog.text(content[i]);
			if(i < content.length - 1) dialog.getContentTable().row();
		}
		dialog.getContentTable().align(Align.center);

		if(yes == null) dialog.button("  OK  ", true); else dialog.button(yes, true);
		if(no != null) dialog.button(no, false);
		dialog.getButtonTable().align(Align.center);

		dialog.show(sg.st);
		dialog.setWidth(worldWidth/scale);
	}

	public void runQuickBooleanDialog(String title, String...content){
		runBooleanDialog(title, content, null, null, new DialogResult());
	}

	public void update(float delta){
		time += delta;
		if(time > 60){
			time = 0;
			if(!isBannerAdLoaded()){
				loadBannerAd();
			}
		}
	}
}
