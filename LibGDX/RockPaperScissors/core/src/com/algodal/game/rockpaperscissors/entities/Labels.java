package com.algodal.game.rockpaperscissors.entities;

import static com.algodal.game.rockpaperscissors.Constants.defSkin;
import static com.algodal.game.rockpaperscissors.Constants.margin;
import static com.algodal.game.rockpaperscissors.Constants.setFromLeft;
import static com.algodal.game.rockpaperscissors.Constants.setFromRight;
import static com.algodal.game.rockpaperscissors.Constants.setFromTop;

import com.algodal.game.rockpaperscissors.ActorWrap;
import com.algodal.game.rockpaperscissors.Entity;
import com.algodal.game.rockpaperscissors.LateInitialization;
import com.algodal.game.rockpaperscissors.State;
import com.algodal.game.rockpaperscissors.SubGame;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.StringBuilder;

public class Labels extends Entity {

	private final NormalState normalState;
	private ActorWrap score, round;
	
	private final StringBuilder stringBuilder;
	
	public Labels() {
		normalState = new NormalState();
		setState(normalState);
		stringBuilder = new StringBuilder();
	}
	
	@Override
	public LateInitialization getLateInitialization() {
		return new LateInitialization() {
			@Override
			public void initialize(SubGame sg) {
				score = new ActorWrap(new Label("Score 00 v 00", (Skin) sg.get(defSkin)));
				round = new ActorWrap(new Label("00 / 00 Round", (Skin) sg.get(defSkin)));
				
				final float scale = 1f / 2.5f;
				
				score.setScale(scale);
				round.setScale(scale);
				
				setFromTop(0.15f, score, false);
				setFromLeft(margin, score, false);
				
				setFromTop(0.15f, round, false);
				setFromRight(margin, round, false);
			}
		};
	}
	
	private void update(SubGame sg) {
		stringBuilder.clear();
		stringBuilder.append("Score ");
		if(sg.data.play.player01.scoreAmount<10) stringBuilder.append("0");
		stringBuilder.append(sg.data.play.player01.scoreAmount);
		stringBuilder.append(" v ");
		if(sg.data.play.player02.scoreAmount<10) stringBuilder.append("0");
		stringBuilder.append(sg.data.play.player02.scoreAmount);
		final Label scoreLabel = (Label)score.actor;
		scoreLabel.setText(stringBuilder);
		
		stringBuilder.clear();
		if(sg.data.play.setting.completedRounds<10) stringBuilder.append("0");
		stringBuilder.append(sg.data.play.setting.completedRounds);
		stringBuilder.append(" / ");
		if(sg.data.play.setting.maxRounds<10) stringBuilder.append("0");
		stringBuilder.append(sg.data.play.setting.maxRounds);
		stringBuilder.append(" Round");
		final Label roundLabel = (Label)round.actor;
		roundLabel.setText(stringBuilder);
	}
	
	public class NormalState extends State {

		@Override
		public void show(SubGame sg) {
			sg.st.addActor(score);
			sg.st.addActor(round);
		}
		
		@Override
		public void render(SubGame sg, float delta) {
			update(sg);
		}
		
	}
}
