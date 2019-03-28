package com.algodal.game.rockpaperscissors;

import com.algodal.game.rockpaperscissors.platform.Platform;

public class Android extends Platform {

	private int interCounter = 0;

	public final AndroidLauncher launcher;

	public Android(Object platformObject) {
		super(platformObject);
		this.launcher = (AndroidLauncher)platformObject;
	}

	@Override
	public void playInterstitialAd() {
		interCounter ++;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if(interCounter > 5){
			launcher.showInterstitialAd();
			interCounter = 0;
		}
	}
}
