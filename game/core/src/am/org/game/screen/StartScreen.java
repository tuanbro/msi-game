package am.org.game.screen;

import static am.org.game.resources.InitDataResource.HEIGHT;
import static am.org.game.resources.InitDataResource.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import am.org.game.MainGame;
import am.org.game.GameStatus;

public class StartScreen extends MyScreen{
	private Texture _img;
	private Sound _sCorrect, _sFail, _sQuestion;
	
	public StartScreen() {
		init();
	}
	
	@Override
	public void init() {
		_img = new Texture("w.png");
		_sCorrect = Gdx.audio.newSound(Gdx.files.internal("ting.wav"));
		_sFail = Gdx.audio.newSound(Gdx.files.internal("fail.mp3"));
		_sQuestion = Gdx.audio.newSound(Gdx.files.internal("question.wav"));
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(_img, MainGame.x, MainGame.y, WIDTH, HEIGHT);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(MainGame.GAME_STATUS == GameStatus.START){
			if(Keys.NUM_1 <= keycode && keycode <= Keys.NUM_9){
				_sQuestion.play();
				MainGame.GAME_STATUS = GameStatus.ANSWER;
				AnswerScreen.QUESTION = keycode - Keys.NUM_1;
				return true;
			} else if(keycode == Keys.F2){
				_sCorrect.play();
			} else if(keycode == Keys.F3){
				_sFail.play();
			}
		}
		return false;
	}
	
	@Override
	public void dispose() {
		_img.dispose();
		_sCorrect.dispose();
		_sFail.dispose();
		_sQuestion.dispose();
	}

}
