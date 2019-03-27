package com.algodal.game.rockpaperscissors.entities;

import static com.algodal.game.rockpaperscissors.Constants.defSkin;
import static com.algodal.game.rockpaperscissors.Constants.menScene;
import static com.algodal.game.rockpaperscissors.Constants.radioDark;
import static com.algodal.game.rockpaperscissors.Constants.radioForest;
import static com.algodal.game.rockpaperscissors.Constants.setFromBottom;
import static com.algodal.game.rockpaperscissors.Constants.setFromLeft;
import static com.algodal.game.rockpaperscissors.Constants.worldWidth;

import com.algodal.game.rockpaperscissors.Entity;
import com.algodal.game.rockpaperscissors.LateInitialization;
import com.algodal.game.rockpaperscissors.State;
import com.algodal.game.rockpaperscissors.SubGame;
import com.algodal.game.rockpaperscissors.dialogs.AdsDialog;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class AdvanceButtons extends Entity {

	private final static float scale = 1f / 3.5f;
	
	private final NormalState normalState = new NormalState();
	
	private CheckBox emeraldCB, menuCB;
	private ButtonGroup<CheckBox> radios;
	private TextButton homeBtn, remAdsBtn, creditsBtn;
	private AdsDialog adsDialog;
	
	public AdvanceButtons() {
		setState(normalState);
		adsDialog = new AdsDialog();
	}
	
	@Override
	public LateInitialization getLateInitialization() {
		return new LateInitialization() {
			
			final float top = 0.70f;
			
			@Override
			public void initialize(final SubGame sg) {
				adsDialog.getLateInitialization().initialize(sg);
				
				emeraldCB = new CheckBox(radioForest, (Skin) sg.get(defSkin));	
				emeraldCB.setTransform(true);
				emeraldCB.setScale(scale);
				emeraldCB.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						sg.playTone();
					}
				});
				setFromBottom(top - 0.5f, emeraldCB);
				setFromLeft(0.25f, emeraldCB);
				
				menuCB = new CheckBox(radioDark, (Skin) sg.get(defSkin));
				menuCB.setTransform(true);
				menuCB.setScale(scale);
				menuCB.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						sg.playTone();
					}
				});
				setFromBottom(top - 0.5f, menuCB);
				setFromLeft(0.75f, menuCB);
				
				radios = new ButtonGroup<CheckBox>(emeraldCB, menuCB);
				radios.setMaxCheckCount(1);
				radios.setMinCheckCount(1);
				radios.setChecked(SubGame.Data.music_names[sg.data.music]);
				
				homeBtn = new TextButton("Home", (Skin) sg.get(defSkin));
				homeBtn.setTransform(true);
				homeBtn.setScale(scale);
				setFromBottom(0.10f, homeBtn);
				setFromLeft(0.50f, homeBtn);
				homeBtn.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						sg.setScene(menScene);
						sg.playTone();
					}
				});
				
				remAdsBtn = new TextButton("Remove Ads + Unlock Skins", (Skin) sg.get(defSkin)); remAdsBtn.setTransform(true);
				remAdsBtn.setScale(scale);
				setFromLeft(0.5f, remAdsBtn);
				setFromBottom(top, remAdsBtn);
				remAdsBtn.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Gdx.app.log("Remove Ads", "Button Selected");
						adsDialog.show(sg);
						sg.playTone();
					}
				});
				
				creditsBtn = new TextButton("Dedications and Credits", (Skin) sg.get(defSkin)); 
				creditsBtn.setTransform(true);
				creditsBtn.setScale(scale);
				setFromLeft(0.5f, creditsBtn);
				setFromBottom(top-0.10f, creditsBtn);
				creditsBtn.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						final Dialog dialog = new Dialog("Dedications and Credits", (Skin) sg.get(defSkin)) {
							@Override
							protected void result(Object object) {
								final boolean result = (Boolean)object;
								Gdx.app.log("Credits Dialog", Boolean.toString(result));
								sg.playTone();
							}
						};
						
						dialog.setScale(scale);
						dialog.setMovable(false);
						dialog.getTitleLabel().setAlignment(Align.center);
						
						dialog.text("Thanks to the Algodal Team");
						dialog.getContentTable().row();
						dialog.text("Director : Alrick Grandison");
						dialog.getContentTable().row();
						dialog.text("Nuff Respect XB*X");
						dialog.getContentTable().row();
						dialog.text("BigUp Life Tabernacle Basic School");						
						dialog.getContentTable().row();
						dialog.text("BigUp Rockfort and MoutainView");
						dialog.getContentTable().row();
						dialog.text("BigUp Jamaica");
						
						dialog.button("  OK  ", true);
						dialog.getButtonTable().align(Align.center);
						
						dialog.show(sg.st);
						dialog.setWidth(worldWidth/scale);
						
						sg.playTone();
					}
				});
			}
		};
	}
	
	public class NormalState extends State {

		@Override
		public void show(SubGame sg) {
			sg.st.addActor(emeraldCB);
			sg.st.addActor(menuCB);
			sg.st.addActor(homeBtn);
			sg.st.addActor(remAdsBtn);
			sg.st.addActor(creditsBtn);
		}
		
		@Override
		public void render(SubGame sg, float delta) {
			sg.applyStageViewport();
			sg.st.act();
			sg.st.draw();
			
			sg.data.music = radios.getCheckedIndex();
		}
		
	}
}
